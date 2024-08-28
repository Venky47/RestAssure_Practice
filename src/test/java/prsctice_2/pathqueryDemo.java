package prsctice_2;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.equalTo;

public class pathqueryDemo {

    @Test
    public void pathDemo(){
        given().pathParams("pathParam","users")
                .queryParam("page","1")
                .queryParam("id","1")
                .when().get("https://reqres.in/api/{pathParam}")
                .then().statusCode(200)
                .log().all();
    }
}
