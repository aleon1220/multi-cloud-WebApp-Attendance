/**
 * Class AttendanceBean number of objects to create
 * initializes static class variables and generates a random attendance 6 digits code
 */

package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named
@ViewScoped
public class AttendanceBean implements Serializable{

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

    public void button1Action() {
        // Implement logic for button 1
        System.out.println("Button 1 has been clicked!");
    }

    public void button2Action() {
        // Implement logic for button 2
        System.out.println("Button 2 clicked!");
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((attendanceCode == null) ? 0 : attendanceCode.hashCode());
        result = prime * result + ((selectedUser == null) ? 0 : selectedUser.hashCode());
        result = prime * result + ((className == null) ? 0 : className.hashCode());
        result = prime * result + ((classes == null) ? 0 : classes.hashCode());
        result = prime * result + ((randomCode == null) ? 0 : randomCode.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AttendanceBean other = (AttendanceBean) obj;
        if (attendanceCode == null) {
            if (other.attendanceCode != null)
                return false;
        } else if (!attendanceCode.equals(other.attendanceCode))
            return false;
        if (selectedUser == null) {
            if (other.selectedUser != null)
                return false;
        } else if (!selectedUser.equals(other.selectedUser))
            return false;
        if (className == null) {
            if (other.className != null)
                return false;
        } else if (!className.equals(other.className))
            return false;
        if (classes == null) {
            if (other.classes != null)
                return false;
        } else if (!classes.equals(other.classes))
            return false;
        if (randomCode == null) {
            if (other.randomCode != null)
                return false;
        } else if (!randomCode.equals(other.randomCode))
            return false;
        return true;
    }
}
// End of AttendanceBean.java