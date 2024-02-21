/**
 * Class AttendanceRegister
 * sends invocation to webservice for confirmation of attendance. Includes prime faces UI functionality.
 */

package util;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MultivaluedHashMap;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.UriBuilderException;
import com.google.gson.JsonObject;

import service.UserService;

public class AuthenticateToken {

    private String inputCode;
    private Integer progress;

    /**
     * @return XHTML page
     */
    public String confirmAttendance() {
        var replyFromMS = "03-attendanceOK";
        UserService tok = new UserService();
        String token = tok.obtainIdToken("10295765", "Value!12");
        // 1-open connection and send user and password as a POST method
        try {
            Client config = ClientBuilder.newClient();
            // call microservice functionality to register attendance
            // Client client = Client.create(config);
            // WebResult webResource =
            // client.resource(UriBuilder.fromUri("https://xgdeevdwh1.execute-api.us-east-1.amazonaws.com").path("addAttendance").build());
            // Params {"studentId": "246810","paperId": "COMP101","status": "present"}

            MultivaluedMap<String, String> formData = new MultivaluedHashMap<>();
            formData.add("studentId", "246810");
            formData.add("paperId", "COMP101");
            formData.add("status", "present");

            JsonObject jsonPayLoad = new JsonObject();
            jsonPayLoad.addProperty("studentId", "246810");
            jsonPayLoad.addProperty("paperId", "COMP101");
            jsonPayLoad.addProperty("status", "present");

            // send JSON Payload to MicroService

            System.out.println("Attendance Register Response");
        } catch (IllegalArgumentException | UriBuilderException e) {
            e.printStackTrace();
        }

        // invoke WS to confirm Attendance and validate
        System.out.println("Java Faces display message in the browser Attendance Registered!!!");

        if (replyFromMS.isEmpty()) {
            return "404-attendanceError";
        } else {
            return "03-attendanceOK";
        }
    }

    public Integer getProgress() {
        if (progress == null) {
            progress = 0;
        } else {
            progress = progress + (int) (Math.random() * 35);

            if (progress > 100)
                progress = 100;
        }
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public void onComplete() {
        System.out.println("Time out!!!");
    }

    public String onTimeout() {
        System.out.println("Timeout Try again!!");
        return "index";
    }

    public void cancel() {
        progress = null;
    }

    public String getInputCode() {
        return inputCode;
    }

    public void setInputCode(String inputCode) {
        this.inputCode = inputCode;
    }
}