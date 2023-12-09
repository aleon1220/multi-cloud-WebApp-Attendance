package json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entity.Authorization;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class TestConversionJson2Map {
    public void convertJSON2Map() {
        var jwt_token = "op://Professional-IT Projects/JWT TestConversionJson2Map/credential";
        String jsonUser = "{'name' : 'Alejandra Rangel'}";
        String mockJSON_response = "{\"ChallengeParameters\":{},\"AuthenticationResult\":{\"AccessToken\":\"" + jwt_token +" \"}}";

        Gson gson = new Gson();
        Type authorization = new TypeToken<Map<String, Authorization>>() {
        }.getType();
        Map<String, Authorization> authorizationMap = gson.fromJson(mockJSON_response, authorization);
        Map<String, Object> retMap = new Gson().fromJson(mockJSON_response, new TypeToken<HashMap<String, Object>>() {
        }.getType());

        Map<String, Object> map = new Gson().fromJson(mockJSON_response,new TypeToken<HashMap<String, Authorization>>() {}.getType());
        System.out.println("Printing Map object: " + authorizationMap.values().toString());

        authorizationMap.keySet().iterator().forEachRemaining(System.out::println);
        System.out.println("Printing ret Map object this is bringing the values: " + retMap.values().toString());
        map.keySet().iterator().forEachRemaining(System.out::println);
        System.out.println(retMap.get("IdToken"));
    }

    public void convertJSONtoMapStructure() {
        var jwt_token = "op://Professional-IT Projects/convertJSONtoMapStructure/credential";
        String jsonFromMS = "{\"ChallengeParameters\":{},\"AuthenticationResult\":{\"AccessToken\":\"" + jwt_token +" \"}}";

        Gson gson = new Gson();
        Type authorization = new TypeToken<Map<String, Authorization>>() {
        }.getType();

        Map<String, Authorization> authorizationMap = gson.fromJson(jsonFromMS, authorization);

        Map<String, Object> retMap = new Gson().fromJson(jsonFromMS, new TypeToken<HashMap<String, Object>>() {
        }.getType());

        Map<String, Object> map = new Gson().fromJson(jsonFromMS, new TypeToken<HashMap<String, Authorization>>() {
        }.getType());

        System.out.println("Printing Map object: " + authorizationMap.values().toString());
        authorizationMap.keySet().iterator().forEachRemaining(System.out::println);
        System.out.println("Printing ret Map object this is bringing the values: " + retMap.values().toString());
        map.keySet().iterator().forEachRemaining(System.out::println);
        System.out.println(retMap.get("IdToken"));
    }
}
