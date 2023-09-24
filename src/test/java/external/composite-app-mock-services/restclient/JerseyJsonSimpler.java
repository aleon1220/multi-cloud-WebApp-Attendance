package external.services.restclient;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.representation.Form;

public class JerseyJsonSimpler {

    public static void main(String[] args) {

        try {
            Client client = (Client) ClientBuilder.newClient();
            WebTarget target = ((javax.ws.rs.client.Client) client).target("http://localhost:9998").path("resource");

            Form form = new Form();
            form.add("id", "10295765");
            form.add("password", "Value!12");
            // ClientResponse response =
            // WebResource.class.type(MediaType.TEXT_PLAIN).post(ClientResponse.class,
            // form);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}