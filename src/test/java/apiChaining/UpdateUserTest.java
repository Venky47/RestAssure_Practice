package apiChaining;

import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdateUserTest {

    @Test
    public void updateuser(ITestContext context){
        Faker faker=new Faker();

        //add test data/payload using JSONObject
        JSONObject data=new JSONObject();
        data.put("name", faker.name().fullName());
        data.put("gender","Male");
        data.put("email",faker.internet().emailAddress());
        data.put("status","active");

        //add the token
        String bearerToken="2ff32e35396687c0318e761863942721684aa0039f480ac1568107b99bbca711";
        int id= (int) context.getAttribute("user_id");
              given()
                .header("Authorization", "Bearer "+bearerToken)
                .contentType("application/json")
                .pathParams("id",id)
                .body(data.toString())
                      .when()
                .put("https://gorest.co.in/public/v2/users/{id}")
                .then()
                      .log().all();
    }
}
