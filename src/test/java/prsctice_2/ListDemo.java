package prsctice_2;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.equalTo;

public class ListDemo {

    @Test
    public void listDemoTest(){
        Response res=given().
                when().get("https://reqres.in/api/users?page=2");
        //convert JSON to string
        JsonPath j = new JsonPath(res.asString());
        System.out.println(j.getString("page"));

        }



}
