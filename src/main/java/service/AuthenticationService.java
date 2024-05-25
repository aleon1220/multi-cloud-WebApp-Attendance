package service;

import jakarta.security.enterprise.authentication.mechanism.http.LoginToContinue;
import jakarta.security.enterprise.identitystore.LdapIdentityStoreDefinition;

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
