package rsPractice;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class Requers {

    public void createUser(){
        RestAssured.baseURI="https://reqres.in";

    }
}
