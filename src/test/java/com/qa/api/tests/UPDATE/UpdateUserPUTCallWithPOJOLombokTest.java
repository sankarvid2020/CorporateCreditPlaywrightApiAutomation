package com.qa.api.tests.UPDATE;

import com.api.data.User;
import com.api.data.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class UpdateUserPUTCallWithPOJOLombokTest {

    //1. post - user id = 123
    //2. put - user id - /123
    //3. get -- user id /123
    Playwright playwright;
    APIRequest request;
    APIRequestContext requestContext;

    static String emailId;

    @BeforeTest
    public void setup(){
        playwright = Playwright.create();
        request =  playwright.request();
        requestContext = request.newContext();
    }

    @AfterTest
    public void tearDown(){
        playwright.close();
    }


    public static String getRandomEmail(){
        emailId = "apitest"+ System.currentTimeMillis() + "@gmail.com";
        return emailId;
    }


    @Test
    public void updateUserTest() throws IOException {

        //create users object: using builder pattern:
        Users users = Users.builder()
                .name("Sankar Automation")
                .email(getRandomEmail())
                .gender("male")
                .status("active").build();

        //1. POST Call: create a user
        APIResponse apiPostResponse = requestContext.post("https://gorest.co.in/public/v2/users",
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json")
                        .setHeader("Authorization", "Bearer fa6317ced12c6ce4da6f48dd2464710539617d8fd089a1c761d40d6ea58dbc7c")
                        .setData(users));

        System.out.println(apiPostResponse.url());
        System.out.println(apiPostResponse.status());
        Assert.assertEquals(apiPostResponse.status(), 201);
        Assert.assertEquals(apiPostResponse.statusText(), "Created");

        String responseText = apiPostResponse.text();
        System.out.println(responseText);

        //convert response text/json to POJO -- deserialization
        ObjectMapper objectMapper = new ObjectMapper();
        User actUser = objectMapper.readValue(responseText, User.class);
        System.out.println("actual user from the response---->");
        System.out.println(actUser);

        Assert.assertEquals(actUser.getName(), users.getName());
        Assert.assertEquals(actUser.getEmail(), users.getEmail());
        Assert.assertEquals(actUser.getStatus(), users.getStatus());
        Assert.assertEquals(actUser.getGender(), users.getGender());
        Assert.assertNotNull(actUser.getId());

        String userId = actUser.getId();
        System.out.println("new user id is : " + userId);

        //update status active to inactive
        users.setStatus("inactive");
        users.setName("Sankar Automation Playwright");

        System.out.println("---------------PUT CALL----------------");

        //2. PUT Call - update user:
        APIResponse apiPUTResponse = requestContext.put("https://gorest.co.in/public/v2/users/" + userId,
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json")
                        .setHeader("Authorization", "Bearer fa6317ced12c6ce4da6f48dd2464710539617d8fd089a1c761d40d6ea58dbc7c")
                        .setData(users));

        System.out.println(apiPUTResponse.status() + " : " + apiPUTResponse.statusText());
        Assert.assertEquals(apiPUTResponse.status(), 200);

        String putResponseText = apiPUTResponse.text();
        System.out.println("update user : " + putResponseText);

        Users actPutUser = objectMapper.readValue(putResponseText, Users.class);
        Assert.assertEquals(actPutUser.getId(), userId);
        Assert.assertEquals(actPutUser.getStatus(), users.getStatus());
        Assert.assertEquals(actPutUser.getName(), users.getName());

        System.out.println("---------------GET CALL----------------");

        //3. Get the updates user with GET CALL:
        APIResponse apiGETResponse = requestContext.get("https://gorest.co.in/public/v2/users/"+userId,
                RequestOptions.create()
                .setHeader("Authorization", "Bearer fa6317ced12c6ce4da6f48dd2464710539617d8fd089a1c761d40d6ea58dbc7c"));

        System.out.println(apiGETResponse.url());

        int statusCode = apiGETResponse.status();
        System.out.println("response status code: " + statusCode);
        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals(apiGETResponse.ok(), true);

        String statusGETStatusText = apiGETResponse.statusText();
        System.out.println(statusGETStatusText);

        String getResponseText = apiGETResponse.text();

        Users actGETUser = objectMapper.readValue(getResponseText, Users.class);
        Assert.assertEquals(actGETUser.getId(), userId);
        Assert.assertEquals(actGETUser.getStatus(), users.getStatus());
        Assert.assertEquals(actGETUser.getName(), users.getName());

    }




}
