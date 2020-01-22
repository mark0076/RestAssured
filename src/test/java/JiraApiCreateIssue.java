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

public class JiraApiCreateIssue {
    Properties prop = new Properties();
    @BeforeTest
    public void getData() throws IOException {

        FileInputStream fis = new FileInputStream("C:/Users/Mark/restAssured1/src/main/java/files/env.properties");
        prop.load(fis);
        //prop.get("HOST");
    }




    @Test
    public void JiraApiCreateIssue(){
        //Creating Issue/Defect

        RestAssured.baseURI = "http://localhost:8080";
        Response res =given().
                header("Content-Type","application/json").
                header("cookie","JSESSIONID="+ReusableMethods.getSessionKey()+"").
                body("{\n" +
                        "\t\"fields\":{\n" +
                        "\t\t\"project\":{\n" +
                        "\t\t\t\"key\": \"RES\"\n" +
                        "\t\t},\n" +
                        "\t\t\"summary\": \"Issue 5 for adding comment\",\n" +
                        "\t\t\"description\": \"Creating my second bug\",\n" +
                        "\t\t\"issuetype\": {\n" +
                        "\t\t\t\"name\": \"Bug\"\n" +
                        "\t\t}\n" +
                        "\t}\n" +
                        "}").
                when().
                post("/rest/api/2/issue").
                then().statusCode(201).extract().response();
            JsonPath responseJson = ReusableMethods.rawToJson(res);
            String idOfIssue =responseJson.get("id");
        System.out.println(idOfIssue);






    }
}


