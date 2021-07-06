package com.techproed.day08;

import com.techproed.testbase.TestBaseDummyRestApi;
import com.techproed.testdata.TestDataDummy;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class GetRequest13De_Serilization extends TestBaseDummyRestApi {
    /*
            http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
        Status kodun 200 olduğunu,
        5. Çalışan isminin "Airi Satou" olduğunu ,  çalışan sayısının 24 olduğunu,
        Sondan 2. çalışanın maaşının 106450 olduğunu
        40,21 ve 19 yaslarında çalışanlar olup olmadığını
        11. Çalışan bilgilerinin
          {
         “id”:”11”
         "employee_name": "Jena Gaines",
        "employee_salary": 90560,
        "employee_age": 30,
        "profile_image": "" }
        } gibi olduğunu test edin
             */

    @Test
    public void test01(){
        spec3.pathParam("parametre", "employees");

        //Expected data from TestDataDummy
        TestDataDummy testDataDummy=new TestDataDummy();
        HashMap<String,Object> expectedDataMap=testDataDummy.setUpTestdata();
        System.out.println(expectedDataMap);

        Response response = given().
                accept("application/json").
                spec(spec3).
                when().get("/{parametre}");
//        response.prettyPrint();

        //De-Serialization islemi ==> ActualDATA
        HashMap<String , Object> actualDataMap =response.as(HashMap.class);
        System.out.println(actualDataMap);

        //Assert
        Assert.assertEquals(expectedDataMap.get("statusCode"), response.getStatusCode());

        //5. Çalışan isminin "Airi Satou" olduğunu
        // burada donen MAP i casting ile List yaptik sonrada MAP e cevirdik
        Assert.assertEquals(expectedDataMap.get("besinciCalisan"),
                ((Map) ((List)actualDataMap.get("data")).get(4)).get("employee_name"));
        System.out.println(((Map) ((List)actualDataMap.get("data")).get(4)).get("employee_name"));//Airi Satou

        //çalışan sayısının 24 olduğunu,
        Assert.assertEquals(expectedDataMap.get("calisanSayisi"),
                ((List)actualDataMap.get("data")).size());
        System.out.println(((List)actualDataMap.get("data")).size()); //24

        //Sondan 2. çalışanın maaşının 106450 olduğunu

        int dataSize = ((List)actualDataMap.get("data")).size();

        Assert.assertEquals(expectedDataMap.get("sondanIkinciMaas"),
                ((Map)((List)actualDataMap.get("data")).get(actualDataMap.size()-2)).get("employee_salary"));

        System.out.println(((Map)((List)actualDataMap.get("data")).get(actualDataMap.size()-2)).get("employee_salary"));//106450

        //40,21 ve 19 yaslarında çalışanlar olup olmadığını
        List<Integer> actualYasListesi = new ArrayList<>();

        for(int i=0; i<dataSize; i++){
            actualYasListesi.add((Integer) ((Map)((List) actualDataMap.get("data")).get(i)).get("employee_age"));
        }

        System.out.println(actualYasListesi);

        Assert.assertTrue(actualYasListesi.containsAll((List)expectedDataMap.get("yasListesi")));

        //11. Çalışan bilgilerinin doggrulugunu
        Assert.assertEquals(
                ((Map)expectedDataMap.get("employee11")).get("id"),
                ((Map)((List<?>) actualDataMap.get("data")).get(10)).get("id"));

        Assert.assertEquals(
                ((Map)expectedDataMap.get("employee11")).get("employee_name"),
                ((Map)((List<?>) actualDataMap.get("data")).get(10)).get("employee_name"));

        Assert.assertEquals(
                ((Map)expectedDataMap.get("employee11")).get("employee_salary"),
                ((Map)((List<?>) actualDataMap.get("data")).get(10)).get("employee_salary"));

        Assert.assertEquals(
                ((Map)expectedDataMap.get("employee11")).get("employee_age"),
                ((Map)((List<?>) actualDataMap.get("data")).get(10)).get("employee_age"));

        Assert.assertEquals(
                ((Map)expectedDataMap.get("employee11")).get("profile_image"),
                ((Map)((List<?>) actualDataMap.get("data")).get(10)).get("profile_image"));



    }

}
