package service;

import bean.Credentials;
import jakarta.inject.Inject;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.authentication.mechanism.http.LoginToContinue;
import jakarta.security.enterprise.identitystore.LdapIdentityStoreDefinition;

@LdapIdentityStoreDefinition(
        url = "ldap://your-openldap-server:389",  // Replace with your server details
        callerBaseDn = "ou=people,dc=example,dc=com", // Replace with your LDAP structure
        groupSearchBase = "ou=groups,dc=example,dc=com",
        bindDn = "cn=admin,dc=example,dc=com",       // Replace with your bind DN
        bindDnPassword = "your-admin-password"     // Replace with your bind DN password
)
@LoginToContinue(
        loginPage = "/01-login.xhtml",
        errorPage = "/404-loginError.xhtml"
)
public class AuthenticationService {

    public String login(String user, String password) {
        String result;
        // LdapAuthenticator ldapAuthenticator = new LdapAuthenticator();
        // boolean isAuthenticated = ldapAuthenticator.authenticate(username, password);
        boolean isAuthenticated = true;

        if (isAuthenticated) {
            result = "home";
        } else {
            result = "404-loginError";
        }
        return result;
    }
}
