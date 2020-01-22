import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import googleAPI.Resourses;
import googleAPI.PayLoad;

public class DeletePlace {
    private static Logger log =LogManager.getLogger(DeletePlace.class.getName());
    Properties prop = new Properties();


    @BeforeTest
    public void getData() throws IOException {

        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//env.properties");
        prop.load(fis);
        //prop.get("HOST");
    }


    @Test
    public void addAndDeletePlace(){
        //Task 1 - Grab the response
        log.info("Host information " +prop.getProperty("HOST"));
        RestAssured.baseURI = prop.getProperty("HOST");

        Response res =given().
                queryParam("key", prop.getProperty("KEY")).
                and().body(PayLoad.getPostData()).
                when().
                post(Resourses.placePostData()).
                then().assertThat().statusCode(200).
                and().contentType(ContentType.JSON).
                and().body("status",equalTo("OK")).
                extract().response();
        // Task 2 - Grab the Place ID from response
        String responseString=res.asString();
        log.info(responseString);

        JsonPath js = new JsonPath (responseString);
        String placeId = js.get("place_id");
        log.info(placeId);


        //Task 3 - Place this place_id in the Delete request
        given().
                queryParam("key","qaclick123").
                body("{\n" +
                        "    \"place_id\":\""+placeId +"\"           \n" +
                        "}\n").
                when().
                post("/maps/api/place/delete/json").
                then().assertThat().statusCode(200).
                and().body("status",equalTo("OK")).
                and().contentType(ContentType.JSON);

    }

}
