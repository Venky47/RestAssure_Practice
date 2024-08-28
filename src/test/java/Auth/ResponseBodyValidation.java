package Auth;

import com.google.gson.JsonObject;
import io.restassured.response.Response;
import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.matcher.RestAssuredMatchers.*;

public class ResponseBodyValidation {

    @Test
    public void responseValidation() {
        Response res = given()
                .when()
                .get("https://reqres.in/api/unknown");
        JSONObject jo = new JSONObject(res.asString());
        boolean status=false;
        for (int i = 0; i < jo.getJSONArray("data").length(); i++) {
            String actualResult = jo.getJSONArray("data").getJSONObject(i).get("name").toString();
            System.out.println(actualResult);
            String expectedResult="fuchsia rose";
            if(actualResult.equals(expectedResult)){
                status=true;
                break;
            }
        }


    }
}