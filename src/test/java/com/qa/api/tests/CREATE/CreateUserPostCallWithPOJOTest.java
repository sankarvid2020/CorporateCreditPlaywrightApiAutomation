package com.qa.api.tests.CREATE;

import com.api.data.User;
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

public class CreateUserPostCallWithPOJOTest {

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
        emailId = "apiautomation"+ System.currentTimeMillis() + "@gmail.com";
        return emailId;
    }


    @Test
    public void createUserTest() throws IOException {

        //create user object:
        User user = new User("Sankar", getRandomEmail(), "male", "active");

        //POST Call: create a user
        APIResponse apiPostResponse = requestContext.post("https://gorest.co.in/public/v2/users",
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json")
                        .setHeader("Authorization", "Bearer fa6317ced12c6ce4da6f48dd2464710539617d8fd089a1c761d40d6ea58dbc7c")
                        .setData(user));

        System.out.println(apiPostResponse.status());
        Assert.assertEquals(apiPostResponse.status(), 201);
        Assert.assertEquals(apiPostResponse.statusText(), "Created");

        String responseText = apiPostResponse.text();
        System.out.println(responseText);

       //convert response text/json to POJO -- desrialization
       ObjectMapper objectMapper = new ObjectMapper();
        User actUser = objectMapper.readValue(responseText, User.class);
        System.out.println("actual user from the response---->");
        System.out.println(actUser);



        Assert.assertEquals(actUser.getName(), user.getName());
        Assert.assertEquals(actUser.getEmail(), user.getEmail());
        Assert.assertEquals(actUser.getStatus(), user.getStatus());
        Assert.assertEquals(actUser.getGender(), user.getGender());
        Assert.assertNotNull(actUser.getId());

    }


}
