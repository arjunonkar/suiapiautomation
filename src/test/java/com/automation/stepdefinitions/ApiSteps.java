package com.automation.stepdefinitions;

import com.automation.api.UserApi;
import io.cucumber.java.en.Then;

public class ApiSteps {

    UserApi userApi = new UserApi();

    @Then("I validate products API")
    public void validate_products_api() {
        userApi.getAllProducts().prettyPrint();
    }
}