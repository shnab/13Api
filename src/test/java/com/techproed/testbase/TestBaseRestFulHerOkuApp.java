package com.techproed.testbase;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class TestBaseRestFulHerOkuApp {

    protected RequestSpecification spec2;

    @Before
    public void setup(){
        spec2= new RequestSpecBuilder().
                setBaseUri("https://restful-booker.herokuapp.com").
                build();
    }

}
