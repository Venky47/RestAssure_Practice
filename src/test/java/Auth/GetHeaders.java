package Auth;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.matcher.RestAssuredMatchers.*;

public class GetHeaders {

    @Test
    public void getHeaders(){
        given()
                .when()
                .get("https://www.google.com/")
                .then()
                .header("Content-Type","text/html; charset=ISO-8859-1")
        .header("Content-Encoding","gzip")
                .log().headers();
    }
}
