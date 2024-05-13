/**
 * Class UserService makes request to the microservices for authentication.
 * obtainIdToken and then DeserializeJSON2Object in java object, creates a session with the token.
 * obtains a JSON object using Gson getJsonString();
 */

package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import jakarta.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import bean.UserBean;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
public class UserService {
    UserBean user = new UserBean();
    private String accessToken;
    private String expiresIn;
    private String idToken;
    private String refreshToken;
    private String tokenType;

    public String obtainIdToken(String id, String password) {

        String replyFromMS = null;

        try {
            // 1-open connection and send user and password as a POST method
            URL url = new URL("https://ctpoixww04.execute-api.us-east-1.amazonaws.com/dev/login");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            // Passing parameters
            //String input = "{\"id\":\"10295765\",\"password\":\"Value!12\"}";

            JsonObject user_auth = new JsonObject();
            user_auth.addProperty("id", id);
            user_auth.addProperty("password", password);

            //System.out.println("UserService: Gson created object " + user_auth);

            OutputStream os = conn.getOutputStream();
            os.write(user_auth.toString().getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String outputFromAmazon;
            //System.out.println("Output from amazon Server MicroService.... \n");

            while ((outputFromAmazon = br.readLine()) != null) {
                //System.out.println(outputFromAmazon);

                replyFromMS = outputFromAmazon;
                //System.out.println("UserService: replyform MS "+replyFromMS);
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        String IdToken = DeserializeJSON2Object(replyFromMS);
        //System.out.println("UserService: about to return token");
        return IdToken;
    }
    
    public String DeserializeJSON2Object(String receivedJSON) {
        // 2-transform the JSON Response to Object, adding JSON Response to a Java Object
        System.out.println(receivedJSON);
        System.out.println((this.getUserBeanAsJson(user)));
        return receivedJSON;
    }
    
    public String createSession(String token) {
        //Create session method is a GET Rest invocation
        var json_token = token;
        Client configClient = ClientBuilder.newClient();
        var aws_api_uri = "https://ctpoixww04.execute-api.us-east-1.amazonaws.com";
        WebTarget webTarget = configClient.target(aws_api_uri);
        // WebResource service = client.resource(UriBuilder.fromUri().build());
        // webTarget.path("dev").path("session").accept(MediaType.APPLICATION_JSON).header("Authorization", json_token);
        Invocation.Builder request = webTarget
        .request(MediaType.APPLICATION_JSON)
        .header("Authorization", json_token);
        Response response = request.get();
        // getting JSON data
        System.out.println("Created Session With JSON token" + response);
        //validate if the result is {"username":"10295765","groups":"administrator"} then return OK otherwise return failed
        return "ok";
    }

    private String getUserBeanAsJson(UserBean userBean) {
        // Before converting to GSON check value of id
        Gson gson = null;
        gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(userBean);
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}