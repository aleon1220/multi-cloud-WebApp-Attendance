/*-----------------------------------com.leo.json.Authorization.java----------------------------------- */

package testing.gson;

import com.google.gson.annotations.SerializedName;

import entity.ChallengeParameters;

public class Authorization {
	
	//names have to be the same from JSON to Java Object so that it works fine.

	private ChallengeParameters challengeParameters;
	// @SerializedName("AuthenticationResult")
	// @Expose
	private AuthenticationResult authenticationResult;

	public ChallengeParameters getChallengeParameters() {
		return challengeParameters;
	}

	public void setChallengeParameters(ChallengeParameters challengeParameters) {
		this.challengeParameters = challengeParameters;
	}

	public AuthenticationResult getAuthenticationResult() {
		return authenticationResult;
	}

	public void setAuthenticationResult(AuthenticationResult authenticationResult) {
		this.authenticationResult = authenticationResult;
	}

}