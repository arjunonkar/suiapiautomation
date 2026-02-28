package com.automation.stepdefinitions;

import com.automation.base.DriverFactory;
import com.automation.pages.HomePage;
import com.automation.pages.ProductsPage;
import com.automation.pages.CartPage;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class UITestSteps {

    private HomePage homePage;
    private ProductsPage productsPage;
    private CartPage cartPage;

    @Given("User navigates to {string}")
    public void user_navigates_to(String url) {

        DriverFactory.getDriver().get(url);

        homePage = new HomePage(DriverFactory.getDriver());
        productsPage = new ProductsPage(DriverFactory.getDriver());
        cartPage = new CartPage(DriverFactory.getDriver());
    }

    @Then("Home page should be visible")
    public void home_page_should_be_visible() {
        Assert.assertTrue(homePage.isHomeVisible());
    }

    @When("User clicks Products")
    public void user_clicks_products() {
        homePage.clickProducts();
    }

    @Then("All Products page should be visible")
    public void all_products_page_should_be_visible() {
        Assert.assertTrue(productsPage.isAllProductsVisible());
    }

    @When("User searches for {string}")
    public void user_searches_for(String product) {
        productsPage.searchProduct(product);
    }

    @Then("Searched Products should be visible")
    public void searched_products_should_be_visible() {
        Assert.assertTrue(productsPage.isSearchedProductsVisible());
    }

    @When("User adds two products to cart")
    public void user_adds_two_products_to_cart() {
        productsPage.addTwoProductsToCart();
    }

    @Then("Both products should be in cart")
    public void both_products_should_be_in_cart() {
        Assert.assertTrue(productsPage.verifyTwoProductsInCart());
    }
}