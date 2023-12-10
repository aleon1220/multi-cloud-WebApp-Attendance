package util;

import com.unboundid.ldap.sdk.LDAPConnection;
import com.unboundid.ldap.sdk.LDAPException;
import com.unboundid.ldap.sdk.SearchResult;
import com.unboundid.ldap.sdk.SearchResultEntry;
import com.unboundid.ldap.sdk.SearchScope;

public class AuthenticateOpenLDAP {
    public void openLDAPAdminSearch() {
        // LDAP connection parameters
        String ldapURL = "ldap://localhost";
        String ldapHost = "localhost";
        String bindDN = "cn=admin,dc=my-company,dc=com";
        String bindPassword = System.getenv("LDAP_ADMIN_PASS");
        String baseDN = "dc=my-company,dc=com";
        String searchFilter = "(objectClass=*)"; // Modify this filter as needed

        // Connect to the LDAP server
        LDAPConnection connection = null;
        try {
            // If using SSL/TLS, initialize SSLSocketFactory (optional)
            // SSLUtil sslUtil = new SSLUtil(new TrustAllTrustManager());
            // SSLSocketFactory socketFactory = sslUtil.createSSLSocketFactory();
            // connection = new LDAPConnection(socketFactory, "localhost", 636);

            // For non-SSL connection
            connection = new LDAPConnection(ldapHost, 389);
            connection.bind(bindDN, bindPassword);

            // Perform the search
            SearchResult searchResult = connection.search(baseDN, SearchScope.SUB, searchFilter);

            // Process the search results
            for (SearchResultEntry entry : searchResult.getSearchEntries()) {
                System.out.println(entry.toLDIFString());
            }
        } catch (LDAPException e) {
            e.printStackTrace();
        } finally {
            // Disconnect from the LDAP server
            if (connection != null) {
                connection.close();
            }
        }
    }
}