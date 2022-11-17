package org.example;

import files.payload;
import io.restassured.path.json.JsonPath;

public class complexJsonParse {
    public static void main(String[] args) {
        JsonPath js = new JsonPath(payload.CoursePrice());

        //Print No of courses returned by API
        int count = js.getInt("courses.size()");
        System.out.println(count);
        //Print Purchase Amount
        int totalAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println(totalAmount);
        //. Print Title of the first course
        String FirstTitleOfCourses = js.getString("courses.title[0]");
        System.out.println(FirstTitleOfCourses);
        //Print All course titles and their respective Prices
        String courseTitles= js.getString("courses.title");
        System.out.println(courseTitles);
        String CoursePrices = js.getString("courses.price");
        System.out.println(CoursePrices);

        for(int i=0; i<count; i++)
        {
            String titlesOfCourses = js.get("courses["+i+"].title");
            System.out.println(titlesOfCourses);
            System.out.println(js.get("courses["+i+"].price").toString());
        }
        //Print no of copies sold by RPA Course
        for(int i=0; i<count; i++)
        {
            String titlesOfCourses = js.get("courses["+i+"].title");
            if (titlesOfCourses.equalsIgnoreCase("RPA"))
            {
               int copies = js.get("courses["+i+"].copies");
               System.out.println(copies);
               break;
            }
        }

        //Verify if Sum of all Course prices matches with Purchase Amount
    }
}
