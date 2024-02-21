   /**
    * Class Authorization helps in creation of JSON to java object
    * creates new String objects
    */

package entity;

public class Authorization {
    // names have to be the same from JSON to Java Object so that it works fine.
    // @SerializedName("AuthenticationResult")
    // @Expose
    private AuthenticationResult authenticationResult;

    public AuthenticationResult getAuthenticationResult() {
        return authenticationResult;
    }

    public void setAuthenticationResult(AuthenticationResult authenticationResult) {
        this.authenticationResult = authenticationResult;
    }
}