package googleapi;

import files.PayLoad;
import io.restassured.RestAssured;
import org.testng.Assert;
import reusablemethods.ReUsable;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class EndToEndFlowAPIAsStringAPI {

    // Validate if the add place api is working as expected
    // given, when, then
    // given: Provide valid data for the API request. .e.g. valid header, parameters, and body
    // when: Provide valid api endpoint and method. e.g. GET, POST, PUT, DELETE
    // then: Provide assertion, log, and convert to response object e.g. validate status code, response body, and response time

    private static final String API_KEY = "qaclick123";

    public static void main(String[] args) {

        // Add Place API
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().queryParam("key", API_KEY)
                .header("Content-Type", "application/json").body(PayLoad.addPlacePayload())
                .when().post("/maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
                .header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
        // For parsing JSON response
        String placeID = ReUsable.rawToJson(response).get("place_id");
        System.out.println("Place ID: " + placeID);

        // Update Place API
        String updateResponse = given().log().all().queryParam("key", API_KEY)
                .header("Content-Type", "application/json")
                .body(PayLoad.updatePlacePayload(placeID, API_KEY))
                .when().put("/maps/api/place/update/json")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();
        String message = ReUsable.rawToJson(updateResponse).get("msg");
        Assert.assertEquals(message, "Address successfully updated");

        // Get Place API
        given().log().all().queryParam("key", API_KEY)
                .queryParam("place_id", placeID)
                .when().get("/maps/api/place/get/json")
                .then().log().all().assertThat().statusCode(200);

        // Delete Place API
        given().log().all().queryParam("key", API_KEY).body(PayLoad.deletePlacePayload(placeID))
                .when().delete("/maps/api/place/delete/json")
                .then().log().all().assertThat().statusCode(200);
    }
}
