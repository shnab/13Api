package com.techproed.day11;

import com.techproed.testbase.TestBaseJsonPlaceHolder;
import com.techproed.testdata.TestDataJsonPlaceHolder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PatchRequest01 extends TestBaseJsonPlaceHolder {
    /*
    https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body gönderdiğimde
   {

      "title": "API calismaliyim"

     }
        Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
        {
         "userId": 10,
         "title": "API calismaliyim"
         "completed": true,
         "id": 198
        }
             */

    //Sadece degistirmek istedigimiz kismi gonderiyoruz.

    @Test
    public void test() {
        spec01.pathParams("parametre1", "todos",
                "parametre2", 198);

        //request body ==request yaparken kulanacagiz
        TestDataJsonPlaceHolder testData = new TestDataJsonPlaceHolder();
        JSONObject requestBody = testData.setUpPatchData01();
        System.out.println(requestBody); //{"title":"Api Calismaliyim"}

        //response olustur gonder
        Response response = given()
                .contentType(ContentType.JSON)
                .spec(spec01)
                .auth()
                .basic("admin", "password123")
                .body(requestBody.toString())
                .when()
                .patch("/{parametre1}/{parametre2}");
        response.prettyPrint(); //{
                                //        "userId": 10,
                                //                "id": 198,
                                //                "title": "Api Calismaliyim",
                                //                "completed": true
                                //    }

        //expected data ==> request body'den farkli oldugu icin olusturduk
        JSONObject expectedData= testData.setUpExpectedBodyPatch();
        System.out.println(expectedData);//{"completed":true,"title":"API calismaliyim","userId":10}

        //Actual data
        //DE serilization ile
        HashMap<String, Object> actualDataMap =response.as(HashMap.class);
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(expectedData.getInt("userId"), actualDataMap.get("userId"));
        Assert.assertEquals(expectedData.getString("title"), actualDataMap.get("title"));
        Assert.assertEquals(expectedData.getBoolean("completed"), actualDataMap.get("completed"));

        //JsonPath ile
        JsonPath actualData =response.jsonPath();
        Assert.assertEquals(200, response.getStatusCode());
          Assert.assertEquals(expectedData.getInt("userId"), actualData.getInt("userId"));
        Assert.assertEquals(expectedData.getString("title"), actualData.getString("title"));
        Assert.assertEquals(expectedData.getBoolean("completed"), actualData.getBoolean("completed"));



    }

}
