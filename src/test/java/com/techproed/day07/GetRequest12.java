package com.techproed.day07;

import com.techproed.testbase.TestBaseRestFulHerOkuApp;
import com.techproed.testdata.TestDataRestFulHerOkuApp;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest12 extends TestBaseRestFulHerOkuApp {

    /*
    https://restful-booker.herokuapp.com/booking/1 url ine bir istek gönderildiğinde
 dönen response body nin
  {
   "firstname": "Eric",
   "lastname": "Smith",
   "totalprice": 555,
   "depositpaid": false,
   "bookingdates": {
       "checkin": "2016-09-09",
       "checkout": "2017-09-21"
    }
} gibi olduğunu test edin.
     */

    @Test
    public void test01(){
        spec2.pathParams("param1","booking",
                "param2", "1");
        Response response = given().
                accept("application/json").
                spec(spec2).
                when().
                get("/{param1}/{param2}");
        response.prettyPrint();

        //Expected data
        TestDataRestFulHerOkuApp testDataRestFulHerOkuApp =new TestDataRestFulHerOkuApp();
        System.out.println("ExpectedData: \n" + testDataRestFulHerOkuApp.setUpTestData());
        HashMap<String, Object> expectedDataMap = testDataRestFulHerOkuApp.setUpTestData();

        //Response u HashMap gib i yap ve yaptigin bu HashMap i actualDataMap a at.
        HashMap<String, Object> actualDataMap = response.as(HashMap.class); //api den gelen response body

        System.out.println("Actual Data : \n" + actualDataMap);

        //5. Asseert
        Assert.assertEquals(expectedDataMap.get("firstname"), actualDataMap.get("firstname"));
        Assert.assertEquals(expectedDataMap.get("lastname"), actualDataMap.get("lastname"));
        Assert.assertEquals(expectedDataMap.get("totalprice"), actualDataMap.get("totalprice"));
        Assert.assertEquals(expectedDataMap.get("depositpaid"), actualDataMap.get("depositpaid"));
        //bu sekilde yapmiyoruz
//        Assert.assertEquals(expectedDataMap.get("bookingdates.checkin"), actualDataMap.get("bookingdates.checkin"));
//        Assert.assertEquals(expectedDataMap.get("bookingdates.checkout"), actualDataMap.get("bookingdates.checkout"));

        //Burada MAP casting yapiyoruz ve bunun Map oldugunu belirtiyoruz
        //biz get methodu ile iceride ki veriye ulasmamiz lazim.
        // Get methodu da Map’lerde var.
        // Bu yuzden bizim map’e cevirmem gerekiyor ki get methodu kullanarak ic taraftaki checkin bilgisine ulasabileyim
        Assert.assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkin"),
                ((Map)actualDataMap.get("bookingdates")).get("checkin"));
        Assert.assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkout"),
                ((Map)actualDataMap.get("bookingdates")).get("checkout"));


    }
}
