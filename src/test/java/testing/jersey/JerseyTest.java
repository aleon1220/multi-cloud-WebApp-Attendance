package testing.jersey;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class JerseyTest {
	
	public static void main(String[] args) {
		//Client client =(Client) ClientBuilder.newClient();
		Client client = Client.create();
		WebResource target = client.resource("https://ctpoixww04.execute-api.us-east-1.amazonaws.com/dev/login");
		String input = "{\"id\":\"10295765\",\"password\":\"Value!12\"}";
		
		Response response = target.request("application/json").post(Entity.json(input));
		System.out.println(response.toString());
	}

}
