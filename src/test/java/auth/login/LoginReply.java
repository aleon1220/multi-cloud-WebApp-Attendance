package auth.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import entity.AuthenticationResult;

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
    // return getClass()+"challenge"+challengeParameters+"data in
    // authent"+authenticationResult.getIdToken();
    return getClass() + "JSON Overriding functionality";
  }
}
