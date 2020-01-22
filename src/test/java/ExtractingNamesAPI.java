import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import TestFramework.ReusableMethods;


public class ExtractingNamesAPI {
    @Test
    public void extractingNamesAPI() {
        //baseURL or Host
        RestAssured.baseURI = "https://maps.googleapis.com";

        Response resp=given().
                param("location", "-33.8670522,151.1957362").
                param("radius", "1500").
                param("key","AIzaSyA3XLP_v1ORSHWSNoz9cUw_9OHMDBe8iAw").
                when().
                get("/maps/api/place/nearbysearch/json").
                then().assertThat().statusCode(200).
                and().contentType(ContentType.JSON).
                and().body("results[0].name",equalTo("Sydney")).
                and().body("results[0].place_id",equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).
                and().header("Server","scaffolding on HTTPServer2").
                extract().response();
        JsonPath responseInJson =ReusableMethods.rawToJson(resp);
        int sizeOfResulst=responseInJson.get("results.size()");
        System.out.println(sizeOfResulst);
        for (int i=0;i<sizeOfResulst;i++){
            String strResp =responseInJson.get("results["+i+"].name");
            System.out.println(strResp);

        }

    }
}
