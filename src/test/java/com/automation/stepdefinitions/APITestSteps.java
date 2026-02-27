package com.automation.stepdefinitions;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class APITestSteps {

    Response response;

    @Given("User calls products API")
    public void call_api() {
        response = given()
                .when()
                .get("https://automationexercise.com/api/productsList");
    }

    @Then("Response status should be 200")
    public void verify_status() {
        assertEquals(response.statusCode(), 200);
    }
}