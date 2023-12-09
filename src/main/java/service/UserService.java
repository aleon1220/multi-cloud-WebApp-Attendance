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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.apache.http.client.ClientProtocolException;
import com.sun.jersey.api.client.ClientResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import bean.UserBean;
import entity.*;

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
        String replyJson = receivedJSON;

        Gson gson = new Gson();
        LoginReply loginReplyObject = gson.fromJson(replyJson, LoginReply.class);

        String extractedIdToken = loginReplyObject.getAuthenticationResult().getIdToken();

        user.setToken(extractedIdToken);
        setAccessToken(loginReplyObject.getAuthenticationResult().getAccessToken());
        setExpiresIn(loginReplyObject.getAuthenticationResult().getExpiresIn().toString());
        setIdToken(loginReplyObject.getAuthenticationResult().getIdToken());
        setRefreshToken(loginReplyObject.getAuthenticationResult().getRefreshToken());
        setTokenType(loginReplyObject.getAuthenticationResult().getTokenType());
        
        return extractedIdToken;
    }
    
    public String createSession(String token) {
        //Create session method is a GET Rest invocation
        //System.out.println("UserServ: creating session method");
        ClientConfig config = new DefaultClientConfig();
        //System.out.println("UserServ: creating client config");
        Client client = Client.create(config);
        
        //System.out.println("UserServ: creating session");

        WebResource service = client.resource(UriBuilder.fromUri("https://ctpoixww04.execute-api.us-east-1.amazonaws.com").build());
        
        // getting JSON data
        System.out.println("User Service Create Session With JSON" + 
        service.path("dev").path("session").accept(MediaType.APPLICATION_JSON).header("Authorization", token).get(String.class));
        
        //validate if the result is {"username":"10295765","groups":"administrator"} then return OK otherwise return failed

        String sessionResult = "ok";
        return sessionResult;
    }

    private String getJsonString(UserBean userBean) {
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