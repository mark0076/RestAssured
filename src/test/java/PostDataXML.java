import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

import TestFramework.ReusableMethods;

public class PostDataXML {

    @Test
    public void postDataXML() throws IOException {
        String postData =GenerateStringFromResourse("C:\\Users\\Mark\\restAssured1\\src\\main\\java\\files\\postData.xml");
         RestAssured.baseURI = "http://216.10.245.166";
        Response resp =  given().
                queryParam("key","qaclick123").
                body(postData).
                when().
                post("/maps/api/place/add/xml").
                then().assertThat().statusCode(200).
                and().contentType(ContentType.XML).

                extract().response();
        XmlPath respInXml = ReusableMethods.rawToXml(resp);
        String place_id =respInXml.get("response.place_id");
        System.out.println(place_id);

        //create a place response (place_id)
            // and delete that (Request - place_id)






    }
    public static String GenerateStringFromResourse(String path) throws IOException{
        return new String(Files.readAllBytes(Paths.get(path)));
    }

}
