package json;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import com.google.gson.Gson;

import org.junit.jupiter.api.Test;
import entity.LoginReply;

public class TestConversionJSON2Object {
    /**
     * examine JSON objects with gson
     *
     */
    @Test
    public void testJWTOutputToken() {
        var jwt_Token = readTokenFromProperties();
        System.out.println("Got the Token from readTokenFromProperties()");
        String replyJson = "";
        try {
            // https://www.baeldung.com/java-text-blocks
            replyJson = """
            {
              "ChallengeParameters": {}, 
              "AuthenticationResult": {
                "AccessToken": %s
              }
            }
            """.formatted(jwt_Token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        // import com.google.gson.Gson;
        // import com.google.gson.GsonBuilder;
        // JSON Printing
        System.out.println("Java Text Block FormattedJson " + replyJson);
        String formattedJson = gson.toJson(replyJson);
        System.out.println("gsonFormattedJson " + formattedJson);
    }

    public static String readTokenFromProperties() {
        Properties prop = new Properties();
        var jwtToken = "";

        try {
            String propFileName = "token_auth.properties";
            FileInputStream fileInputStream = new FileInputStream(propFileName);
            prop.load(fileInputStream);
            // get the property value and print it out
            jwtToken = prop.getProperty("jwt_Token");
            System.out.println("Got the Token from local property file "+ propFileName);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return jwtToken;
    }
}
