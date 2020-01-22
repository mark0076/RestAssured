package googleAPI;

public class PayLoad {

    public static String getPostData(){
        String payLoad ="{\n" +
                "    \"location\":{\n" +
                "        \"lat\" : -38.383494,\n" +
                "        \"lng\" : 33.427362\n" +
                "    },\n" +
                "    \"accuracy\":50,\n" +
                "    \"name\":\"Frontline house\",\n" +
                "    \"phone_number\":\"(+91) 983 893 3937\",\n" +
                "    \"address\" : \"29, side layout, cohen 09\",\n" +
                "    \"types\": [\"shoe park\",\"shop\"],\n" +
                "    \"website\" : \"http://google.com\",\n" +
                "    \"language\" : \"French-IN\"\n" +
                "}\n";
        return payLoad;
    }
    public static String createPlaceData(){
        String data = "{\\n\" +\n" +
                "                        \"    \\\"location\\\":{\\n\" +\n" +
                "                        \"        \\\"lat\\\" : -38.383494,\\n\" +\n" +
                "                        \"        \\\"lng\\\" : 33.427362\\n\" +\n" +
                "                        \"    },\\n\" +\n" +
                "                        \"    \\\"accuracy\\\":50,\\n\" +\n" +
                "                        \"    \\\"name\\\":\\\"Frontline house\\\",\\n\" +\n" +
                "                        \"    \\\"phone_number\\\":\\\"(+91) 983 893 3937\\\",\\n\" +\n" +
                "                        \"    \\\"address\\\" : \\\"29, side layout, cohen 09\\\",\\n\" +\n" +
                "                        \"    \\\"types\\\": [\\\"shoe park\\\",\\\"shop\\\"],\\n\" +\n" +
                "                        \"    \\\"website\\\" : \\\"http://google.com\\\",\\n\" +\n" +
                "                        \"    \\\"language\\\" : \\\"French-IN\\\"\\n\" +\n" +
                "                        \"}\\n";
        return data;
    }
}
