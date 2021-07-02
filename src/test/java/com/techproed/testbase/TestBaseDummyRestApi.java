package com.techproed.testbase;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class TestBaseDummyRestApi {

    protected RequestSpecification spec3;

    @Before
    public void setup(){

        spec3 =new RequestSpecBuilder(). //burada com/api/v1 yazdik, cunku sayfaya gittik baktik, hep bu kisma kadar tekrarli
                setBaseUri("http://dummy.restapiexample.com/api/v1").
                build();
    }
}
