package attendance.login;

import static org.junit.Assert.*;

import org.junit.Test;

import bean.UserBean;

public class TestLogin {

    @Test
    public void GetToken() {
        // fail("Not yet implemented");
    }

    @Test
    public void loginGeneralUser() {
        UserBean user = new UserBean();
        user.setId("10295765");
        user.setPassword("Value!12");
        // System.out.println("assert result"+user.login());
        assertTrue(user.login() == "main");
    }

    public void loginGeneralUserNOK() {
    }

    public void loginLecturer() {
    }

}