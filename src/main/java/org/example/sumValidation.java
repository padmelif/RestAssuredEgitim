package org.example;

import files.payload;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class sumValidation {
    @Test
    public void SumOfCourses(){
        int sum = 0;

        JsonPath js = new JsonPath(payload.CoursePrice());
        int count = js.getInt("courses.size()");

        for(int i=0; i<count; i++)
        {
            int priceOfCourse = js.getInt("courses["+i+"].price");
            int copiesOfCourse = js.getInt("courses["+i+"].copies");
            int amount = priceOfCourse * copiesOfCourse;
                        sum = sum + amount;
         }
        System.out.println(sum);
        int purchaseAmount = js.getInt("dashboard.purchaseAmount");
        Assert.assertEquals(sum, purchaseAmount);
    }
}
