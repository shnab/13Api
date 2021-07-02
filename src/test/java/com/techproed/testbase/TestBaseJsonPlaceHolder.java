package com.techproed.testbase;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class TestBaseJsonPlaceHolder {

    //acces modifier++> Interface ==> spec
    protected RequestSpecification spec01;

@Before // herseyden once calissin
    public void setup(){
        //spec icine base URL
        spec01 =new RequestSpecBuilder().
                setBaseUri("https://jsonplaceholder.typicode.com").
                build();
    }


}
