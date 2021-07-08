package com.techproed.day11;

import com.techproed.testbase.TestBaseDummyRestApi;
import com.techproed.testbase.TestBaseJsonPlaceHolder;
import com.techproed.testdata.TestDataDummy;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DeleteRequest01 extends TestBaseDummyRestApi {

    /*
    http://dummy.restapiexample.com/api/v1/delete/2 bir DELETE request gönderdiğimde

    Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
    {
     "status": "success",
     "data": "2",
     "message": "Successfully! Record has been deleted"
    }
     */

    @Test
    public void test() {
        spec3.pathParams("parametre1", "delete",
                "parametre2", 2);

        //expected data olustur. Bna donmesini bekledigim data bu kisim
        TestDataDummy testData = new TestDataDummy();
        JSONObject expectedData = testData.setUpDelete();
        System.out.println(expectedData); //{"data":"2","message":"Successfully! Record has been deleted","status":"success","statusCode":200}


        //response olustur gonder
        Response response = given()
                //.contentType(ContentType.JSON)
                .spec(spec3)
                .auth()
                .basic("admin", "password123")
                //.body(requestBody.toString())==> silerken bu body kismina gerek yok
                .when()
                .delete("/{parametre1}/{parametre2}");
        response.prettyPrint();


        //actual Data

       response.then()
               .assertThat()
               .statusCode(expectedData.getInt("statusCode"))
               .body("status", equalTo(expectedData.getString("status")),
                       "data", equalTo(expectedData.getString("data")),
                       "message", equalTo(expectedData.getString("message")));







    }
}
