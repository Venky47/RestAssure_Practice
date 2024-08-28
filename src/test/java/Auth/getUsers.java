package Auth;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.equalTo;


public class getUsers {
    int id;
    @Test(priority = 1)
    public void getusersList(){
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("page",equalTo(2))
                .log().all();
    }

    @Test(priority = 2)
    public void createUser(){
        HashMap data=new HashMap();
        data.put("name","morpheus");
        data.put("job", "leader");
        id=given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("https://reqres.in/api/users")
                .jsonPath().getInt("id");
//                .then()
//                .statusCode(201)
//                .log().all();
    }

    @Test(priority = 3,dependsOnMethods = {"createUser"})
    public void updateUser(){
        HashMap data=new HashMap();
        data.put("name","venkat");
        data.put("job", "SDET");
        given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("https://reqres.in/api/users"+id)
                .then()
                .statusCode(201)
                .log().all();
    }

    @Test(priority = 4)
    public void deleteUser(){
        given()

                .when()
                .post("https://reqres.in/api/users"+id)
                .then()
                .statusCode(204)
                .log().all();
    }
}
