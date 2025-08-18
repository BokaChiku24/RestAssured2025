package reusablemethods;

import io.restassured.path.json.JsonPath;

public class ReUsable {
    public static JsonPath rawToJson(String response) {
        return new JsonPath(response);
    }
}
