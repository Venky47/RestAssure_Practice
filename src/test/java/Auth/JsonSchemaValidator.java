package Auth;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.equalTo;

public class JsonSchemaValidator {

    @Test
    public void jsonValidator(){
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then();
//                .assertThat().body(Auth.JsonSchemaValidator.)
    }
}
