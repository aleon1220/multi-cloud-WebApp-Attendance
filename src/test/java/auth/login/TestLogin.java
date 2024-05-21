package auth.login;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import bean.UserBean;
import service.AuthenticationService;

public class TestLogin {

    @Test
    public void GetToken() {
        // fail("Not yet implemented");
    }

    @Test
    @Inject
    public void loginGeneralUser() {
        AuthenticationService authService = new AuthenticationService();
        UserBean user = new UserBean();
        user.setId("10295765");
        user.setPassword("Value!12");
        // System.out.println("assert result"+user.login());
        assertSame("home", authService.login("demo.user@testing.com", user.getPassword()));
    }

    public void loginLecturer() {
    }
} // End of TestLogin