package com.techproed.day04;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest02 {

    @Test
    public void test01(){
        /*
            https://restful-booker.herokuapp.com/booking url'ine
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen response'un
    status kodunun 200
    content type'inin "application/json" oldugunu test edin
         */
        String url = "https://restful-booker.herokuapp.com/booking";

        Response response = given().
                            accept("application/json").
                            when().
                            get(url);
        response.prettyPrint();
        //Assert kismi
        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON);

        System.out.println(response.getHeaders());
        System.out.println("=======");
        System.out.println(response.getHeader("Server"));//Cowboy

    }

    @Test
    public void test02(){

     /*
    https://restful-booker.herokuapp.com/booking/1001 url'ine
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen response'un
    status kodunun 404 oldugunu
    Response body sinin "Not Found" icerdigini
    ve  Response body sinin "API" icermedigini
   */
        String url ="https://restful-booker.herokuapp.com/booking/1001";

        Response response = given().
                accept("application/json").
                when().
                get(url);
        response.prettyPrint(); //Not Found

        //status kodunun 404 oldugunu
        response.then().assertThat().statusCode(404);

        //Response body sinin "Not Found" icerdigini
        //response.asString() ==> response u String cevirir
      Assert.assertTrue(response.asString().contains("Not Found"));
       //Response body sinin "API" icermedigini
      Assert.assertFalse(response.asString().contains("API"));

    }

}
