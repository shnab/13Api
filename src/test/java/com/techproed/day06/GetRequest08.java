package com.techproed.day06;

import com.techproed.testbase.TestBaseDummyRestApi;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest08 extends TestBaseDummyRestApi {
    /*
    http://dummy.restapiexample.com/api/v1/employees url’inde bulunan
   1) Butun calisanlarin isimlerini consola yazdıralim
   2) 3. calisan kisinin ismini konsola yazdıralim
   3) Ilk 5 calisanin adini konsola yazdiralim
   4) En son calisanin adini konsola yazdiralim
     */

    @Test
    public void test01(){
        spec3.pathParams("parametre1", "employees");

        Response response =given().
                accept("application/json").
                spec(spec3).when().get("/{parametre1}");
        //response.prettyPrint();

//        1) Butun calisanlarin isimlerini consola yazdıralim
        JsonPath json = response.jsonPath();
        //burada response daki tum isimleri tek bir deger olarak aldi ve dondu

        System.out.println(json.getString("data.employee_name"));
        System.out.println(json.getList("data.employee_name"));

//        2) 3. calisan kisinin ismini konsola yazdıralim
        System.out.println(json.getString("data.employee_name[2]"));//Ashton Cox

//        3) Ilk 5 calisanin adini konsola yazdiralim
        System.out.println(json.getString("data.employee_name[0,1,2,3,4]"));

//        4) En son calisanin adini konsola yazdiralim
        System.out.println(json.getString("data.employee_name[-1]"));///Doris Wilder

    }



}
