package rsPractice;

import common.BaseClass;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;
import payloads.AddPlacePayload;
import payloads.ChangeAddPayload;

public class AddPlace {
    public static String placeId;
    public static String actualAddress;


    @Test(priority = 1)
    public void addPlace() {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().queryParam("Key", "qaclick123").header("Content-Type", "application/json")
                .body(AddPlacePayload.addPlacePayload())
                .when().post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200).
                body("scope", equalTo("APP")).header("Server", equalTo("Apache/2.4.52 (Ubuntu)")).extract().response().asString(); //extract the response & store it in a String

        //takes String input & convert into json
        JsonPath jsonPath = BaseClass.rawToJson(response);
        placeId = jsonPath.getString("place_id");
        System.out.println("place id is " + placeId);
    }

    @Test(priority = 2, dependsOnMethods = {"addPlace"})
    public void updatePlace() {
        given().queryParam("Key", "qaclick123").header("Content-Type", "application/json")
                .body(ChangeAddPayload.changeAddPayload())
                .when().put("/maps/api/place/update/json")
                .then().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated")).log().all();

    }


    @Test(priority = 3, dependsOnMethods = {"updatePlace"})
    public void getPlace() {
        System.out.println("place id inside get is " + placeId);
        given().queryParam("Key", "qaclick123").queryParam("place_id", placeId)
                .when().get("/maps/api/place/get/json")
                .then().assertThat().statusCode(200).log().all();

//       JsonPath jsonPath=new JsonPath(responseGet);
//        actualAddress=jsonPath.getString("address");
//        System.out.println("actual address is "+actualAddress);
    }
}