package payloads;

import rsPractice.AddPlace;

public class ChangeAddPayload extends AddPlace {

    public static String changeAddPayload(){
        String pId=AddPlace.placeId;
        System.out.println(pId);
        return "{\n" +
                "\"place_id\":\""+pId+"\",\n" +
                "\"address\":\"70 Summer walk, USA\",\n" +
                "\"key\":\"qaclick123\"\n" +
                "}";
    }
}
