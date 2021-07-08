package com.techproed.testdata;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestDataDummy {

        /*
            http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
        Status kodun 200 olduğunu,
        5. Çalışan isminin "Airi Satou" olduğunu ,
        çalışan sayısının 24 olduğunu,
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

    public HashMap<String , Object>  setUpTestdata(){

        //bu map asagida List, map ve  dgerler atayacagiz
        HashMap<String , Object> expectedDataMap =new HashMap<>();

        //yaslari liste halinde olusturduk
        List<Integer> yaslar = new ArrayList<>();
        yaslar.add(40);
        yaslar.add(21);
        yaslar.add(19);

        //11. calisani MAP olarak olusturacagiz
        HashMap<String, Object> onBirinciCalisan = new HashMap<>();
        onBirinciCalisan.put("id", 11);
        onBirinciCalisan.put("employee_name","Jena Gaines");
        onBirinciCalisan.put("employee_salary",90560);
        onBirinciCalisan.put("employee_age",30);
        onBirinciCalisan.put("profile_image","");

        //burada expectedDataMap icine List, Map ve diger key=value degerleri ekledik
        expectedDataMap.put("yasListesi", yaslar);
        expectedDataMap.put("employee11", onBirinciCalisan);
        expectedDataMap.put("statusCode", 200);
        expectedDataMap.put("besinciCalisan", "Airi Satou");
        expectedDataMap.put("calisanSayisi", 24);
        expectedDataMap.put("sondanIkinciMaas", 106450);

        return expectedDataMap;

    }

        /*
    http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
    Status kodun 200 olduğunu,
    En yüksek maaşın 725000 olduğunu,
    En küçük yaşın 19 olduğunu,
    İkinci en yüksek maaşın 675000
    olduğunu test edin.
     */

    public HashMap<String, Integer> setUpTestData2() {

        HashMap<String , Integer> expectedDataMap =new HashMap<>();

        expectedDataMap.put("statusCode", 200);
        expectedDataMap.put("enYuksekMaas", 725000);
        expectedDataMap.put("enKucukYas", 19);
        expectedDataMap.put("ikinciEnYuksekMaas", 675000);

        return expectedDataMap;

    }

    //Gonderecegim request datayi olusturduk
    public HashMap<String, Object> setUpPostData3(){
        HashMap<String, Object> requestBodyMap= new HashMap<>();

        requestBodyMap.put("name","Ahmet Aksoy");
        requestBodyMap.put("salary",1000);
        requestBodyMap.put("age",18);
        requestBodyMap.put("profile_image", "");

        return requestBodyMap;
        }

    public HashMap<String, Object> setUpTestData4(){
        HashMap<String, Object> expectedDataMap= new HashMap<>();
        expectedDataMap.put("statusCode", 200);
        expectedDataMap.put("status" , "success");
        expectedDataMap.put("message", "Successfully! Record has been added.");

        return expectedDataMap;
    }

    public JSONObject setUpDelete(){
        /*
            {
        "status": "success",
            "data": "2",
            "message": "Successfully! Record has been deleted"
    }
         */
        JSONObject expectedDataMap= new  JSONObject();
        expectedDataMap.put("status" , "success");
        expectedDataMap.put("data", "2");
        expectedDataMap.put("message", "Successfully! Record has been deleted");
        //statusCode ==> bu kismi Body icersindfe gondermeyecegimiz icin burada map icine koyduk,
        // yoksa koyamayiz.Konu ile ilgisi yok
        expectedDataMap.put("statusCode", 200);


        return expectedDataMap;
    }




    }








