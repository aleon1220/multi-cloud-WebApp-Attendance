package ui;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import service.AttendanceService;

public class AttendanceGenerateTest {

    @Test
    public void testRandomCode() {
        AttendanceService attendanceSvc = new AttendanceService();
        assertNotNull("data", attendanceSvc.generateAttendanceCode());
    } // testRandomCode()
}
