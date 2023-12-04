// utility class involved with business logid
// authenticate LDAP UserName or Email call LdapAuth.authenticateUserAndGetInfo (username,password);
// Note: Configure ldapURI ,requiredAttributes ,ADSearchPaths,accountSuffex 

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import javax.naming.*;

import java.util.regex.*;

import javax.naming.directory.*;

import javax.naming.ldap.InitialLdapContext;

import javax.naming.ldap.LdapContext;

public class AuthenticateLDAP {

    private static String ldapURI;
    private static String[] requiredAttributes;
    private static String[] ADSearchPaths;
    private static String accountSuffex;
    private final static String contextFactory = "com.sun.jndi.ldap.LdapCtxFactory";
    // private final static String ldapURI = "ldap://52.40.50.72:389/DC=corp,DC=local";
    
    // private static String[] requiredAttributes = { "cn", "givenName", "sn", "userPrincipalName", "sAMAccountName", "objectSid" };
    // see you active directory user OU's hierarchy
    // private static String[] ADSearchPaths =
    //         {
    //         "OU=O365 Synced Accounts,OU=ALL USERS",
    //         "OU=Users,OU=O365 Synced Accounts,OU=ALL USERS",
    //         "OU=In-House,OU=Users,OU=O365 Synced Accounts,OU=ALL USERS",
    //         "OU=Torbram Users,OU=Users,OU=O365 Synced Accounts,OU=ALL USERS",
    //         "OU=Migrated Users,OU=TES-Users"
    //         };

    // private static String accountSuffex = "@corp.local"; // this will be used if
    // user name is just provided

    static {
        try ((InputStream inputStream = LdapAuth.class.getClassLoader().getResourceAsStream("ldap.properties")) {

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