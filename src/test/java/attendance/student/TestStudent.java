package attendance.student;

import javax.faces.bean.ManagedBean;

import org.junit.jupiter.api.Test;

public class TestStudent {

    private String name;

    @Test
    public String sayHello() {
        if (name != null && !name.trim().equals("")) {
            return "Hello, " + name + "!";
        } else {
            return null;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}