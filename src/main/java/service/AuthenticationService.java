package service;

import jakarta.security.enterprise.authentication.mechanism.http.LoginToContinue;
import jakarta.security.enterprise.identitystore.LdapIdentityStoreDefinition;

@LdapIdentityStoreDefinition(
        url = "ldap://localhost:389",  // Replace with your server details
        callerBaseDn = "ou=people,dc=latintech,dc=org", // Replace with your LDAP structure
        groupSearchBase = "dc=latintech,dc=org",
        bindDn = "cn=admin,dc=latintech,dc=org",
        bindDnPassword ="#{authenticateOpenLDAP.bindDnPassword}" // bind Distinguished Name password
)
@LoginToContinue(
        loginPage = "/01-login.xhtml",
        errorPage = "/errorPage.xhtml"
)
public class AuthenticationService {

    public String login(String user, String password) {
        String result;
        // LdapAuthenticator ldapAuthenticator = new LdapAuthenticator();
        // boolean isAuthenticated = ldapAuthenticator.authenticate(username, password);
        boolean isAuthenticated = true;
        System.out.println("\t\t\t login function in service");

        if (isAuthenticated) {
            result = "protected/home";
//            result = "protected/home?faces-redirect=true";
        } else {
            result = "errorPage";
        }
        return result;
    }
}
