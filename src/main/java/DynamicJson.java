import TestFramework.PayLoad;
import TestFramework.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class DynamicJson {

    @Test(dataProvider = "BooksData")
    public void addBook(String isbn,String aisle ) throws IOException{
    RestAssured.baseURI = "http://216.10.245.166";
    Response resp=given().
            header("Content-Type","application/json").
            body(PayLoad.addBook(isbn,aisle)).
            when().
            post("Library/Addbook.php").
            then().assertThat().statusCode(200).
            and().contentType(ContentType.JSON).
            extract().response();
    JsonPath respInJson = ReusableMethods.rawToJson(resp);
    String id = respInJson.get("ID");
        System.out.println(id);




}

    @DataProvider(name ="BooksData")
    public Object[][] detData(){
        //array= collection of element
        //multidomential array = collection of arrays
        return new Object[][]{
                {"isada","1233"},{"dasfv","3596"},{"jioqw","386"},
        };
    }

}
