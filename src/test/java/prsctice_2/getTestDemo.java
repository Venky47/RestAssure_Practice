package prsctice_2;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.equalTo;

public class getTestDemo {

    @Test
    public void getTest(){
        given().
                when().get("https://jsonplaceholder.typicode.com/users")
                .then().statusCode(200)
                .body("name",equalTo("ven"))
                .log().all();

    }
}
