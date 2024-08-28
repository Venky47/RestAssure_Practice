package prsctice_2;

import org.testng.annotations.Test;

import java.util.HashMap;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.equalTo;

public class postTest {
    int id;
    @Test(priority = 1)
    public int postTestDemo() {

        HashMap map = new HashMap();
        map.put("name", "Venkat");
        map.put("job", "QE");
        id = given().contentType("application/json").body(map)
                .when().post("https://reqres.in/api/users")
                .jsonPath().getInt("id");
           return id;

//    then().statusCode(201).log().all();
    }
        @Test(priority = 2, dependsOnMethods = {"postTestDemo"})
                public void putDemo(){
            HashMap map = new HashMap();
            map.put("name", "Venkat");
            map.put("job", "QE/SDET");
            given().contentType("application/json").body(map)
                    .when().post("https://reqres.in/api/users"+id)
                    .then().statusCode(201).log().all();
        }

    }

