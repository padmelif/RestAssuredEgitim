package org.example;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import pojo.Api;
import pojo.GetCourse;
//import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class OAuth {
    public static void main (String[] args){

        String [] courseTitles = {"Selenium Webdriver Java", "Cypress", "Protractor"};

        String url ="https://rahulshettyacademy.com/getCourse.php?code=4%2F0ARtbsJpRmODU2kryVq1ZeG1rv6yhG40mcv-BDTvZtop5XfeBSPXeNehemVOBkpAhR_6URQ&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
        String partialcode=url.split("code=")[1];
        String code=partialcode.split("&scope")[0];
        System.out.println(code);

       // System.setProperty("webdriver.driver.chrome", "c://chromedriver.exe");
       // WebDriver driver = new ChromeDriver();
       // driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
       // driver.findElement(By.xpath("input[type='e-mail']")).sendKeys("padmelif@gmail.com");
       // driver.findElement(By.xpath("input[type='e-mail']")).sendKeys(Keys.ENTER);
       // Thread.sleep(3000);
       // driver.findElement(By.xpath("input[type='password']")).sendKeys("password");
       // driver.findElement(By.xpath("input[type='password']")).sendKeys(Keys.ENTER);
       // Thread.sleep(4000);
       // String url = driver.getCurrentUrl();
       // String partialcode = url.split("code=")[1];
       // String code = partialcode.split("&scope")[0];
       // System.out.println(code);


        String response =
                given()

                        .urlEncodingEnabled(false)
                        .queryParams("code",code)
                        .queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                        .queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                        .queryParams("grant_type", "authorization_code")
                        //.queryParams("state", "verifyfjdss")
                        //.queryParams("session_state", "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")
                        // .queryParam("scope", "email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email")
                        .queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
                        .when().log().all()
                        .post("https://www.googleapis.com/oauth2/v4/token").asString();

        // System.out.println(response);

        JsonPath jsonPath = new JsonPath(response);

        String accessToken = jsonPath.getString("access_token");

        System.out.println(accessToken);

        //String r2 =
        GetCourse gc=
                given().

                queryParam("access_token", accessToken)
                        .expect()
                        .defaultParser(Parser.JSON)

                .when()

                .get("https://rahulshettyacademy.com/getCourse.php")
                        .as(GetCourse.class);

                //.asString();


        //System.out.println(r2);
        System.out.println(gc.getInstructor());
        System.out.println(gc.getLinkedIn());
        System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());

        List<Api> apiCourses = gc.getCourses().getApi();
            for(int i=0; i<apiCourses.size() ;i++)
            {
                if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
                {
                    System.out.println(apiCourses.get(i).getPrice());
                }
            }

            //Get the course names of WebAutomation
        ArrayList<String> actual = new ArrayList<String >();
            List<pojo.WebAutomation> w = gc.getCourses().getWebAutomation();

            for (int j=0; j<w.size(); j++)
            {
                actual.add(w.get(j).getCourseTitle());
            }

         List<String> expectedList = Arrays.asList(courseTitles);

        Assert.assertTrue(actual.equals(expectedList));
    }
}