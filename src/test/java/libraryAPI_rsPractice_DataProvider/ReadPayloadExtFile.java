package libraryAPI_rsPractice_DataProvider;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;

public class ReadPayloadExtFile {

    @Test
    public void readPayloadExt() throws IOException {
        RestAssured.baseURI="https://rahulshettyacademy.com";
        given().queryParam("Key","qaclick123").header("Content-Type","application/json").body(new String(Files.readAllBytes(Paths.get("D:\\RestAssure_ONE\\src\\test\\java\\test.json"))))
                .when().post("/maps/api/place/add/json")
                .then().statusCode(200).log().all();
//        JsonPath jsonPath= BaseClass.rawToJson(response);
//        String pamount=jsonPath.getString("dashboard.purchaseAmount");
//        System.out.println(pamount);
    }
    }

