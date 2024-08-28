package prsctice_2;

import org.testng.annotations.Test;
import prsctice_2.pojo.postPOJO;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.equalTo;

public class postPOJODemo {

   @Test
    public void testPOJO() {
        postPOJO data = new postPOJO();
        data.setName("Ven");
        data.setJob("QE");
        data.setAmount(1000);
//        data.setCourse({"c"});
        given().contentType("application/json").body(data)
                .when().post("https://reqres.in/api/users")
                .then().statusCode(201)
                .log().all();
    }
}