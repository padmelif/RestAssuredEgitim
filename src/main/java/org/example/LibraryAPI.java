package org.example;

import files.payload;
import files.reUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;

import static io.restassured.RestAssured.given;

public class LibraryAPI {
    @Test(dataProvider = "BooksData")
     public void addBook(String aisle, String isbn)
    {
        RestAssured.baseURI = "http://216.10.245.166";
        String res = given().log().all().header("Content-Type", "application/json").body(payload.AddBook(aisle, isbn))
                .when().post("/Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();
       JsonPath js = reUsableMethods.rawToJson(res);
       String id = js.get("ID");
       System.out.println(id);
    }
    @DataProvider(name="BooksData")
    public Object[][] getData()
    {
        return new Object[][] {{"asdf","1236"},{"vvvd","2312"},{"sfsd","3243"}};
    }
}
