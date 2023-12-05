package attendance.student;

import com.google.gson.annotations.SerializedName;

import entity.ChallengeParameters;

public class StudentAuthorization {

    // names have to be the same from JSON to Java Object so that it works fine.

    private ChallengeParameters challengeParameters;
    // @SerializedName("AuthenticationResult")
    // @Expose
    private StudentAuthenticationResult authenticationResult;

    public ChallengeParameters getChallengeParameters() {
        return challengeParameters;
    }

    public void setChallengeParameters(ChallengeParameters challengeParameters) {
        this.challengeParameters = challengeParameters;
    }

    public StudentAuthenticationResult getAuthenticationResult() {
        return authenticationResult;
    }

    public void setAuthenticationResult(StudentAuthenticationResult authenticationResult) {
        this.authenticationResult = authenticationResult;
    }
}