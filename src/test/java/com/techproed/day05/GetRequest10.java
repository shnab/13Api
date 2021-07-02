package com.techproed.day05;

import com.techproed.testbase.TestBaseDummyRestApi;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static io.restassured.RestAssured.given;

public class GetRequest10 extends TestBaseDummyRestApi {
    /*
    http://dummy.restapiexample.com/api/v1/employees
    url ine bir istek gönderildiğinde
    Dönen response un
     Status kodunun 200,
     1)10’dan büyük tüm id’leri ekrana yazdırın ve
    10’dan büyük 14 id olduğunu,
     2)30’dan küçük tüm yaşları ekrana yazdırın ve
      bu yaşların içerisinde en büyük yaşın 23 olduğunu
     3)Maası 350000 den büyük olan tüm employee name’leri ekrana yazdırın ve
      bunların içerisinde “Charde Marshall” olduğunu test edin
         */

    @Test
    public void test01() {
        spec3.pathParam("parametre", "employees");

        Response response = given().
                accept("application/json").
                spec(spec3).
                when().get("/{parametre}");
        response.then().assertThat();
        Assert.assertEquals(200, response.getStatusCode());
        response.prettyPrint();

//        1)10’dan büyük tüm id’leri ekrana yazdırın ve 10’dan büyük 14 id olduğunu,
        JsonPath json = response.jsonPath();


        //1. yol
        // burada liste olusturduk ve tu idleri atadik
        //GROOVY DILI : javanin alt dillerinden biridir.
        //it==> this gibi
        List<Integer> idList = json.getList("data.findAll{it.id>10}.id");
//        for(Integer each: idList) {
//            if(each>=10){
//                System.out.println(idList);
//            }
//        }
        //findAll => Datanin icerisinde istenen sarta bagli olan degerleri getirir.
        System.out.println(idList);
        //10’dan büyük 14 id olduğunu test edin
        Assert.assertEquals(14, idList.size());

        //2.yol => Lambda ile
        List<Integer> list = json.getList("data.id");
        list.stream().filter(x -> x > 10).forEach(x -> System.out.print(x + " "));

//        2)30’dan küçük tüm yaşları ekrana yazdırın ve  bu yaşların içerisinde en büyük yaşın 23 olduğunu
        List<Integer> yasListe = json.getList("data.findAll{it.employee_age<30}.employee_age");
        System.out.println(yasListe);

        Collections.sort(yasListe);
        Assert.assertEquals(Integer.valueOf(23), yasListe.get(yasListe.size()-1));


//        3)Maası 350000 den büyük olan tüm employee name’leri ekrana yazdırın ve
//        bunların içerisinde “Charde Marshall” olduğunu test edin
                //==> burada sadce ==>.employee_name olarak aratmak yeterli
        List<Integer> maasListesindenName = json.getList("data.findAll{it.employee_salary>350000}.employee_name");
        System.out.println(maasListesindenName); //[Cedric Kelly, Brielle Williamson, Charde Marshall, Tatyana Fitzpatrick, Paul Byrd, Yuri Berry]
        Assert.assertTrue(maasListesindenName.contains("Charde Marshall"));


    }
}