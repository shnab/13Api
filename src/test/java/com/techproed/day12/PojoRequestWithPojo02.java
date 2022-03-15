package com.techproed.day12;

import com.techproed.pojos.ActualBookingPojo;
import com.techproed.pojos.BookingDatesPojo;
import com.techproed.pojos.BookingPojo;
import com.techproed.testbase.TestBaseRestFulHerOkuApp;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PojoRequestWithPojo02 extends TestBaseRestFulHerOkuApp {

    /*
    https://restful-booker.herokuapp.com/booking
    request body {
                   "firstname": "Selim",
                   "lastname": "Ak",
                   "totalprice": 15000,
                   "depositpaid": true,
                   "bookingdates": {
                       "checkin": "2020-09-09",
                       "checkout": "2020-09-21"
                    }
                 }
   Status code is 200
    response body  {
                            "bookingid": 11,
                            "booking": {
                                "firstname": "Selim",
                                "lastname": "Ak",
                                "totalprice": 15000,
                                "depositpaid": true,
                                "bookingdates": {
                                    "checkin": "2020-09-09",
                                    "checkout": "2020-09-21"
                                }
                            }
                         }
     */

    @Test
    public void test(){
        spec2.pathParam("parametre", "booking");

        //request body
        //bookingdates i booking icine yerlestirmis olduk
        BookingDatesPojo bookingdates =new BookingDatesPojo("2020-09-09","2020-09-21" );
        BookingPojo booking =new BookingPojo("Selim", "Ak",15000,true, bookingdates);
        System.out.println(booking);


        //request olustur
        Response  response =given().
                contentType(ContentType.JSON)
                .spec(spec2)
                .auth()
                .basic("admin", "password123")
                .body(booking)
                .when()
                .post("/{parametre}");
        response.prettyPrint();


        //DE-Serilization Actual data

        ActualBookingPojo actualData = response.as(ActualBookingPojo.class);
        System.out.println(actualData);

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(booking.getFirstname(), actualData.getBooking().getFirstname());
        Assert.assertEquals(booking.getLastname(), actualData.getBooking().getLastname());
        Assert.assertEquals(booking.getTotalprice(), actualData.getBooking().getTotalprice());
        Assert.assertEquals(booking.isDepositpaid(), actualData.getBooking().isDepositpaid());
        //Bu kisim hakikaten ORIJINAL
        Assert.assertEquals(booking.getBookingdates().getCheckin(),
                actualData.getBooking().getBookingdates().getCheckin());
        Assert.assertEquals(booking.getBookingdates().getCheckout(),
                actualData.getBooking().getBookingdates().getCheckout());

    }
}
