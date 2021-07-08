package com.techproed.day11;

import com.techproed.testbase.TestBaseJsonPlaceHolder;
import com.techproed.testdata.TestDataJsonPlaceHolder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PutRequest01 extends TestBaseJsonPlaceHolder {
    /*
            https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body gönerdiğimde
           {
    “userId”: 10,
    “id”: 198,
    “title”: “quis eius est sint explicabo”,
    “completed”: true
}
seklindeki kaydi;

           {
              "userId": 21,
              "title": "Wash the dishes",
              "completed": false
             }
             olarak guncelleyin/UPDATE
        Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
        {
         "userId": 21,
         "title": "Wash the dishes",
         "completed": false,
         "id": 198
        }
             */

    @Test
    public void test(){
        spec01.pathParams("parametre1", "todos",
                            "parametre2", "198");

        //expected- request data
        TestDataJsonPlaceHolder testData = new TestDataJsonPlaceHolder();
        JSONObject requestBody = testData.setUpPutData01();
        System.out.println(requestBody);

        //request gonder
        Response response = given()
                .contentType(ContentType.JSON)
                .spec(spec01)
                .auth()
                .basic("admin", "password123")
                .body(requestBody.toString())
                .when()
                .put("/{parametre1}/{parametre2}");
        response.prettyPrint();





    }



}
