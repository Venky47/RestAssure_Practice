package payloads;

import org.testng.annotations.DataProvider;

public class AddLibPayload {
    public static String addLibraryPayload(String isbn, String aisle) {

        String payload = "{\n" +
                "\n" +
                "\"name\":\"QE/SDET\",\n" +
                "\"isbn\":\""+isbn+"\",\n" +
                "\"aisle\":\""+aisle+"\",\n" +
                "\"author\":\"John foe\"\n" +
                "}\n";
        return payload;
    }


}