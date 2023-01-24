/**
 * Class AttendanceBean number of objects to create
 * initializes static class variables and generates a random attendance 6 digits code
 */

package bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ManagedBean(name = "attendanceGenerate")
@SessionScoped
public class AttendanceBean {

    private String attendanceCode;
    private String selectedUser;
    private String className;
    private List<String> classes;
    private String randomCode;

    @PostConstruct
    public void init() {
        // invokes service class to populate classes
        classes = new ArrayList<String>();
        classes.add("SOA");
        classes.add("Service Science");
        classes.add("Service Based Design");
        classes.add("Cloud Computing");
        classes.add("Service Deployment");

        // for(int i = 0; i < 10; i++) {
        // classes.add("Class " + i);
        // }
    }

    public String generateAttendanceCode() {

        int[] numbers = new int[6];
        // Generates 10 Random Numbers in the range 1 - 20
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = (int) (Math.random() * 9);
        } // end for loop
        System.out.println("Numbers Generated: " + Arrays.toString(numbers).replace(",", " -"));
        setRandomCode(Arrays.toString(numbers));
        // Arrays.toString(numbers).replace(",", "");

        return Arrays.toString(numbers).replace(",", " -");
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

    public void buttonAction(ActionEvent actionEvent) {

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
/*
 * End of AttendanceBean.java
 */
