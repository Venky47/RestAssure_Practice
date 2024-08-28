package jira_Lib;

import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import payloads.Add_Comment_JIRA;
import payloads.Create_Issue_JIRA;
import payloads.Create_Session_JIRA;

import java.io.File;
import java.util.Objects;
import java.util.Properties;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Create_Session_Add_Comment {

    public String sessionName;
    public String sessionValue;
    public String pKey;
    public String cId;
    public String comment;
    SessionFilter session = new SessionFilter();//A session filter can be used record the session id returned from the server as well as automatically apply this session id in subsequent requests. For example: SessionFilter sessionFilter = new SessionFilter(); given().

    @Test(priority = 1)
    public void createSession() {
        RestAssured.baseURI = "http://localhost:8080";

        String response = given().header("Content-Type", "application/json").body(Create_Session_JIRA.createSessionPayload())
                .filter(session).when().post("/rest/auth/1/session").then().extract().response().asString();

        JsonPath jsonPath = new JsonPath(response);
        sessionName = jsonPath.getString("session.name");
        System.out.println(sessionName);
        sessionValue = jsonPath.getString("session.value");
        System.out.println(sessionValue);
    }

    @Test(priority = 2, dependsOnMethods = "createSession")
    public void createIssue() {
        RestAssured.baseURI = "http://localhost:8080";
        String responseOne = given().queryParam("Cookie", sessionName = sessionValue).header("Content-Type", "application/json")
                .body(Create_Issue_JIRA.createIssuePayload())
                .filter(session).when().post("/rest/api/2/issue").then().extract().response().asString();

        JsonPath jsonPath = new JsonPath(responseOne);
        pKey = jsonPath.get("id");
        System.out.println(pKey);
    }

    @Test(priority = 3, dependsOnMethods = "createIssue")
    public void addComment() {
        String response = given().queryParam("Cookie", sessionName = sessionValue).pathParams("id", pKey).header("Content-Type", "application/json")
                .body(Add_Comment_JIRA.addCommentpayload())
                .filter(session).when().post("/rest/api/2/issue/{id}/comment")
                .then().extract().response().asString();

        JsonPath jsonPath = new JsonPath(response);
        comment = jsonPath.get("body");
        System.out.println(comment);
        cId = jsonPath.get("id");
    }

    //Add an attachment --Content-Type is multipart/form-data & use multipart method and pass the file path
//    @Test(priority = 4)
//    public void addAnAttachment(){
//        given().header("X-Atlassian-Token","no-check").filter(session).pathParams("key",cId).header("Content-Type","multipart/form-data")
//                .multiPart("file",new File("D:\\RestAssure_ONE\\src\\test\\java\\jira.txt")).when().post("/rest/api/2/issue/{key}/attachments")
//                .then().statusCode(200);
//
//    }

    //query=parameter --- using query param we can restrict/filter the response
    //get the issue and verify the added comment message
    @Test(priority = 4)
    public void verifyComment() {
        String response = given().filter(session).relaxedHTTPSValidation().pathParam("key", pKey).queryParam("fields", "comment").when().get("/rest/api/2/issue/{key}")
                .then().statusCode(200).extract().response().asString();
        System.out.println(response);

        JsonPath jsonPath = new JsonPath(response);
        int commentCount = jsonPath.getInt("fields.comment.comments.size()");

        for (int i = 0; i < commentCount; i++) {
            String commentId = jsonPath.get("fields.comment.comments[" + i + "].id").toString();
            if (Objects.equals(commentId, cId)) {
                String commentMessage = jsonPath.get("fields.comment.comments[" + i + "].body").toString();
                System.out.println(commentMessage);
                Assert.assertEquals(commentMessage, comment);
            }
        }

    }

}
