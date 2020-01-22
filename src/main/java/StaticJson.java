import TestFramework.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


import static io.restassured.RestAssured.given;

public class StaticJson {
    @Test
    public void addBook() throws IOException {
        RestAssured.baseURI = "http://216.10.245.166";
        Response resp=given().
                header("Content-Type","application/json").
                body(GenerateStringFromResourse("src\\main\\java\\files\\addbookdetails.json")).
                when().
                post("Library/Addbook.php").
                then().assertThat().statusCode(200).
                and().contentType(ContentType.JSON).
                extract().response();
        JsonPath respInJson = ReusableMethods.rawToJson(resp);
        String id = respInJson.get("ID");
        System.out.println(id);




    }
    public static String GenerateStringFromResourse(String path) throws IOException{
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}
