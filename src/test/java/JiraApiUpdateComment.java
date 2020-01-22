import TestFramework.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class JiraApiUpdateComment {
    Properties prop = new Properties();

    @BeforeTest
    public void getData() throws IOException {

        FileInputStream fis = new FileInputStream("src/main/java/files/env.properties");
        prop.load(fis);
        //prop.get("HOST");
    }

    @Test
    public void JiraApiUpdateComment() {
        //Creating Issue/Defect

        RestAssured.baseURI = "http://localhost:8080";
        Response res = given().
                header("Content-Type", "application/json").
                header("cookie", "JSESSIONID=" + ReusableMethods.getSessionKey() + "").
                pathParam("commentid","10001").
                body("{\n" +
                        "    \"body\": \"Updated comment\",\n" +
                        "    \"visibility\": {\n" +
                        "        \"type\": \"role\",\n" +
                        "        \"value\": \"Administrators\"\n" +
                        "    }\n" +
                        "}").
                when().
                put("/rest/api/2/issue/10006/comment/{commentid}").
                then().statusCode(200).extract().response();
        JsonPath responseJson = ReusableMethods.rawToJson(res);
        String idOfIssue = responseJson.get("id");
        System.out.println(idOfIssue);
    }

}