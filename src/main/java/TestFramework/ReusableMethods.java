package TestFramework;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import java.util.Properties;

import static io.restassured.RestAssured.given;

public class ReusableMethods {
    
    public static XmlPath rawToXml(Response res){
        String resp =res.asString();
        System.out.println(resp);
        XmlPath respInXml = new XmlPath(resp);
        return respInXml;
    }
    public static JsonPath rawToJson(Response res){
        String resp =res.asString();
        System.out.println(resp);
        JsonPath respInJson = new JsonPath(resp);
        return respInJson;
    }
    public static String getSessionKey(){
        Properties prop = new Properties();
        RestAssured.baseURI = "http://localhost:8080";
        Response resp =given().
                header("Content-Type","application/json").
                body("{ \"username\": \"rma0076\", \"password\": \"qazXSW69\" }").
                when().
                post("/rest/auth/1/session").
                then().
                statusCode(200).
                extract().response();
        JsonPath response = ReusableMethods.rawToJson(resp);
        String sessionValue =response.get("session.value");
        return sessionValue;
    }
}
