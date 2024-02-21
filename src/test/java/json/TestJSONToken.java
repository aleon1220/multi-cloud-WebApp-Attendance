package json;

import jakarta.json.Json;
import jakarta.json.stream.JsonParser;
import jakarta.json.stream.JsonParser.Event;
import com.google.gson.Gson;
import auth.login.LoginReply;

import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TestJSONToken {

    @Test
    public void testMalformedURL() throws MalformedURLException {
        // URL url = new URL("https://graph.facebook.com/search?q=java&type=post");
        try {
            URL url = new URL("https://ctpoixww04.execute-api.us-east-1.amazonaws.com/dev/login");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            var username = "op://Professional-IT Projects/multi-cloud-WebApp-Attendance/username";
            var password = "op://Professional-IT Projects/multi-cloud-WebApp-Attendance/password";
            String input = "{\"id\":\"" + username + "\",\"" + password + "\":\"\"}";

            OutputStream os = conn.getOutputStream();
            // 1.The client sends their credentials (username and password) to the server.
            os.write(input.getBytes());
            os.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            JsonParser parser = (JsonParser) Json.createReader(br);

            while (parser.hasNext()) {
                Event e = parser.next();
                if (e == Event.KEY_NAME) {
                    switch (parser.getString()) {
                        case "RefreshToken":
                            parser.next();
                            System.out.print(parser.getString());
                            System.out.print(": ");
                            break;
                        case "IdToken":
                            parser.next();
                            System.out.println(parser.getString());
                            System.out.println("---------");
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void formatTokenJSONResponse() {
        var token = "";
        String replyJson = "{\"ChallengeParameters\":{},\"AuthenticationResult\":{\"AccessToken\":\"" + token + "\"}}";
        Gson gson = new Gson();
        LoginReply loginReplyObject = gson.fromJson(replyJson, LoginReply.class);
        // Object loginReplyObject = gson.fromJson(replyJson, Object.class);
        System.out.println("value " + loginReplyObject.getAuthenticationResult().getIdToken());
        System.out.println("obtain token " + loginReplyObject.getAuthenticationResult().getAccessToken());
    }
}
