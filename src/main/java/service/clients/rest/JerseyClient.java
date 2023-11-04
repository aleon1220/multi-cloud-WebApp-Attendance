package service.clients.rest;

import java.io.IOException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import org.apache.http.client.ClientProtocolException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class JerseyClient {

    /**
     * @param args
     * @throws ClientProtocolException
     * @throws IOException
     */

    public void obtainJSONResponse() throws ClientProtocolException, IOException {

        // Invoke REST service with GET Method
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);

        WebResource service = client
                .resource(UriBuilder.fromUri("https://ctpoixww04.execute-api.us-east-1.amazonaws.com").build());

        // getting JSON data
        System.out.println("With JSON" + service.path("dev").path("session").accept(MediaType.APPLICATION_JSON).header(
                "Authorization",
                "eyJraWQiOiJKQWJRaHNlTTVldFRBMk1vMlpoNGs5Mlp2ZmFHaU5zWWtVQlFNbG1cL1Mybz0iLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiI2MWI5ZTI4NC1kMzVhLTQ0M2YtOTlhNy04MGY0OWE4YTI4NmQiLCJhdWQiOiIxZTh0ZWZtbGNsMmgyOTB2bmg5cmNlZm52ciIsImNvZ25pdG86Z3JvdXBzIjpbImFkbWluaXN0cmF0b3IiXSwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJ0b2tlbl91c2UiOiJpZCIsImF1dGhfdGltZSI6MTUwNTAyMTU0OSwiaXNzIjoiaHR0cHM6XC9cL2NvZ25pdG8taWRwLnVzLWVhc3QtMS5hbWF6b25hd3MuY29tXC91cy1lYXN0LTFfMEhkMUtvTTVhIiwiY29nbml0bzp1c2VybmFtZSI6IjEwMjk1NzY1IiwiZXhwIjoxNTA1MDI1MTQ5LCJpYXQiOjE1MDUwMjE1NDksImVtYWlsIjoiam9uYXRoYW4udEB4dHJhLmNvLm56In0.pL9EmmsbB-pxTCXRmDmAAnfMBhLgEY39tbNf0LMhHWF_sSFTB6gYELxnfNa3ITMEEV_wwgvJ-9USb3nHh1G8JkNDxDVCS0S0q4U81GZDqA8USGzA4mklTg1qROlHQkKFipaKQia8U9oLZSIGp6HrpvtQN7wOm9S_0Z5ePXN2REgYsWKobfkLI3dHICACt0RANRBLlXNlQC1F8Hj7dl7UbeQkCZqgj_J_GcxHHLHBRRERO4Koz09gYBhpVrWZZlbUJpwAk3OVCesj7Wz73xjpE2ckm9x6SLkzxC8W5FLvyH5rbiD7Dif9xIVB6jV1pz9GIwMGqzcSWT0LIujw90hoNg")
                .get(String.class));
    }

    public void obtainXMLResponse() throws ClientProtocolException, IOException {
        // getting XML data
        // System.out.println("with
        // XML"+service.path("dev").path("session").accept(MediaType.APPLICATION_XML).get(String.class));
    }
}