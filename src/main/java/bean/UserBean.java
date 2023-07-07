/**
 * Class UserBean performs specialized login
 * contains attributes of user object and performs login calling service objects and functionalities
 */

package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.google.gson.annotations.Expose;

import service.UserService;

@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean {

    @Expose
    private String id;
    @Expose
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String token;
    private String returnMsg;
    private String accessToken;
    private String expiresIn;
    private String idToken;
    private String refreshToken;
    private String tokenType;

    // Login Method
    public String login() {
        try {
            UserService userService = new UserService();
            // System.out.println("userBean: print the name to see if it got it: "+getId());
            String IdToken = userService.obtainIdToken(getId(), getPassword());
            // System.out.println("IdToken in UserBean: " + IdToken);
            // System.out.println("UserBean checking value of response before if");

            if (userService.createSession(IdToken).equals("ok")) {

                setAccessToken(userService.getAccessToken());
                setExpiresIn(userService.getExpiresIn().toString());
                setIdToken(userService.getIdToken());
                setRefreshToken(userService.getRefreshToken());
                setTokenType(userService.getTokenType());

                return "main";

            } else {

                return "loginError";
            }

        } catch (Exception e) {
            this.setReturnMsg(e.getMessage());
            e.printStackTrace();
            System.out.println("UserBean. inside exception catch.");
            return "loginError";
        }
    }// end of login method

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public UserBean() {
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public String getToken() {
        return token;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(String username) {
        this.id = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}// End of UserBean.java
