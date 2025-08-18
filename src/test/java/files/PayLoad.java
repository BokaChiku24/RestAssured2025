package files;

public class PayLoad {
    public static String addPlacePayload() {
        return """
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
                }""";
    }

    public static String updatePlacePayload(String placeID, String key) {
        return "{\n" +
                "\"place_id\":\""+placeID+"\",\n" +
                "\"address\":\"76 Summer walk, USA\",\n" +
                "\"key\":\""+key+"\"\n" +
                "}\n";
    }

    public static String deletePlacePayload(String placeID) {
        return "{\n" +
                "    \"place_id\":"+ placeID+ "\n" +
                "}";
    }
}
