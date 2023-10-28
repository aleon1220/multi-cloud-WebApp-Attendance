import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import bean.AttendanceBean;

public class AttendanceGenerateTest {

    @Test
    public void testRandomCode() {
        AttendanceBean at = new AttendanceBean();
        assertNotNull("data", at.generateAttendanceCode());
    } // testRandomCode()
}
