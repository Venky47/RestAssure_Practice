package Auth;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.equalTo;

public class BasicAuth {

    @Test
    public void basicAuthVerification(){
        given()
                .auth().basic("postman","password")
                .when().get("https://postman-echo.com/basic-auth").then()
                .statusCode(200)
                .body("authenticated",equalTo(true))
                .log().all();
    }
}
