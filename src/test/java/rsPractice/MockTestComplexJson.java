package rsPractice;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import payloads.MockPayload;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class MockTestComplexJson {

    @Test
    public void mockTest() {
        String firstcourseTitle = null;
        int priceCourse = 0;
        int copiesCourse = 0;
        int sum=0;
        int totalPurchaseAmount=0;
        JsonPath jsonPath = new JsonPath(MockPayload.mockedPayLoad());
        //Print No of courses
        int courseCount = jsonPath.getInt("courses.size()"); //size() applied to get the count of an array
        System.out.println("course count is " + courseCount);
        //Print Purchase Amount
        int purchasAmount = jsonPath.getInt("dashboard.purchaseAmount");
        System.out.println("PurchasAmount is " + purchasAmount);
        //Print Title of the first course
        String title = jsonPath.getString("courses[0].title");
        System.out.println("title of the first course is " + title);
        //Print All course titles and their respective Prices
        for (int i = 0; i < courseCount; i++) {
            String courseTitles = jsonPath.getString("courses[" + i + "].title");
            System.out.println("course titles are " + courseTitles);
            if (courseTitles.equals("Selenium Python")) {
                firstcourseTitle = jsonPath.getString("courses[" + i + "].title");
                System.out.println("First course title is " + firstcourseTitle);
            }
            Assert.assertEquals(firstcourseTitle, "Selenium Python");
            int price = jsonPath.getInt("courses[" + i + "].price");
            System.out.println("course prices are " + price);
        }

        //Print no of copies sold by RPA Course
        for (int i = 0; i < courseCount; i++) {
            String courseTitles = jsonPath.getString("courses[" + i + "].title");
            if (courseTitles.equals("RPA")) {
                int copies = jsonPath.getInt("courses[" + i + "].copies");
                System.out.println("RPA copies are -->" + copies);
            }
        }

        //Verify if Sum of all Course prices matches with Purchase Amount
        int purchaseAmount = jsonPath.getInt("dashboard.purchaseAmount");
        System.out.println("purchase amount is " + purchaseAmount);
        for (int i = 0; i < courseCount; i++) {
            priceCourse = jsonPath.getInt("courses[" + i + "].price");
            copiesCourse = jsonPath.getInt("courses[" + i + "].copies");
            totalPurchaseAmount = (priceCourse * copiesCourse);
            sum=sum+totalPurchaseAmount;
        }

        System.out.println(sum);
        Assert.assertEquals(sum,purchaseAmount);
    }
}