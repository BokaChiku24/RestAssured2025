package addplace;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class AddPlaceAPI {

    // Validate if the add place api is working as expected
    // given, when, then
    // given: Provide valid data for the API request. .e.g. valid header, parameters, and body
    // when: Provide valid api endpoint and method. e.g. GET, POST, PUT, DELETE
    // then: Provide assertion, log, and convert to response object e.g. validate status code, response body, and response time

    public static void main(String[] args) {
        RestAssured.baseURI="https://rahulshettyacademy.com";
        Response response = given().queryParam("key","qaclick123")
                .header("Content-Type","application/json").body("""
                        {
                          "location": {
                            "lat": -50.383494,
                            "lng": 50.427362
                          },
                          "accuracy": 50,
                          "name": "Frontline house",
                          "phone_number": "(+91) 983 893 3937",
                          "address": "29, side layout, cohen 09",
                          "types": [
                            "shoe park",
                            "shop"
                          ],
                          "website": "http://google.com",
                          "language": "English-IN"
                        }""")
                .when().post("/maps/api/place/add/json")
                .then().assertThat().log().all().statusCode(200).extract().response();
        String placeID = response.jsonPath().get("place_id");
        System.out.println("Place ID: " + placeID);
    }

}
