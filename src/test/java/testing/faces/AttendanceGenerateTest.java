package testing.faces;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import bean.AttendanceBean;
import bean.UserBean;

public class AttendanceGenerateTest {

    @Test
    public void testRandomCode() {
        AttendanceBean at = new AttendanceBean();

        assertNotNull("data", at.generateAttendanceCode());
    }

}
