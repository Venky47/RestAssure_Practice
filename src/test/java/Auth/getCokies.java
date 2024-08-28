package Auth;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.matcher.RestAssuredMatchers.*;

public class getCokies {

    @Test
    public void getCookies(){
        Response res=given()
                .when()
                .get("https://www.google.com/");
        //if single cookie
                String cookie=res.getCookie("AEC");
        System.out.println(cookie);

        //if multiple cookies
        Map<String, String> cookiesValues=res.getCookies();
        //extract only keys
        System.out.println(cookiesValues.keySet());

        for(String k:cookiesValues.keySet()){
           String cookieValue=res.getCookie(k);
            System.out.println(k+"   "+cookieValue);
        }

    }
}
