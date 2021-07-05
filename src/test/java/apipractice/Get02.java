package apipractice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class Get02 {
    /*
             Positive Scenario:
             When Asagidaki Endpoint'e bir GET request yolladim
             https://www.gmibank.com/api/tp-customers/42697
         And Accept type “application/json” dir
         Then
         HTTP Status Code 200
         And Response format "application/json"
         And firstname "Ali"
         And lastname "Deckow"
         And middleInitial Leroy Hoeger Sipes
         And email com.github.javafaker.Name@7c011174@gmail.com
         And zelleEnrolled false
         And country null
            */
    @Test
    public void getRequest(){

    //1. url olustur
    String endPoint = "https://www.gmibank.com/api/tp-customers/42697";

    String bearerToken ="";

    //request gonder
    Response response = RestAssured.given().auth().oauth2(bearerToken).
            accept("application/json").
            when().
            get(endPoint );
        response.prettyPrint();

        response.then().assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname", equalTo("Ali"),
                        "lastname", equalTo("Deckow"),
                        "middleInitial", equalTo("Leroy Hoeger Sipes"),
                        "email", equalTo("com.github.javafaker.Name@7c011174@gmail.com"),
                        "zelleEnrolled",equalTo(false),
                        "country", equalTo(null));

        JsonPath jsonPath= response.jsonPath();
        Assert.assertEquals("Isimler uysmuyor", "Ali", jsonPath.getString("firstname") );

}}
