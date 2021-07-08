package com.techproed.day10;

import com.techproed.testbase.TestBaseDummyRestApi;
import com.techproed.testdata.TestDataDummy;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PostRequest01 extends TestBaseDummyRestApi {

    /*
    http://dummy.restapiexample.com/api/v1/create url ine, Request Body olarak
{
    "name":"Ahmet Aksoy",
           "salary":"1000",
           "age":"18",
           "profile_image": ""
}
gönderildiğinde, Status kodun 200 olduğunu ve dönen response body nin ,
{
   "status": "success",
           "data": {
        “id”:…
   },
   "message": "Successfully! Record has been added."
}
olduğunu test edin
     */

    @Test
    public void test(){

        //url ==>endpoint olusturuyoruz.
        spec3.pathParam("parametre", "create");

        //expected datadan once PORT yaparken
        // request body gonderdigim icin onu olusturmaliyim.
        TestDataDummy testDataDummy = new TestDataDummy();
       HashMap<String, Object>  requestBodyMap= testDataDummy.setUpPostData3();
        System.out.println(requestBodyMap); //{profile_image=, name=Ahmet Aksoy, salary=1000, age=18}

        //expected data olustur
        HashMap<String, Object> expectedDataMap = testDataDummy.setUpTestData4();
        System.out.println(expectedDataMap);

        //request olustur
        //AUTHorization ==> sizin yetki ve neler yapacaginiza dair sinirlari belirleyen yapi
                           //post hakkim olabilir ama delete olmayabilir.
        Response response =given().
                            accept("application/json").
                            spec(spec3).
                            body(requestBodyMap).//request body'yi yolluyoruz.
                            auth().//burada autherization yapacagiz
                            basic("admin", "password123")//basic auth yapacagiz. username, passsword
                            .when().post("/{parametre}");
        response.prettyPrint();

        //actual data
        HashMap<String, Object> actualDataMap = response.as(HashMap.class);

        //Assert
        //Status kodun 200 olduğunu
        Assert.assertEquals(expectedDataMap.get("statusCode"), response.getStatusCode());
        System.out.println(response.getStatusCode());//200

        //body ==>status
        Assert.assertEquals(expectedDataMap.get("status"), actualDataMap.get("status"));
        System.out.println(actualDataMap.get("status"));//success

        //message ==>Successfully! Record has been added.
        Assert.assertEquals(expectedDataMap.get("message"), actualDataMap.get("message"));
        System.out.println(actualDataMap.get("message"));//Successfully! Record has been added.

    }

}
