import TestFramework.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import resourses.DataDriven;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


import static io.restassured.RestAssured.given;

public class ExcelDriven {


    @Test
    public void addBook() throws IOException{
        DataDriven d=new DataDriven();
        ArrayList data= d.getData("RestAddbook","RestAssured");
        HashMap<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name",data.get(1));
        jsonAsMap.put("isbn",data.get(2));
        jsonAsMap.put("aisle",data.get(3));
        jsonAsMap.put("author",data.get(4));
    RestAssured.baseURI = "http://216.10.245.166";
    Response resp=given().
            header("Content-Type","application/json").
            body(jsonAsMap).
            when().
            post("Library/Addbook.php").
            then().assertThat().statusCode(200).
            and().contentType(ContentType.JSON).
            extract().response();
    JsonPath respInJson = ReusableMethods.rawToJson(resp);
    String id = respInJson.get("ID");
        System.out.println(id);




}}
