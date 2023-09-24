package external.services.restclient;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.representation.Form;

public class JerseyPostHeader {

    Client client = (Client) ClientBuilder.newClient();
    WebTarget target = ((javax.ws.rs.client.Client) client).target("http://localhost:9998").path("resource");

    Form form = new Form();
    // form.param("x", "foo");
    // form.param("y", "bar");

    // String bean =
    // target.request(MediaType.APPLICATION_JSON_TYPE).post(MediaType.APPLICATION_FORM_URLENCODED_TYPE,
    // String.class);
}
