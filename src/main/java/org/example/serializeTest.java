package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class serializeTest {

    public static void main(String[] args){

        RestAssured.baseURI = "https://rahulshettyacademy.com";

        AddPlace p = new AddPlace();
        p.setAccuracy(50);
        p.setAddress("29, side layout, cohen 09");
        p.setLanguage("French-IN");
        p.setPhone_number("+90532 716 28 74");
        p.setWebsite("https://rahulsehttyacademy.com");
        p.setName("Elif Parlak");
        List<String > mylist = new ArrayList<String>();
        mylist.add("shoe park");
        mylist.add("shop");
        p.setTypes(mylist);
        Location l = new Location();
        l.setLat(-38.3236);
        l.setLng(33.427362);
        p.setLocation(l);

        Response res = given().queryParam("key", "qaclick123")
                .body(p)
                .when().post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200).extract().response();
        String responseString = res.asString();
        System.out.println(responseString);

    }

}
