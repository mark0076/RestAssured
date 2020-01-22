import TestFramework.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import jiraAPI.Resourses;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class JiraApiCreateComment {
    Properties prop = new Properties();
    @BeforeTest
    public void getData() throws IOException {

        FileInputStream fis = new FileInputStream("C:/Users/Mark/restAssured1/src/main/java/files/env.properties");
        prop.load(fis);
        //prop.get("HOST");
    }
    @Test
    public void JiraApiCreateComment(){
        //Creating Issue/Defect

        RestAssured.baseURI = "http://localhost:8080";
        Response res =given().
                header("Content-Type","application/json").
                header("cookie","JSESSIONID="+ ReusableMethods.getSessionKey()+"").
                body("{\n" +
                        "    \"body\": \"Inserting comment from the automation code\",\n" +
                        "    \"visibility\": {\n" +
                        "        \"type\": \"role\",\n" +
                        "        \"value\": \"Administrators\"\n" +
                        "    }\n" +
                        "}").
                when().
                post(Resourses.createComment()).
                then().statusCode(201).extract().response();
        JsonPath responseJson = ReusableMethods.rawToJson(res);
        String idOfIssue =responseJson.get("id");
        System.out.println(idOfIssue);






    }
}
