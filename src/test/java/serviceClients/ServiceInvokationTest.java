package serviceClients;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import bean.AttendanceRegister;
import service.MainService;

public class ServiceInvokationTest {

    MainService testService = new MainService();
    AttendanceRegister attend = new AttendanceRegister();

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
