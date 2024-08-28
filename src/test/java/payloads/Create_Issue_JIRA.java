package payloads;

public class Create_Issue_JIRA {

    public static String createIssuePayload(){
        return "{\n" +
                "    \"fields\": {\n" +
                "       \"project\":\n" +
                "    {\n" +
                "          \"key\": \"RES\"\n" +
                "       },\n" +
                "       \"summary\": \"Issue 5 for adding comment\",\n" +
                "       \"description\": \"Creating my second bug\",\n" +
                "       \"issuetype\": {\n" +
                "          \"name\": \"Bug\"\n" +
                "       }\n" +
                "}\n" +
                "}";
    }
}
