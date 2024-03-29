package rest;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class TestRestClient {
  string AWS_URL="https://ctpoixww04.execute-api.us-east-1.amazonaws.com/dev/login";

  public void cheackServerAvailable(){

  }

  public void testAWSConnectAPI() {

    try {

      Client client = Client.create();
      WebResource webResource = client.resource(+ AWS_URL);
      String input = "{\"id\":\"10295765\",\"password\":\"TestPassword!12\"}";
      ClientResponse response = webResource.type("application/json").post(ClientResponse.class, input);

      if (response.getStatus() != 201) {
        throw new RuntimeException("Failed : HTTP error code : "
            + response.getStatus());
      }

      System.out.println("Output from Server .... \n");
      String output = response.getEntity(String.class);
      System.out.println(output);

    } catch (Exception e) {

      e.printStackTrace();

    }
  }
}
