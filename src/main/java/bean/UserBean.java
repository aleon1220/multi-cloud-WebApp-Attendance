/**
 * Class UserBean performs specialized login
 * contains attributes of user object and performs login calling service objects and functionalities
 */

package bean;

import service.UserService;

public class UserBean {
    private String id;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String token;
    private String accessToken;
    private String expiresIn;
    private String idToken;
    private String refreshToken;
    private String tokenType;
    private String returnMsg;

    public UserBean() {
        super();
        setId("userID123");
    }

    public String loginToken() {
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
                return "06-Reports";
            } else {

                return "404-loginError";
            }

        } catch (Exception e) {
            this.setReturnMsg(e.getMessage());
            e.printStackTrace();
            System.out.println("UserBean. inside exception catch");
            return "loginError";
        }
    }// end of loginToken method

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

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
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

    public String getPassword() {
        return password;
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
}
// End of Class UserBean.java