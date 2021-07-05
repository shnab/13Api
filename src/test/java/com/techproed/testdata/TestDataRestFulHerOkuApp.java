package com.techproed.testdata;

import java.util.HashMap;

public class TestDataRestFulHerOkuApp {
    /*
      {
   "firstname": "Eric",
   "lastname": "Smith",
   "totalprice": 555,
   "depositpaid": false,
   "bookingdates": {
       "checkin": "2016-09-09",
       "checkout": "2017-09-21"
    }
}
     */

    // Kirmizi olursa==> public static HashMap<String, Object> setUpTestData(){
    public HashMap<String, Object> setUpTestData(){

        //bookingdates icerisnde ayri bir map var.Onun icin farkli bir map olusturacagiz.
        HashMap<String, String> bookingdatesMap =new HashMap<>();
        bookingdatesMap.put("checkin", "2018-11-27");
        bookingdatesMap.put("checkout", "2020-03-05");

        HashMap<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", "Eric");
        expectedData.put("lastname", "Smith");
        expectedData.put("totalprice", 832);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates", bookingdatesMap);//ilk olusturdugumuz bookingdatesMap i buraya aldik

        return  expectedData;
    }
}
