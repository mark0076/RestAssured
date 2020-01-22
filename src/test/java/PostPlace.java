import googleAPI.PayLoad;
import googleAPI.Resourses;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class PostPlace {

    @Test
    public void createPlaceAPI(){
        RestAssured.baseURI = "http://216.10.245.166";
        given().
                queryParam("key","qaclick123").
                body(PayLoad.createPlaceData()).
                when().
                post(Resourses.addPlaceData()).
                then().assertThat().statusCode(200).
                and().contentType(ContentType.JSON).
                and().body("status",equalTo("OK"));

        //create a place response (place_id)
            // and delete that (Request - place_id)








    }

}
