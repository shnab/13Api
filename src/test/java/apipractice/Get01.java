package apipractice;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import com.techproed.testbase.TestBaseRestFulHerOkuApp;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Get01 extends TestBaseRestFulHerOkuApp {
    /*
         Positive Scenario:
         When Asagidaki Endpoint'e bir GET request yolladim
         https://restful-booker.herokuapp.com/booking/10
     And Accept type “application/json” dir
     Then
     HTTP Status Code 200
     And Response format "application/json"
     And firstname "Mark"
     And lastname "Wilson"
     And depositpaid true
     And checkin date "2016-06-19"
     And checkout date "2019-08-26"

     1.Adim Url'i olustur
     2.Adim Data'yi oluştur
     3.Adim Request gönder
     4.Adim Validation yap
        */
    @Test
    public void getRequest(){

        //1. url olustur
        String endPoint = "https://restful-booker.herokuapp.com/booking/10";

        //request gonder
        Response response = RestAssured.given().
                accept("application/json").
                when().
                get(endPoint );
        response.prettyPrint();

      //  Assert.assertEquals(200,response.getStatusCode());
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON); //"application/json"

        //Body validation verify
        response.then().assertThat().body("firstname", equalTo("Eric"),
                "lastname", equalTo("Brown"),
                "depositpaid", equalTo(false),
                "bookingdates.checkin", equalTo("2020-11-06"),
                "bookingdates.checkout", equalTo("2020-11-24"));

        JsonPath jsonPath = response.jsonPath();
        //Verinin tipini bilmiyorsak jsonPath.get() kullanilabilir
        Assert.assertEquals("Susan", jsonPath.getString("firstname"));
        Assert.assertEquals("Jockson", jsonPath.getString("lastname"));
        Assert.assertEquals(945, jsonPath.getInt("totalprice"));
        Assert.assertEquals(true, jsonPath.getBoolean("depositpaid"));
        Assert.assertEquals("2015-07-30", jsonPath.getString("bookingdates.checkin"));
        Assert.assertEquals("2020-03-02", jsonPath.getString("bookingdates.checkout"));


    }

}
