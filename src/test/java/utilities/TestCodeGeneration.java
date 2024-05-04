package utilities;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import bean.AttendanceBean;

public class TestCodeGeneration {

    @Test
    public void testRandomCode() {
        AttendanceBean at = new AttendanceBean();
        assertNotNull("data", at.getAttendanceCode());
    } // testRandomCode()
}
