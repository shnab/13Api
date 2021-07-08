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
import static org.hamcrest.Matchers.equalTo;

public class PostRequest03 extends TestBaseJsonPlaceHolder {

    /*
    https://jsonplaceholder.typicode.com/todos URL ine aşağıdaki body gönderildiğinde,
     }
     "userId": 55,
     "title": "Tidy your room",
     "completed": false
   }
Dönen response un Status kodunun 201 ve response body nin aşağıdaki gibi olduğunu test edin
   {
     "userId": 55,
     "title": "Tidy your room",
     "completed": false,
     "id": …
    }
     */

    @Test
    public void test(){
        //url-einpoint olusturldu
        spec01.pathParam("parametre", "todos");

        //request body
        TestDataJsonPlaceHolder testData = new TestDataJsonPlaceHolder();
        JSONObject requestBody = testData.setUpPostData2();
        System.out.println(requestBody);

        //request gonder
        Response response = given()
                .contentType(ContentType.JSON)
                .spec(spec01)
                .auth()
                .basic("admin", "password123")
                .body(requestBody.toString())
                .when()
                .post("/{parametre}");
        response.prettyPrint();

        //expected ==> request body ile expected body ayni oldugu icin ayri bir expected data olusturmadik

        //DE-SERILIZATION -->Gson diger adi.
        HashMap<String, Object>  actualDataMap = response.as(HashMap.class);
        System.out.println(actualDataMap);

        //Assert.assertEquals(201, response.getStatusCode());
        Assert.assertEquals(testData.statusCode, response.getStatusCode());
        Assert.assertEquals(requestBody.getInt("userId"), actualDataMap.get("userId"));
        Assert.assertEquals(requestBody.getString("title"), actualDataMap.get("title"));
        Assert.assertEquals(requestBody.getBoolean("completed"), actualDataMap.get("completed"));

        //JsonPath
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(requestBody.getInt("userId"), jsonPath.getInt("userId"));
        Assert.assertEquals(requestBody.getString("title"), jsonPath.getString("title"));
        Assert.assertEquals(requestBody.getBoolean("completed"), jsonPath.getBoolean("completed"));


        //Matchers
        response.then()
                .assertThat().statusCode(testData.statusCode)
                .body("userId", equalTo(requestBody.getInt("userId"))
                ,"title", equalTo(requestBody.getString("title"))
                ,"completed", equalTo(requestBody.getBoolean("completed")));




    }
}
