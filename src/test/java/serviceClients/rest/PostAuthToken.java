package serviceClients.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class PostAuthToken {

    // authentication scheme based on tokens follow these steps, steps are numbered
    public static void main(String[] args) {

        try {
            URL url = new URL("https://ctpoixww04.execute-api.us-east-1.amazonaws.com/dev/login");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            String input = "{\"id\":\"10295765\",\"password\":\"Value!12\"}";

            OutputStream os = conn.getOutputStream();
            // 1.The client sends their credentials (username and password) to the server.
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output;
            // 2.The server authenticates the credentials and, if they are valid, generate a
            // token for the user.
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                JsonReader rdr = Json.createReader(br);
                System.out.println("creates object");
                // rdr.readObject();
                System.out.println("obtain object" + rdr.toString());
                System.out.println("prints output " + br.readLine());

                JsonObject obj = rdr.readObject();
                System.out.println("before reading object");

                JsonArray results = obj.getJsonArray("AuthenticationResult");
                // obj.getJsonString(arg0)
                System.out.print("value type: " + results.getValueType());

                // for (JsonObject result : results.getValuesAs(JsonObject.class)) {
                // System.out.print("value type: "+results.getValueType());
                // System.out.println("token"+ result.getString("IdToken"));
                // }

                System.out.println(output);
            } // end of while

            System.out.println("prints output 1" + br.readLine());

            /// put object in hashmap somehow

            // disconnects
            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }
}