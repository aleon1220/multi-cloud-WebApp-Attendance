package auth.ldap;

import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.assertTrue;
import com.unboundid.ldap.sdk.LDAPConnection;
import com.unboundid.ldap.sdk.LDAPException;
import com.unboundid.ldap.sdk.SearchRequest;
import com.unboundid.ldap.sdk.SearchResult;
import com.unboundid.ldap.sdk.SearchResultEntry;
import com.unboundid.ldap.sdk.SearchScope;

import util.AuthenticateOpenLDAP;

public class TestLDAPAuthentication {
    // - Mocks LDAPConnection to avoid real connection
    // - Mocks search result and entries returned
    // - Stubs the behavior for the mocks
    // - Calls the method under test
    // - Verifies expected methods are called on the mocks

    @Test
    public void testMockOpenLDAPAdminSearch() {
        // Mock LDAPConnection
        LDAPConnection connectionMock = Mockito.mock(LDAPConnection.class);

        // Mock search result
        SearchResult searchResultMock = Mockito.mock(SearchResult.class);
        Mockito.when(connectionMock.search(Mockito.anyString(), Mockito.any(), Mockito.anyString()))
                .thenReturn(searchResultMock);

        // Mock search result entries
        SearchResultEntry entry1 = Mockito.mock(SearchResultEntry.class);
        SearchResultEntry entry2 = Mockito.mock(SearchResultEntry.class);

        // Stub searchEntries to return mocked entries
        Mockito.when(searchResultMock.getSearchEntries()).thenReturn(new SearchResultEntry[] { entry1, entry2 });

        // Test the method
        AuthenticateOpenLDAP ldapAuth = new AuthenticateOpenLDAP();
        ldapAuth.openLDAPAdminSearch();

        // Verify search was called
        Mockito.verify(connectionMock).search(Mockito.anyString(), Mockito.any(), Mockito.anyString());

        // Verify entry processing
        Mockito.verify(entry1).toLDIFString();
        Mockito.verify(entry2).toLDIFString();

        // Verify connection closed
        Mockito.verify(connectionMock).close();
    }

    @Test
    public void testOpenLDAPAdminSearch_withOpenLDAP() {

        // Test parameters
        String ldapURL = "ldap://localhost";
        String ldapHost = "localhost";
        int ldapPort = 389;
        String bindDN = "cn=admin,dc=my-company,dc=com";
        var bindPassword = "adminpassword";
        String baseDN = "dc=my-company,dc=com";
        String searchFilter = "(objectClass=*)";      

        // Connect to OpenLDAP
        // Establishing a connection to the LDAP server
        try (LDAPConnection connection = new LDAPConnection(ldapHost, ldapPort, bindDN, bindPassword)) {
            System.out.println("Connected to the LDAP server.");

            SearchRequest searchRequest = new SearchRequest(baseDN, SearchScope.SUB, searchFilter);
            // Performing the search
            SearchResult result = connection.search(baseDN, SearchScope.SUB, "(objectClass=*)");

            // Verify search returned results
            assertTrue(result.getEntryCount() > 0);

            // Print entries
            for (SearchResultEntry entry : result.getSearchEntries()) {
                System.out.println(entry.toLDIFString());
            }

            SearchResult searchResult = connection.search(searchRequest);
            System.out.println("Search returned " + searchResult.getEntryCount() + " entries.");

            // Process the search results as needed
            // Close connection
            connection.close();
        } catch (LDAPException e) {
            System.err.println("Error connecting to the LDAP server: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
