package apiChaining;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetUserTest {

    @Test
    public void getUser(ITestContext context){
        int id= (int) context.getAttribute("user_id");
        String bearerToken="2ff32e35396687c0318e761863942721684aa0039f480ac1568107b99bbca711";

        given()
                .header("Authorization", "Bearer "+bearerToken)
                .pathParams("id",id)
                .when()
                .get("https://gorest.co.in/public/v2/users/{id}")
                .then()
                .statusCode(200)
                .log().all();
    }
}
