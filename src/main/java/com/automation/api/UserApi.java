package com.automation.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UserApi {

    public Response getAllProducts() {

        return RestAssured
                .given()
                .baseUri("https://automationexercise.com/api")
                .when()
                .get("/productsList")
                .then()
                .extract()
                .response();
    }
}