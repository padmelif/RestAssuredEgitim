package org.example;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.Category;
import pojo.Root;
import pojo.Tags;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PetstoreCrudTests {

    public static void main (String[] args) {


            Category category = new Category();
            category.setId(0);
            category.setName("category");

            List<Tags> tagObs = new ArrayList<>();
            Tags tag = new Tags();
            tag.setId(1);
            tag.setName("tagString");
            tagObs.add(tag);

        RestAssured.baseURI = "https://petstore.swagger.io/";
        Root body = new Root();
        body.setId(10);
        body.setPhotoUrls(null);
        body.setName("corgi");
        body.setStatus("available");
        body.setCategory(category);
        body.setTags(tagObs);

        Response res = given(). header("Content-Type", "application/json")
                .body(body)
                .when().post("v2/pet")
                .then().assertThat().statusCode(200).extract().response();
        String responseString = res.asString();
        System.out.println(responseString);

        body.setName("cont");
        Response res2 = given(). header("Content-Type", "application/json")
                .body(body)
                .when().put("v2/pet")
                .then().assertThat().statusCode(200).extract().response();
        String res2String = res2.asString();
        System.out.println(res2String);

    }
}
