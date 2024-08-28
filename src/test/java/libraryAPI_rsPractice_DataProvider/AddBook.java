package libraryAPI_rsPractice_DataProvider;

import common.BaseClass;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import payloads.AddLibPayload;

import static io.restassured.RestAssured.*;

public class AddBook {
    public String id;

    @Test(dataProvider ="BookData" )
    public void addBook(String isbn, String aisle){
        RestAssured.baseURI="http://216.10.245.166";
    String response=given().header("Content-Type","application/json").body(AddLibPayload.addLibraryPayload(isbn,aisle))
            .when().post("/Library/Addbook.php")
            .then().statusCode(200).extract().response().asString();

        JsonPath jsonPath=BaseClass.rawToJson(response);
        id=jsonPath.get("ID");
        System.out.println(id);
    }

    //dataprovider -can send multiple test data
    @DataProvider(name="BookData")
    public Object[][] getData(){
        return new Object[][]{{"abs","234"},{"absq","2534"},{"abset","21234"}};
    }
}
