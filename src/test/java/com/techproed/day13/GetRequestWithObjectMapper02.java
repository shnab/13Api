package com.techproed.day13;

import com.techproed.testbase.TestBaseRestFulHerOkuApp;
import com.techproed.utilities.JsonUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequestWithObjectMapper02 extends TestBaseRestFulHerOkuApp {

    /*
    https://restful-booker.herokuapp.com/booking/2 url’ine bir get request gönderildiğinde,
status kodun 200 ve response body’nin
{
   "firstname": "Mark",
   "lastname": "Wilson",
   "totalprice": 284,
   "depositpaid": false,
   "bookingdates": {
       "checkin": "2016-08-10",
       "checkout": "2018-06-22"
   }
}
     */

    @Test
    public void test(){
        //Query param olursa boyle kullaniyoruz.
       // https://restful-booker.herokuapp.com/booking/2?a=b
       // spec2.pathParams("pr1", "booking", "pr2", 2).queryParam("a","b");
        spec2.pathParams("pr1", "booking", "pr2", 2);

        //String cevir
        String jsonData = "{\n" +
                "   \"firstname\": \"Jim\",\n" +
                "   \"lastname\": \"Ericsson\",\n" +
                "   \"totalprice\": 544,\n" +
                "   \"depositpaid\": false,\n" +
                "   \"bookingdates\": {\n" +
                "       \"checkin\": \"2021-02-16\",\n" +
                "       \"checkout\": \"2021-05-10\"\n" +
                "   }\n" +
                "}";
        //expected data
        HashMap<String, Object> expectedDataMap = JsonUtil.convertJsonToJava(jsonData, HashMap.class);
        System.out.println(expectedDataMap);

        //request gonder
        Response response =given()
                .contentType(ContentType.JSON)
                .spec(spec2)
                .when()
                .get("/{pr1}/{pr2}");
        response.prettyPrint();

        //Actual data
        HashMap<String ,Object> actualDataMap = JsonUtil.convertJsonToJava(response.asString(), HashMap.class );
        System.out.println(actualDataMap);

        //boyle bakilmaz
        System.out.println(expectedDataMap.get("checkin")); //null

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(expectedDataMap.get("firstname"), actualDataMap.get("firstname"));
        Assert.assertEquals(expectedDataMap.get("lastname"), actualDataMap.get("lastname"));
        Assert.assertEquals(expectedDataMap.get("totalprice"), actualDataMap.get("totalprice"));
        Assert.assertEquals(expectedDataMap.get("depositpaid"), actualDataMap.get("depositpaid"));
        Assert.assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkin"),
                ((Map)actualDataMap.get("bookingdates")).get("checkin"));
        Assert.assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkout"),
                ((Map)actualDataMap.get("bookingdates")).get("checkout"));

    }

}
