package payloads;

public class Add_Comment_JIRA {
    public static String addCommentpayload(){
        String addComment="{\n" +
                "    \"body\": \"Added my 1st comment in JIRA\",\n" +
                "    \"visibility\": {\n" +
                "        \"type\": \"role\",\n" +
                "        \"value\": \"Administrators\"\n" +
                "    }\n" +
                "}";
        return addComment;
    }
}
