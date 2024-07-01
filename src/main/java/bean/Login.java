package bean;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.LoginToContinue;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.Password;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStoreHandler;
import jakarta.security.enterprise.identitystore.LdapIdentityStoreDefinition;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.security.enterprise.SecurityContext;
import jakarta.servlet.http.HttpSession;
import org.primefaces.context.PrimeFacesContext;
import service.AuthenticationService;

import java.io.IOException;
import java.io.Serializable;

import static jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;

@CustomFormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/01-login.xhtml",
                errorPage = "/errorPage.xhtml"
        )
)
//https://jakarta.ee/specifications/platform/9/apidocs/jakarta/security/enterprise/identitystore/ldapidentitystoredefinition
@LdapIdentityStoreDefinition(
    url = "ldap://openldap:389",
//  groupSearchBase = "ou=group,dc=latintech,dc=org",
    callerBaseDn = "dc=latintech,dc=org",
    bindDn = "cn=admin,dc=latintech,dc=org",
    bindDnPassword ="#{login.ldapDnPassword}" // bind Distinguished Name password
//  bindDnPassword = ""
)

@Named
@RequestScoped
public class Login implements Serializable {
    @Inject
    private SecurityContext securityContext;

    @Inject
    private IdentityStoreHandler identityStoreHandler;

    @NotBlank
    private String username;
    @NotBlank
    private String password;

//    @Inject
//    @ConfigProperty(name="ldappassword")
    private String ldapPassword;

    private String ldapDnPassword = "${System.getProperty('passwordproperty')}";
//    String bindPassword = System.getenv("LDAP_ADMIN_PASS");

    AuthenticationService authService = new AuthenticationService();

    public void login() {
        PrimeFacesContext primeFacesContext;
        FacesContext context = FacesContext.getCurrentInstance();
        Credential credential = new UsernamePasswordCredential(username, new Password(password));

        try {
            System.out.println("\t\t\t login function in Login");
            CredentialValidationResult validationResult = identityStoreHandler.validate(credential);
            System.out.println("\t\t Login function validation Result " + validationResult.getStatus());
            System.out.println("\t\t\t login function credential valid? " + credential.isValid());
            // Request for authentication
            AuthenticationStatus status = securityContext.authenticate(
                    getRequest(context),
                    getResponse(context),
                    withParams().credential(credential));

//            AuthenticationStatus status = identityStoreHandler.validate(credential);
            var fancyValidation = status.equals(AuthenticationStatus.SEND_CONTINUE);
            System.out.println("\t\t Login function fancyValidation Result " + fancyValidation);

            if (credential.isValid()) {
                // Authentication mechanism has sent a redirect, continue the login process
                System.out.println("\t\t Login function If statement credential success validation");
                FacesContext facesContext = FacesContext.getCurrentInstance();
                ExternalContext externalContext = facesContext.getExternalContext();
                externalContext.redirect(externalContext.getRequestContextPath() + "/protected/home");
                facesContext.responseComplete();
            } else {
//            else if (status.equals(AuthenticationStatus.SEND_FAILURE)) {
                // Something went wrong during authentication
                addError("Authentication failed");
            }
//            return authService.login(getUsername(), getPassword());
        } catch (RuntimeException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Login Authentication failed.",
                "Error: check username or password to find issues"+ e.getMessage()));
            System.out.println("\t\t\t login function credential valid? "+ e.getMessage());
//            return "errorPage"; // Redirect to error page
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        try {
            // Logout current request
            if (session != null) {
                session.invalidate();
            }
        } catch (RuntimeException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Logout failed.","Error: webapp operation " + e.getMessage()));
//            logger.error("Logout failed", e);
            return "errorPage"; // Redirect to error page
        }
        return "01-login?faces-redirect=true"; // Redirect to login page
    }

    private void addError(String message) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
    }

    private static HttpServletResponse getResponse(FacesContext context) {
        return (HttpServletResponse) context
                .getExternalContext()
                .getResponse();
    }

    private static HttpServletRequest getRequest(FacesContext context) {
        return (HttpServletRequest) context
                .getExternalContext()
                .getRequest();
    }

    private static void addError(FacesContext context, String message) {
        context.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public @NotBlank String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank String username) {
        this.username = username;
    }

    public String getLdapDnPassword() {
        ldapDnPassword = "${System.getProperty('passwordproperty')}";
        return ldapDnPassword;
    }

    public void setLdapDnPassword(String ldapDnPassword) {
        this.ldapDnPassword = System.getenv("LDAP_ADMIN_PASS");
    }
}