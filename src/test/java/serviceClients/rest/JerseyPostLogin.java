package serviceClients.rest;

import java.io.IOException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;
import org.apache.http.client.ClientProtocolException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class JerseyPostLogin {

    public static void main(String[] args) throws ClientProtocolException, IOException {

        ClientConfig config = new DefaultClientConfig();

        Client client = Client.create(config);

        WebResource webResource = client
                .resource(UriBuilder.fromUri("https://ctpoixww04.execute-api.us-east-1.amazonaws.com/").build());

        MultivaluedMap<String, String> formData = new MultivaluedMapImpl();

        formData.add("id", "10295765");
        formData.add("password", "Value!12");

        ClientResponse response = webResource.path("dev").path("login").type(MediaType.APPLICATION_FORM_URLENCODED)
                .post(ClientResponse.class, formData);

        System.out.println("Response " + response.getEntity(String.class));
    }
}
