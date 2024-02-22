/**
 * Class AttendanceBean number of objects to create
 * initializes static class variables and generates a random attendance 6 digits code
 */

package bean;

import java.util.ArrayList;
import java.util.List;

public class AttendanceBean {

    private String attendanceCode;
    private String selectedUser;
    private String className;
    private List<String> classes;
    private String randomCode;

    public void init() {
        // invokes service class to populate classes
        classes = new ArrayList<String>();
        classes.add("Micro Services Architecture");
        classes.add("Service Science");
        classes.add("Service Based Design");
        classes.add("Cloud Computing");
        classes.add("Service Deployment");

        // for(int i = 0; i < 10; i++) {
        // classes.add("Class " + i);
        // }
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<String> getClasses() {
        return classes;
    }

    public void setClasses(List<String> classes) {
        this.classes = classes;
    }

    public String getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(String selectedUser) {
        this.selectedUser = selectedUser;
    }

    public String getAttendanceCode() {
        return attendanceCode;
    }

    public void setAttendanceCode(String attendanceCode) {
        this.attendanceCode = attendanceCode;
    }

    public String getRandomCode() {
        return randomCode;
    }

    public void setRandomCode(String randomCode) {
        this.randomCode = randomCode;
    }
}
// End of AttendanceBean.java