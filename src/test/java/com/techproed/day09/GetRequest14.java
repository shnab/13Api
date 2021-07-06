package com.techproed.day09;

import com.techproed.testbase.TestBaseDummyRestApi;
import com.techproed.testdata.TestDataDummy;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.net.Inet4Address;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class GetRequest14 extends TestBaseDummyRestApi {
    /*
    http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
    Status kodun 200 olduğunu,
    En yüksek maaşın 725000 olduğunu,
    En küçük yaşın 19 olduğunu,
    İkinci en yüksek maaşın 675000
    olduğunu test edin.
     */
    @Test
    public void test01() {
        spec3.pathParam("parametre", "employees");

        //Expected data from TestDataDummy
        TestDataDummy testDataDummy = new TestDataDummy();
        HashMap<String, Integer> expectedDataMap = testDataDummy.setUpTestData2();
        System.out.println(expectedDataMap);

        Response response = given().
                accept("application/json").
                spec(spec3).
                when().get("/{parametre}");
        response.prettyPrint();

        //Actual data
        HashMap<String, Object> actualDataMap = response.as(HashMap.class);
        System.out.println(actualDataMap);

        //Status kodun 200 olduğunu,
        Assert.assertEquals(expectedDataMap.get("statusCode"), (Integer) response.getStatusCode());


                          //====================En yüksek maaşın 725000 olduğunu,=======================

                                //=====1. De-Serilization ile============

                    //Once maaslardan olusan bir liste yapmaliyiz.
                      List<Integer> actualMaasList = new ArrayList<Integer>();

                     //actualDataMapten donen datanin size bulmaliyiz.
                    int dataSize= ((List<?>) actualDataMap.get("data")).size();

                  for (int i = 0; i < dataSize; i++) {
                        int salary = (int)((Map)((List)actualDataMap.get("data")).get(i)).get("employee_salary");
                        actualMaasList.add(salary);
                    }
                    Collections.sort(actualMaasList);
                    Assert.assertEquals(expectedDataMap.get("enYuksekMaas"),actualMaasList.get(actualMaasList.size()-1));

                                //2. =============Lambda ile============
                    Integer enYuksekMaas = actualMaasList.stream().reduce(0, (x, y) -> x > y ? x : y);

        //İkinci en yüksek maaşın 675000
        Assert.assertEquals(expectedDataMap.get("ikinciEnYuksekMaas"), actualMaasList.get(actualMaasList.size()-2));

        //==================== En küçük yaşın 19 olduğunu, =================//
                        List<Integer> actualYasList = new ArrayList<>();

                        for(int i=0; i<dataSize; i++){
                           actualYasList.add((Integer) ((Map)((List)actualDataMap.get("data")).get(i)).get("employee_age"));
                        }
                        Collections.sort(actualYasList);
                        Assert.assertEquals(expectedDataMap.get("enKucukYas"), actualYasList.get(0));

        // ================En küçük yaşın 19 olduğunu ==> Lambda ile
                    List<Integer> ageList = (List<Integer>) ((List) actualDataMap.get("data")).
                            stream().
                            map(x -> ((Map) x).
                                    get("employee_age")).
                            collect(Collectors.toList());
                    Collections.sort(ageList);
                    Assert.assertEquals(expectedDataMap.get("enKucukYas"),ageList.get(0));

                             //===========JSONPATH ile=======================//
                        JsonPath jsonPath = response.jsonPath();
                        Assert.assertEquals(expectedDataMap.get("statusCode"), (Integer) response.getStatusCode());

                        //En küçük yaşın 19 olduğunu
                        List<Integer> jsonYasListesi = jsonPath.getList("data.employee_age");
                        Collections.sort(jsonYasListesi);
                        Assert.assertEquals(expectedDataMap.get("enKucukYas"), jsonYasListesi.get(0) );

                        //En yüksek maaşın 725000 olduğunu,
                        List<Integer> jsonMaasList = jsonPath.getList("data.employee_salary");
                        Collections.sort(jsonMaasList);

                        Assert.assertEquals(expectedDataMap.get("ikinciEnYuksekMaas"), jsonMaasList.get(jsonMaasList.size()-1));
                        Assert.assertEquals(expectedDataMap.get("ikinciEnYuksekMaas"), jsonMaasList.get(jsonMaasList.size()-2));


    }
}
