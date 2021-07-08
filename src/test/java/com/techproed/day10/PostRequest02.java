package com.techproed.day10;


import com.techproed.testbase.TestBaseRestFulHerOkuApp;
import com.techproed.testdata.TestDataRestFulHerOkuApp;
import io.restassured.http.ContentType;
import io.restassured.internal.RestAssuredResponseImpl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PostRequest02 extends TestBaseRestFulHerOkuApp {
    /*
    https://restful-booker.herokuapp.com/booking url ine, Request Body olarak
{ "firstname": "Selim",
               "lastname": "Ak",
               "totalprice": 11111,
               "depositpaid": true,
               "bookingdates": {
                   "checkin": "2020-09-09",
                   "checkout": "2020-09-21"
                }
 }gönderildiğinde, Status kodun 200 olduğunu ve dönen response body nin ,
      "booking": {
         "firstname": " Selim ",
         "lastname": " Ak ",
         "totalprice":  11111,
         "depositpaid": true,
         "bookingdates": {
             "checkin": "2020-09-01",
              "checkout": " 2020-09-21”
         },
        }
olduğunu test edin
     */



    @Test
    public void test(){
        //url olustur
        spec2.pathParam("parametre", "booking");

        //request body olustur
        TestDataRestFulHerOkuApp testData= new TestDataRestFulHerOkuApp();
        JSONObject requestBodyRequestObject = testData.setUpTestRequestData2();
        System.out.println(requestBodyRequestObject);
        //{"firstname":"Selim","bookingdates":{"checkin":"2020-09-09","checkout":"2020-09-21"},"totalprice":11111,"depositpaid":true,"lastname":"Ak"}

        //response
        Response response =given()
                .contentType(ContentType.JSON)
                //.accept("application/json")
                .spec(spec2)
                .body(requestBodyRequestObject.toString())
                .auth()
                .basic("admin", "password123")
                .when().post("/{parametre}")
                ;
        response.prettyPrint();

                //DE-SERILIZATION
        //actual data
        HashMap<String, Object> actualDataMap = response.as(HashMap.class);
        System.out.println(actualDataMap);

        //requestBodyRequestObject => ***expectedData olarak kullaniyoruz burada**
        //firtsname
        Assert.assertEquals(requestBodyRequestObject.getString("firstname"),
                ((Map)actualDataMap.get("booking")).get("firstname"));
        //lastname
        Assert.assertEquals(requestBodyRequestObject.getString("lastname"),
                ((Map)actualDataMap.get("booking")).get("lastname"));
        //totalprice
        Assert.assertEquals(requestBodyRequestObject.getInt("totalprice"),
                ((Map)actualDataMap.get("booking")).get("totalprice"));
        //depositpaid
        Assert.assertEquals(requestBodyRequestObject.getBoolean("depositpaid"),
                ((Map)actualDataMap.get("booking")).get("depositpaid"));

        //bookingdates ==> getJSONObject("bookingdates") olarak kullandik cunku bunun  icine girecegiz,
        Assert.assertEquals(requestBodyRequestObject.getJSONObject("bookingdates").getString("checkin"),
                ((Map)((Map)actualDataMap.get("booking")).get("bookingdates")).get("checkin"));

        Assert.assertEquals(requestBodyRequestObject.getJSONObject("bookingdates").getString("checkout"),
                ((Map)((Map)actualDataMap.get("booking")).get("bookingdates")).get("checkout"));


                    //JSON PATH ile
        JsonPath jsonPath = response.jsonPath();

        //firtsname
        Assert.assertEquals(requestBodyRequestObject.getString("firstname"),
                jsonPath.getString("booking.firstname"));
        System.out.println(jsonPath.getString("booking.firstname"));

        //lastname
        Assert.assertEquals(requestBodyRequestObject.getString("lastname"),
                jsonPath.getString("booking.lastname"));
        System.out.println(jsonPath.getString("booking.lastname"));

        //totalprice
        Assert.assertEquals(requestBodyRequestObject.getInt("totalprice"),
                jsonPath.getInt("booking.totalprice"));
            System.out.println(jsonPath.getInt("booking.totalprice"));
        //depositpaid
        Assert.assertEquals(requestBodyRequestObject.getBoolean("depositpaid"),
                jsonPath.getBoolean("booking.depositpaid"));
        System.out.println(jsonPath.getBoolean("booking.depositpaid"));

        //bookingdates ==> getJSONObject("bookingdates") olarak kullandik cunku bunun  icine girecegiz,
        Assert.assertEquals(requestBodyRequestObject.getJSONObject("bookingdates").getString("checkin"),
                jsonPath.getMap("booking.bookingdates").get("checkin"));
        System.out.println(jsonPath.getMap("booking.bookingdates").get("checkin"));

        Assert.assertEquals(requestBodyRequestObject.getJSONObject("bookingdates").getString("checkout"),
                jsonPath.getMap("booking.bookingdates").get("checkout"));
        System.out.println(jsonPath.getMap("booking.bookingdates").get("checkout"));

    }

}
