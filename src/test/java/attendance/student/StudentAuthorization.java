package attendance.student;

import com.google.gson.annotations.SerializedName;

public class StudentAuthorization {
    // names must be the same from JSON to Java Object so that it works fine.
    @SerializedName("AuthenticationResult")
    private StudentAuthenticationResult authenticationResult;

    public StudentAuthenticationResult getAuthenticationResult() {
        return authenticationResult;
    }

    public void setAuthenticationResult(StudentAuthenticationResult authenticationResult) {
        this.authenticationResult = authenticationResult;
    }
}