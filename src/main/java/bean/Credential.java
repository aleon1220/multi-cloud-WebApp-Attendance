package bean;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.servlet.ServletException;
import jakarta.validation.constraints.NotBlank;
import jakarta.faces.webapp.FacesServlet;
import jakarta.servlet.http.HttpSession;
import org.primefaces.context.PrimeFacesContext;
import service.AuthenticationService;

import java.io.Serializable;

@Named
@SessionScoped
public class Credential implements Serializable {
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    AuthenticationService authService = new AuthenticationService();

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

    public String login() {
        PrimeFacesContext facesContext;
        try {
            System.out.println("\t\t\t login function in Credential");
            return authService.login(getUsername(), getPassword());
        } catch (RuntimeException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed.","Error: check username or password to find issues"+ e.getMessage()));
            return "errorPage"; // Redirect to error page
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
}