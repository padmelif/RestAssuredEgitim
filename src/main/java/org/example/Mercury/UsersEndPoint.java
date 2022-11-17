package org.example.Mercury;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.response.Response;
import pojo.CreateUser;
import pojo.Token;

import static io.restassured.RestAssured.given;

public class UsersEndPoint {

    public static void main(String[] args) throws JsonProcessingException {


        CreateUser addUser = new CreateUser();
        addUser.setType("ADMIN");
        addUser.setFirstName("Elif");
        addUser.setLastName("Parlak");
        addUser.setEmail("epr1365999@epr.com");
        addUser.setTitle("QA");
        addUser.setPhoneCountryCode(1);
        addUser.setPhoneNumber("5055555555");
        addUser.setPhoneNumberExtension("1111");
        addUser.setMobilePhoneCountryCode(1);
        addUser.setMobilePhoneNumber("5055655656");

//        String response;
//        response = given().body("{username: \"user@testcompany.com\", password: \"TEst123*\"}")
//                .when().post("https://bff-b2c-api-test-titan.shipmercury.io/api/v1/bff/b2c/users/insecure/auth/access-token")
//                .then().assertThat().statusCode(200).extract().response().asString();
//        JsonPath jsonPath = new JsonPath(response);
//        String accessToken = jsonPath.getString("accessToken");
//
//        System.out.println(accessToken);

        //Get AccessToken
        SessionFilter session=new SessionFilter();
        String js1 =given().relaxedHTTPSValidation().header("Content-Type","application/json").body("{\r\n" +
                "    \"username\": \"user@testcompany.com\",\r\n" +
                "    \"password\": \"Test12345*\"\r\n" +
                "}").filter(session).when().post("https://bff-b2c-api-test-titan.shipmercury.io/api/v1/bff/b2c/users/insecure/auth/access-token")
                .then().extract().response().asString();

        ObjectMapper mapper = new ObjectMapper();
        Token obje = mapper.readValue(js1, Token.class);
        String accessToken = obje.getData().getAccessToken();
        System.out.println("accessToken = " + accessToken);


    RestAssured.baseURI = "https://api-test-titan.shipmercury.io/api/v1";

        //Create User
     Response createUser = given()
               .log().all().header("Authorization","Bearer "+ accessToken)
               .header("Content-Type","application/json")
               .body(addUser)
               .when().post("/companies/4/users")
               .then().log().all().assertThat().statusCode(400).extract().response();
       String responseString = createUser.asString();

       //List Users
       Response listUser =given()
               .log().all().header("Authorization","Bearer "+ accessToken)
               .when().get("/companies/4/users")
               .then().log().all().assertThat().statusCode(400).extract().response();

       //Update User
       Response updateUser =given()
               .log().all().header("Authorization","Bearer "+ accessToken)
               .header("Content-Type","application/json")
               .body(addUser)
               .when().put("https://bff-b2c-api-test-titan.shipmercury.io/api/v1/bff/b2c/companies/users/3")
               .then().log().all().assertThat().statusCode(200).extract().response();


     //Deactivate User

     //   Response updateUser =given()
     //           .log().all().header("Authorization","Bearer "+ accessToken)
     //           .when().patch("https://bff-b2c-api-dev-titan.shipmercury.io/api/v1/bff/b2c/companies/users/3/userStatus/INACTIVE")
     //           .then().log().all().assertThat().statusCode(200).extract().response();
    }
}
