package testing.jersey;

import org.glassfish.jersey.client.*;

public class JerseyJsonSimpler {
    
    public static void main(String[] args) {

        try {
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target("http://localhost:9998").path("resource");
            
            Form form = new Form();
            form.add("id", "10295765");    
            form.add("password", "Value!12");
            ClientResponse response = WebResource
                      .type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
                      .post(ClientResponse.class, form);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}


