package apiChaining;

import com.github.javafaker.Faker;
import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateUserTest {

    @Test
    void createUser(ITestContext context){
        Faker faker=new Faker();

        //add test data/payload using JSONObject
        JSONObject data=new JSONObject();
        data.put("name", faker.name().fullName());
        data.put("gender","Male");
        data.put("email",faker.internet().emailAddress());
        data.put("status","active");

        //add the token
        String bearerToken="2ff32e35396687c0318e761863942721684aa0039f480ac1568107b99bbca711";

       int id= given()
                .header("Authorization", "Bearer "+bearerToken)
                .contentType("application/json")
                .body(data.toString())
        .when()
                .post("https://gorest.co.in/public/v2/users")
                .jsonPath().getInt("id");

        System.out.println("generated id is "+id);
        context.setAttribute("user_id",id);





    }
}
