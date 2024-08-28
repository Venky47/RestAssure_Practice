package common;

import io.restassured.path.json.JsonPath;

public class BaseClass {

    public static JsonPath rawToJson(String response){
        JsonPath jsonPath = new JsonPath(response);
        return jsonPath;
    }
}
