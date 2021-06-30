package com.techproed.day04;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRequest03 {

    /*
    https://restful-booker.herokuapp.com/booking/7 url'ine
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen response'un
    status kodunun 200
    ve content type'inin "application/json"
    ve firstname'in "Mark"
    ve lastname'in "Smith"
    ve checkin date'in 2018-03-12"
    ve checkout date'in 2019-05-30 oldugunu test edin
     */

    @Test
    public void test01(){
        String url = "https://restful-booker.herokuapp.com/booking/7";

        Response response= given().accept("application/json").when().get(url);

        response.prettyPrint();
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname", equalTo("Eric")).
                body("lastname", equalTo("Brown")).
                body("bookingdates.checkin", equalTo("2019-10-16")).
                body("bookingdates.checkout", equalTo("2021-01-15")).
                body("additionalneeds", equalTo("Breakfast"));

        //import static org.hamcrest.Matchers.equalTo; ile Mtchers import etmis olduk
        //artik nubu yazmadan bu classtaki metodlari kullanabiliriz
       response.
               then().
               assertThat().
               statusCode(200).
               contentType(ContentType.JSON).
               body("firstname", equalTo("Eric"),
                       "lastname", equalTo("Brown"),
                       "depositpaid", equalTo(false),
                       "bookingdates.checkin", equalTo("2019-10-16"),
                       "bookingdates.checkout", equalTo("2021-01-15"),
                       "additionalneeds", equalTo("Breakfast"));


    }
}
