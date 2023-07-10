package jersey;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

public class TestJSONResponse {

    public static void main(String[] args) throws MalformedURLException {
        // URL url = new URL("https://graph.facebook.com/search?q=java&type=post");
        // URL url = new
        // URL("https://ctpoixww04.execute-api.us-east-1.amazonaws.com/dev/login");

        // try (InputStream is = url.openStream(); JsonParser parser =
        // Json.createParser(is)) {
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
}
