package com.automation.stepdefinitions;

import com.automation.base.DriverFactory;
import com.automation.pages.HomePage;
import com.automation.pages.ProductsPage;
import com.automation.pages.CartPage;
import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import io.qameta.allure.Allure;
import io.cucumber.java.Scenario;

import java.io.ByteArrayInputStream;

public class UITestSteps {

    private WebDriver driver;
    private HomePage homePage;
    private ProductsPage productsPage;
    private CartPage cartPage;

    @Given("User launches browser")
    public void user_launches_browser() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();

        homePage = new HomePage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
    }

    @And("User navigates to {string}")
    public void user_navigates_to(String url) {

        driver.get(url);

        if (driver.getCurrentUrl().contains("google_vignette")) {
            driver.get("https://automationexercise.com/");
        }

        if (driver.getCurrentUrl().contains("#google_vignette")) {
            driver.navigate().to("https://automationexercise.com/");
        }
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

    @After
    public void tearDown(Scenario scenario) {

        if (scenario.isFailed()) {
            Allure.addAttachment(
                    "Failure Screenshot",
                    "image/png",
                    new ByteArrayInputStream(DriverFactory.takeScreenshot()),
                    ".png"
            );
        }

        DriverFactory.quitDriver();
    }
}