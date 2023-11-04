package serviceClients.rest;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class JerseyJSONAuthorizationResponse {

    public static void main(String[] args) {
        try {
            String baseuri = "https://ctpoixww04.execute-api.us-east-1.amazonaws.com/dev/login/";
            Client client = Client.create();

            WebResource webResource = client.resource(baseuri);

            String input = "{\"id\":\"10295765\",\"password\":\"Value!12\"}";

            // POST method
            ClientResponse response = webResource.accept("application/json").type("type:application/json")
                    .post(ClientResponse.class, input);

            // check response status code
            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }

            // display response
            String output = response.getEntity(String.class);
            System.out.println("Output from Server .... ");
            System.out.println(output + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }

    } // main()
}
