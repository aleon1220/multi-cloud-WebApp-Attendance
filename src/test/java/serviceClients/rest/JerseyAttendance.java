package serviceClients.rest;

import bean.AttendanceRegister;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class JerseyTest {

    public static void main(String[] args) {
        testAttendanceRegister();
        // responseREST();
    }

    String responseREST() {
        // Client client =(Client) ClientBuilder.newClient();
        Client client = Client.create();
        WebResource target = client.resource("https://ctpoixww04.execute-api.us-east-1.amazonaws.com/dev/login");
        String input = "{\"id\":\"10295765\",\"password\":\"Value!12\"}";

        Response response = ((WebTarget) target).request("application/json").post(Entity.json(input));
        // System.out.println(response.toString());

        return response.toString();
    }

    static void testAttendanceRegister() {
        AttendanceRegister att = new AttendanceRegister();
        att.confirmAttendance();
    }

}
