package testing.login;

import static org.junit.Assert.*;

import org.junit.Test;

import bean.UserBean;

public class LoginTest {

	@Test
	public void testLogin() {
		UserBean user = new UserBean();
		user.setId("10295765");
		user.setPassword("Value!12");
		//System.out.println("assert result"+user.login());
		assertTrue(user.login()=="main");
	}

	@Test
	public void testGetToken() {
		//fail("Not yet implemented");
	}
}
