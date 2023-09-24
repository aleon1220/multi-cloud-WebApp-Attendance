package external.services.wsdlclient;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import service.MainService;
import util.AuthenticateToken;

public class ServiceInvokationTest {

    MainService testService = new MainService();
    AuthenticateToken attend = new AuthenticateToken();

    @Test
    public void testWSDLSuccess() {

        assertTrue(testService.invokeWSDLForIP().length() > 2);
        // System.out.println(testService.invokeWSDLForIP());
    }

    @Test
    public void testRestBibleSuccess() {
        assertTrue(testService.invokeRestForBibleVerse().length() > 2);
        // System.out.println(testService.invokeRestForBibleVerse());
    }
}
