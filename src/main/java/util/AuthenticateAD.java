// utility class authenticate LDAP call LdapAuth.authenticateUserAndGetInfo (username,password);
// Note: Configure ldapURI ,requiredAttributes ,ADSearchPaths,accountSuffex 

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import javax.naming.*;
import java.util.regex.*;
import javax.naming.directory.*;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

public class AuthenticateAD {
    /*
     * you could use a secure connection such as LDAPS (LDAP over SSL/TLS) or
     * StartTLS (LDAP over TLS)
     * Use a secure password storage mechanism: Consider using a secure password
     * storage mechanism such as a password hash or a secure encryption algorithm
     * Handle errors and exceptions more robustly: The code currently catches and
     * prints out errors and exceptions, but it does not provide specific error
     * handling or recovery mechanisms.
     * You could consider adding more robust error handling and recovery mechanisms
     * to the code to ensure that it can handle a variety of error conditions and
     * recover gracefully.
     * Implement rate limiting: The code currently does not implement any rate
     * limiting mechanisms, which could potentially allow an attacker to perform a
     * brute force attack on the AD server. You could consider implementing rate
     * limiting mechanisms such as sleeping between authentication attempts or
     * limiting the number of authentication attempts per user.
     * Use a more secure algorithm for searching the AD: The code currently uses the
     * `objectClass=*` search filter, which can be inefficient and may return a
     * large number of results. You could consider using a more specific search
     * filter such as `(&(objectClass=user)(sAMAccountName=*))`, which only return user objects
     * reduce the amount of data transferred over the network
     * Use a more secure algorithm for binding to the AD: The code currently uses
     * the `bind` method to authenticate the user, which can be vulnerable to replay
     * attacks. You could consider using a more secure algorithm such as the
     * `saslBind` method, which supports SASL (Simple Authentication and Security
     * Layer) and is less vulnerable to replay attacks.
     * Use a more secure algorithm for retrieving user information: The code
     * currently uses the `search` method to retrieve user information, which can be
     * inefficient and may return a large amount of data. You could consider using a
     * more specific method such as `getAttribute` or `getAttributes` to retrieve
     * only the specific user information that you need.
     * Use a more secure algorithm for storing user information: The code currently
     * stores user information in a `Map`, which is not secure. You could consider
     * using a more secure storage mechanism such as a secure database or a secure
     * file storage system to protect the user's information.
     */

    private static String ldapURI;
    private static String[] requiredAttributes;
    private static String[] ADSearchPaths;
    private static String accountSuffex;
    private final static String contextFactory = "com.sun.jndi.ldap.LdapCtxFactory";
    // private final static String ldapURI =
    // "ldap://52.40.50.72:389/DC=corp,DC=local";
    // private static String[] requiredAttributes = {
    // "cn","givenName","sn","userPrincipalName","sAMAccountName","objectSid" };
    // see you active directory user OU's hierarchy
    // private static String[] ADSearchPaths = {
    // "OU=O365 Synced Accounts,OU=ALL USERS",
    // "OU=Users,OU=O365 Synced Accounts,OU=ALL USERS",
    // "OU=In-House,OU=Users,OU=O365 Synced Accounts,OU=ALL USERS",
    // "OU=Migrated Users
    // };
    // private static String accountSuffex = "@corp.local"; // this will be used if
    // user name is just provided

    public void readFromPropertiesFile() {
        try (InputStream inputStream = AuthenticateAD.class.getClassLoader().getResourceAsStream("ad.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);
            ldapURI = properties.getProperty("ldapURI");
            requiredAttributes = properties.getProperty("requiredAttributes").split(",");
            ADSearchPaths = properties.getProperty("ADSearchPaths").split(";");
            accountSuffex = properties.getProperty("accountSuffex");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void authenticateUserAndGetInfo(String user, String password) throws Exception {
        try {
            Hashtable<String, String> env = new Hashtable<String, String>();
            env.put(Context.INITIAL_CONTEXT_FACTORY, contextFactory);
            env.put(Context.PROVIDER_URL, ldapURI);
            env.put(Context.SECURITY_AUTHENTICATION, "simple");
            env.put(Context.SECURITY_PRINCIPAL, user);
            env.put(Context.SECURITY_CREDENTIALS, password);

            DirContext ctx = new InitialDirContext(env);

            String filter = "(sAMAccountName=" + user + ")"; // default for search filter username

            if (user.contains("@")) // if user name is a email then
            {
                // String parts[] = user.split("\\@");
                // use different filter for email
                filter = "(userPrincipalName=" + user + ")";
            }

            SearchControls ctrl = new SearchControls();
            ctrl.setSearchScope(SearchControls.SUBTREE_SCOPE);
            ctrl.setReturningAttributes(requiredAttributes);

            NamingEnumeration userInfo = null;

            Integer i = 0;
            do {
                userInfo = ctx.search(ADSearchPaths[i], filter, ctrl);
                i++;

            } while (!userInfo.hasMore() && i < ADSearchPaths.length);

            if (userInfo.hasMore()) {

                SearchResult UserDetails = (SearchResult) userInfo.next();
                Attributes userAttr = UserDetails.getAttributes();
                System.out.println("adEmail = " + userAttr.get("userPrincipalName").get(0).toString());
                System.out.println("adFirstName = " + userAttr.get("givenName").get(0).toString());
                System.out.println("adLastName = " + userAttr.get("sn").get(0).toString());
                System.out.println("name = " + userAttr.get("cn").get(0).toString());
                System.out.println("AdFullName = " + userAttr.get("cn").get(0).toString());
            }

            userInfo.close();

        } catch (javax.naming.AuthenticationException e) {

        }
    }
}