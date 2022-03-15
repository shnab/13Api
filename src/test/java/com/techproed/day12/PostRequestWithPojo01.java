package com.techproed.day12;

import com.techproed.pojos.JsonPlaceHolderTodosPojo;
import com.techproed.testbase.TestBaseJsonPlaceHolder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostRequestWithPojo01 extends TestBaseJsonPlaceHolder {

    /*POJO icin
    1. private
    2.getter-setter
    3.parametresiz constructor
    4.parametreli constructoe
    5.toString

     */

    /*
    https://jsonplaceholder.typicode.com/todos
    Request body  {
                      "userId": 21,
                      "id": 201,
                      "title": "Tidy your room",
                      "completed": false
                    }
   Status code is 201
    response body {
                      "userId": 21,
                      "id": 201,
                      "title": "Tidy your room",
                      "completed": false
                    }
     */

    @Test
    public void test(){
        //url-endpoint
        spec01.pathParam("parametre", "todos");

        //request body olustrur

        JsonPlaceHolderTodosPojo todos = new JsonPlaceHolderTodosPojo(21,201,"Tidy your room", false );
        System.out.println(todos);

        //Request gonder
        Response response =given()
                        .contentType(ContentType.JSON)
                        .spec(spec01)
                        .auth().basic("admin", "password123")
                        .body(todos).when().post("/{parametre}");
        response.prettyPrint();

        //DE-Serilization (GSON) ---POJO
        //yani kaliba dok diyoruz burada
        JsonPlaceHolderTodosPojo actualData= response.as(JsonPlaceHolderTodosPojo.class);

        //Assertion
        Assert.assertEquals(201, response.getStatusCode());
        Assert.assertEquals(todos.getId(), actualData.getId());
        Assert.assertEquals(todos.getUserId(), actualData.getUserId());
        Assert.assertEquals(todos.getTitle(), actualData.getTitle());
        Assert.assertEquals(todos.isCompleted(), actualData.isCompleted());


        //jsonpath ile
        JsonPath jsonPath =response.jsonPath();
        Assert.assertEquals(todos.getId(), jsonPath.getInt("id") );
        Assert.assertEquals(todos.getUserId(), jsonPath.getInt("userId"));
        Assert.assertEquals(todos.getTitle(), jsonPath.getString("title"));
        Assert.assertEquals(todos.isCompleted(), jsonPath.getBoolean("completed"));

        //matchers ile
        response
                .then()
                .assertThat()
                .body("userId", equalTo(todos.getUserId()),
                        "id", equalTo(todos.getId()),
                        "title", equalTo(todos.getTitle()),
                        "completed", equalTo(todos.isCompleted()));

    }


}
