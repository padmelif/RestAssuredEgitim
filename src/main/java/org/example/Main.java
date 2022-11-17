package org.example;

import files.payload;
import files.reUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Main {
    public static void main(String[] args) throws IOException {

        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type0", "application/json")
                .body(payload.AddPlace()).when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200).body("scope", equalTo("APP")).header("server", "Apache/2.4.41 (Ubuntu)")
                .extract().response().asString();
        System.out.println(response);
        JsonPath js = new JsonPath(response); //for parsing json
        String placeId = js.getString("place_id");

        System.out.println(placeId);

        //Update Place

        String newAddress = "Summer Walk, Africa";

        given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body("{\n" +
                        "\"place_id\": \""+placeId+"\",\n" +
                        "\"address\":\""+newAddress+"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}\n" +
                        " ")
                .when().put("maps/api/place/update/json")
                .then().assertThat().log().all().statusCode(200)
                .body("msg",equalTo("Address successfully updated"));

        //Get Place

        String getPlaceResponse = given().log().all().queryParam("key", "qaclick123")
                .queryParam("place_id", placeId)
                .when().get("maps/api/place/get/json")
                .then().assertThat().log().all().statusCode(200)
                .extract().response().asString();


        JsonPath js1 = reUsableMethods.rawToJson(getPlaceResponse);
        String actualAddress = js1.getString("address");

        //System.out.println(actualAddress);
        Assert.assertEquals(actualAddress, newAddress);

        String response2 = given().log().all().queryParam("key", "qaclick123").header("Content-Type0", "application/json")
                .body(new String (Files.readAllBytes(Paths.get("C:\\Users\\elif.parlak\\AddPlace.json")))).when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200).body("scope", equalTo("APP")).header("server", "Apache/2.4.41 (Ubuntu)")
                .extract().response().asString();
        System.out.println(response2);
        JsonPath js2 = new JsonPath(response2); //for parsing json
        String placeId2 = js2.getString("place_id");

        System.out.println(placeId2);

    }
}