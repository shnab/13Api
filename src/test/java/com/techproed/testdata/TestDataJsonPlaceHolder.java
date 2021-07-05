package com.techproed.testdata;

import java.util.HashMap;

public class TestDataJsonPlaceHolder {

    //deger dondurebilmesi icin return type HashMap<String, Object>
    //expectedDataMap dondurduk
    public HashMap<String, Object> setUpTestData(){

        //expectedData ==>expectedDataMap idi aslinda bu
    HashMap<String, Object> expectedData = new HashMap<>();
        expectedData.put("statusCode", 200);
        expectedData.put("completed", false);
        expectedData.put("userId",1);
        expectedData.put("title", "quis ut nam facilis et officia qui");
        expectedData.put("Via","1.1 vegur");
        expectedData.put("Server", "cloudflare");

        return expectedData;
    }
}
