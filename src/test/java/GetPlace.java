import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


public class GetPlace {
    @Test
    public void getPlaceAPI() {
        //baseURL or Host
        RestAssured.baseURI = "https://maps.googleapis.com";

        given().
                param("location", "-33.8670522,151.1957362").
                param("radius", "1500").
                param("key","AIzaSyA3XLP_v1ORSHWSNoz9cUw_9OHMDBe8iAw").log().all().
                when().
                get("/maps/api/place/nearbysearch/json").
                then().assertThat().statusCode(200).
                and().contentType(ContentType.JSON).
                and().body("results[0].name",equalTo("Sydney")).
                and().body("results[0].place_id",equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).
                and().header("Server","scaffolding on HTTPServer2").log().body();


    }
}
