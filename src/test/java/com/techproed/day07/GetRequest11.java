package com.techproed.day07;

import com.techproed.testbase.TestBaseJsonPlaceHolder;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class GetRequest11 extends TestBaseJsonPlaceHolder {

    /*
    https://jsonplaceholder.typicode.com/api/todos/2 url ‘ine istek gönderildiğinde,
 Dönen response un
 Status kodunun 200, dönen body de,
       "completed": değerinin false
       "title”: değerinin “quis ut nam facilis et officia qui”
       "userId"  sinin 1 ve header değerlerinden
 "Via" değerinin “1.1 vegur” ve
       "Server" değerinin “cloudflare” olduğunu test edin…
     */

    @Test
    public void getRequest(){
        //1. url olustrur
        spec01.pathParams("p1","todos",
                "p2", 2);
        //2. expected data olustur
        HashMap<String, Object> expectedDataMap = new HashMap<>();
        expectedDataMap.put("statusCode", 200);
        expectedDataMap.put("completed", false);
        expectedDataMap.put("userId",1);
        expectedDataMap.put("title", "quis ut nam facilis et officia qui");
        expectedDataMap.put("Via","1.1 vegur");
        expectedDataMap.put("Server", "cloudflare");

        System.out.println("expectedData : \n" + expectedDataMap);


        //3.Request gonder
       Response response = given().
                accept("application/json").
                spec(spec01).when().get("/{p1}/{p2}");
       response.prettyPrint();

        //ACTUAL DATA olustur //DE-SERILIZATION
       //4. Responsu map icine atacagiz.bu da actula data olacak
        //json format-> java objesine donustur
        //Response u HashMap gib i yap ve yaptigin bu HashMap i actualDataMap a at.
        HashMap<String, Object> actualDataMap = response.as(HashMap.class); //apiden gelen response body

        System.out.println("Actual Data : \n" + actualDataMap);

        //5. Asseert
        Assert.assertEquals(expectedDataMap.get("statusCode"), response.getStatusCode());
        Assert.assertEquals(expectedDataMap.get("completed"), actualDataMap.get("completed"));
        Assert.assertEquals(expectedDataMap.get("title"), actualDataMap.get("title"));
        Assert.assertEquals(expectedDataMap.get("userId"), actualDataMap.get("userId"));
        Assert.assertEquals(expectedDataMap.get("Via"), response.getHeader("Via"));
        Assert.assertEquals(expectedDataMap.get("Server"), response.getHeader("Server"));
        // Assert.assertEquals("application/json; charset=utf-8",response.contentType());


    }


}
