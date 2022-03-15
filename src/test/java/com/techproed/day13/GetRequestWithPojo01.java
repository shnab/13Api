package com.techproed.day13;

import com.google.gson.Gson;
import com.techproed.pojos.DataPojo;
import com.techproed.pojos.EmployeePojo;
import com.techproed.testbase.TestBaseDummyRestApi;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequestWithPojo01 extends TestBaseDummyRestApi {

    /*
    GET Request to the URL http://dummy.restapiexample.com/api/v1/employee/1
            Status code is 200
             {
                                  "status": "success",
                                  "data": {
                                      "id": 1,
                                      "employee_name": "Tiger Nixon",
                                      "employee_salary": 320800,
                                      "employee_age": 61,
                                      "profile_image": ""
                                  },
                                  "message": "Successfully! Record has been fetched."
                                 }
}
     */
    @Test
    public void test(){
        spec3.pathParams("parametre1", "employee", "parametre2", 1);

        //expected data olustur
        //POJO ile expected data data olustur
        DataPojo data = new DataPojo(1,"Tiger Nixon",320800, 61, "" );
        EmployeePojo expectedData = new EmployeePojo("success", data, "Successfully! Record has been fetched.");
        System.out.println(expectedData);

        //Bu get oldugu icin ben olusturdugum POJo kalibi ile hem expected hem de actual olusturabilirim
        //Request gonder
        Response response=given().
                contentType(ContentType.JSON)
                .spec(spec3)
                .when()
                .get("/{parametre1}/{parametre2}");
        response.prettyPrint();

        //Actual Data
        EmployeePojo actualData = response.as(EmployeePojo.class);
        System.out.println(actualData);//EmployeePojo{status='success', data=DataPojo{id=1, employee_name='Tiger Nixon', employee_salary=320800, employee_age=61, profile_image=''}, message='Successfully! Record has been fetched.'}


        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(expectedData.getStatus(),actualData.getStatus());
        Assert.assertEquals(expectedData.getMessage(), actualData.getMessage());
        Assert.assertEquals(expectedData.getData().getId(), actualData.getData().getId());
        Assert.assertEquals(expectedData.getData().getEmployee_age(),actualData.getData().getEmployee_age());
        Assert.assertEquals(expectedData.getData().getEmployee_name(),actualData.getData().getEmployee_name());
        Assert.assertEquals(expectedData.getData().getEmployee_salary(), actualData.getData().getEmployee_salary());
        Assert.assertEquals(expectedData.getData().getProfile_image(), actualData.getData().getProfile_image());

        //Seriliozaztion islemi ile Java  objesi Json objesine cevirilebilir
        //Gson classindan bir obje uret
        Gson gson = new Gson();
        String  jsonFromJava = gson.toJson(actualData);
        //urettigim obje uzerinden toJson metodunu kullandim
        System.out.println(jsonFromJava); //{"status":"success","data":{"id":1,"employee_name":"Tiger Nixon","employee_salary":320800,"employee_age":61,"profile_image":""},"message":"Successfully! Record has been fetched."}


    }

}
