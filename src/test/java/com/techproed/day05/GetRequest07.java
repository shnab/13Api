package com.techproed.day05;

import com.techproed.testbase.TestBaseJsonPlaceHolder;
import com.techproed.testbase.TestBaseRestFulHerOkuApp;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRequest07 extends TestBaseRestFulHerOkuApp {

    /*
    https://restful-booker.herokuapp.com/booking/5 url'ine bir request yolladigimda
     	HTTP Status Code'unun 200
     	ve response content type'inin "application/JSON" oldugunu
			ve response body'sinin asagidaki gibi oldugunu test edin
				{"firstname": Sally,
     			"lastname": "Smith",
     			"totalprice": 789,
     			"depositpaid": false,
     			"bookingdates": { 	"checkin": "2017-12-11",
     	                     						"checkout":"2020-02-20" }
      		}
     */
    @Test
    public void test01(){
        spec2.pathParams("name", "booking",
                         "id", "5");
        Response response = given().
                accept("application/json").
                spec(spec2).
                when().
                get("/{name}/{id}");
        response.prettyPrint();

//        response.then().assertThat().
//                statusCode(200).
//                contentType(ContentType.JSON).
//                body("firstname", equalTo("Eric"),
//                        "lastname",equalTo("Jackson"),
//                        "totalprice",equalTo(257),
//                        "depositpaid",equalTo(true),
//                        "bookingdates.checkin",equalTo("2018-06-17"),
//                        "bookingdates.checkout", equalTo("2020-02-20"));

        response.then().assertThat().
                statusCode(200).
                contentType(ContentType.JSON);

        //JSON path classindan bir nesne olusturarak
        //response icindeki datayi alip json icine yerlestiriyoruz.  //body ixcindeki dataya ulasmak icin
    /*
    Body içindeki ifadeleri body("", Matcher.equalTo("")) ile assert etmek yerine
    JsonPath ile direk Assert.assert() edebiliyoruz.
     */
        //bu bir verify yontemi
        JsonPath json = response.jsonPath();
        Assert.assertEquals("data degisti","Eric", json.getString("firstname"));//"firstname": "Eric"
        Assert.assertEquals("Smith", json.getString("lastname"));
        Assert.assertEquals(414, json.getInt("totalprice"));
        Assert.assertEquals(false, json.getBoolean("depositpaid"));
        Assert.assertEquals("2016-04-11", json.getString("bookingdates.checkin"));
        Assert.assertEquals("2020-03-26", json.getString("bookingdates.checkout"));
     //   Assert.assertEquals(200, response.getStatusCode());

    }

}
