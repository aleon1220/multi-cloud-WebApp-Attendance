   /**
    * Class LoginReply uses gson to create the java object based on JSON
    * builds the java object and overrides a toString method for testing purposes
    */

package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginReply {
    @SerializedName("AuthenticationResult")
    @Expose
    private AuthenticationResult authenticationResult;

    public AuthenticationResult getAuthenticationResult() {
    return authenticationResult;
    }

    public void setAuthenticationResult(AuthenticationResult authenticationResult) {
    this.authenticationResult = authenticationResult;
    }
    
    @Override
      public String toString() {
        //return getClass()+"challenge"+challengeParameters+"data in authent"+authenticationResult.getIdToken();
        return getClass()+" Overriden method to fetch extra info";
      }
}
