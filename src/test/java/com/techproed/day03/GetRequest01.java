package com.techproed.day03;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest01 {

    /*
    GetRequest01:
    https://restful-booker.herokuapp.com/booking/3 adresine bir request gonderildiginde donecek cevap(response) icin
    HTTP status kodunun 200
    Content Type’in Json
    Ve Status Line’in HTTP/1.1 200 OK
    Oldugunu test edin.
     */

    @Test
    public void test01(){
        //1.url belirleme
        String url = "https://restful-booker.herokuapp.com/booking/3";

        //2-expected result
        //
        //3-request gonder
        Response response=
                given().
                accept("application/json").
                when().
                get(url);
        //api ile print etmek icin
        response.prettyPrint();

        //4-response al(actual result)
        //body testi yapmadigimiz icin actual result almiyoruz

        //5-assertion islemini yapma
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                statusLine("HTTP/1.1 200 OK");

        System.out.println("StatusCode: " +  response.getStatusCode());
        System.out.println("statusLine : " + response.statusLine());
        System.out.println("contentType : " + response.contentType());
        System.out.println("Headers: \n" + response.getHeaders());

    }

}
