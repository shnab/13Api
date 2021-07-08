package com.techproed.testdata;

import org.json.JSONObject;

import java.util.HashMap;

public class TestDataJsonPlaceHolder {

    //bunu  burada tanimliyorum ki class PostRequest03 icinde testdata olmasin
    public int statusCode =201;

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


    public JSONObject setUpPostData2(){
            /*
         }
     "userId": 55,
     "title": "Tidy your room",
     "completed": false
   }
     */
        JSONObject jsonRequestBody =new JSONObject();
        jsonRequestBody.put("userId", 55);
        jsonRequestBody.put("title", "Tidy your room");
        jsonRequestBody.put("completed",false);

        return jsonRequestBody ;
    }


    public JSONObject setUpPutData01(){
            /*
              {
              "userId": 21,
              "title": "Wash the dishes",
              "completed": false
             }
     */
        JSONObject requestBody =new JSONObject();
        requestBody.put("userId", 21);
        requestBody.put("title", "Wash the dishes");
        requestBody.put("completed",false);

        return requestBody ;
    }

    public JSONObject setUpPatchData01() {
    /*
       {
      "title": "API calismaliyim"
     }
     */

         JSONObject requestBody =new JSONObject();
         requestBody.put("title", "API calismaliyim");

        return requestBody ;
    }

    public JSONObject setUpExpectedBodyPatch(){
            /*
        {
         "userId": 10,
         "title": "API calismaliyim"
         "completed": true,
         "id": 198
        }
     */
        JSONObject expectedBody =new JSONObject();
        expectedBody.put("userId", 10);
        expectedBody.put("title", "API calismaliyim");
        expectedBody.put("completed",true);

        return expectedBody;
    }

}
