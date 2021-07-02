package com.techproed.day05;

import com.techproed.testbase.TestBaseDummyRestApi;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequest09 extends TestBaseDummyRestApi {

    /*
    http://dummy.restapiexample.com/api/v1/employees
    url ine bir istek gönderildiğinde,
    status kodun 200,
    gelen body de,
    5. çalışanın isminin "Airi Satou" olduğunu ,
    6. çalışanın maaşının "372000" olduğunu ,
    Toplam 24 tane çalışan olduğunu,
    "Rhona Davidson" ın employee lerden biri olduğunu
    "21", "23", "61" yaşlarında employeeler olduğunu test edin
     */


    @Test
    public void test01() {
        spec3.pathParam("parametre1", "employees");

        Response response = given().
                accept("application/json").
                spec(spec3).
                when().get("/{parametre1}");
        response.prettyPrint();

        //status kodun 200,
        Assert.assertEquals(200,response.getStatusCode());

//        5. çalışanın isminin "Airi Satou" olduğunu ,
        JsonPath json = response.jsonPath();
        Assert.assertEquals("Airi Satou", json.getString("data.employee_name[4]"));

    //6. çalışanın maaşının "372000" olduğunu ,
        Assert.assertEquals(372000, json.getInt("data.employee_salary[5]"));

        //Toplam 24 tane çalışan olduğunu,
        System.out.println(json.getList("data.id"));
      Assert.assertEquals(24, json.getList("data.id").size());

        //"Rhona Davidson" ın employee lerden biri olduğunu
        Assert.assertTrue(json.getList("data.employee_name").contains("Rhona Davidson"));

        //"21", "23", "61" yaşlarında employeeler olduğunu test edin
        //1. yol
        Assert.assertTrue(json.getList("data.employee_age").contains(21));
        Assert.assertTrue(json.getList("data.employee_age").contains(23));
        Assert.assertTrue(json.getList("data.employee_age").contains(61));

        //2. yol ==>containsAll(yasList) =< collection dondurur
        //asList(21,23,61); cok tavsiye edilmez
         List<Integer> yasList = Arrays.asList(21,23,61);
        Assert.assertTrue(json.getList("data.employee_age").containsAll(yasList));

        //3. yol
        List<Integer> yasListesi = new ArrayList<>();
        yasListesi.add(21);
        yasListesi.add(23);
        yasListesi.add(61);
        System.out.println(yasListesi);

        Assert.assertTrue(json.getList("data.employee_age").containsAll(yasListesi));
    }
}