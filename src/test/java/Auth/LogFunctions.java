package Auth;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.matcher.RestAssuredMatchers.*;
public class LogFunctions {

    @Test
    public void logFun(){
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().body()
                .log().headers()
                .log().cookies()
                .log().all();

    }
}
