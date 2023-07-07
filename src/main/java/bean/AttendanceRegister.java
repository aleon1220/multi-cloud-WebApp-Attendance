/**
 * Class AttendanceRegister
 * sends invocation to webservice for confirmation of attendance. Includes prime faces UI functionality.
 */

package bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;

import com.google.gson.JsonObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import service.UserService;

@ManagedBean
public class AttendanceRegister {

    private String inputCode;
    private Integer progress;

    public String confirmAttendance() {

        String replyFromMS = null;

        UserService tok = new UserService();
        String token = tok.obtainIdToken("10295765", "Value!12");
        // 1-open connection and send user and password as a POST method
        ClientConfig config = new DefaultClientConfig();

        Client client = Client.create(config);
        WebResource webResource = client.resource(UriBuilder
                .fromUri("https://xgdeevdwh1.execute-api.us-east-1.amazonaws.com").path("addAttendance").build());
        // Passing parameters
        // {"studentId": "246810","paperId": "COMP101","status": "present"}

        MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
        formData.add("studentId", "246810");
        formData.add("paperId", "COMP101");
        formData.add("status", "present");

        JsonObject jsonPayLoad = new JsonObject();
        jsonPayLoad.addProperty("studentId", "246810");
        jsonPayLoad.addProperty("paperId", "COMP101");
        jsonPayLoad.addProperty("status", "present");

        // ClientResponse response =
        // webResource.accept(MediaType.APPLICATION_JSON).header("Authorization",
        // token).post(ClientResponse.class, jsonPayLoad.toString());
        ClientResponse response = webResource.header("Authorization", token).post(ClientResponse.class,
                jsonPayLoad.toString());
        replyFromMS = response.getEntity(String.class);
        // String replyFromMS =
        // webResource.path("addAttendance").accept(MediaType.APPLICATION_JSON).header("Authorization",
        // token).post(String.class, jsonPayLoad.toString());

        System.out.println("AttendanceRegisterResponse:" + response);

        // invoke WS to confirm Attendance and validate
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CORRECT", "Attendance Registered!!!"));

        if (replyFromMS.isEmpty()) {
            return "attendanceNOK.xhtml";
        } else {
            return "attendanceOK.xhtml";
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
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Time Ended"));
    }

    public String onTimeout() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Time Out"));
        return "main.xhtml";
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