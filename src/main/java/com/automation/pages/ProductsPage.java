package com.automation.pages;

import com.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

public class ProductsPage extends BasePage {

    private WebDriver driver;

    // Locators
    private By productsLink = By.xpath("//a[@href='/products']");
    private By allProductsTitle = By.xpath("//h2[text()='All Products']");
    private By searchInput = By.id("search_product");
    private By searchButton = By.id("submit_search");
    private By searchedProductsTitle = By.xpath("//h2[text()='Searched Products']");

    private By firstAddToCart = By.xpath("(//a[contains(text(),'Add to cart')])[1]");
    private By secondAddToCart = By.xpath("(//a[contains(text(),'Add to cart')])[2]");
    private By continueShopping = By.xpath("//button[contains(text(),'Continue Shopping')]");
    private By viewCart = By.xpath("//u[contains(text(),'View Cart')]");
    private By cartItems = By.xpath("//tr[contains(@id,'product-')]");

    public ProductsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickProducts() {

        removeAds();

        try {
            // Click using JS to avoid overlay issues
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", driver.findElement(productsLink));

            // Small wait
            Thread.sleep(2000);

            // If vignette appears, force redirect
            if (driver.getCurrentUrl().contains("google_vignette")) {
                driver.navigate().to("https://automationexercise.com/products");
            }

        } catch (Exception e) {
            driver.navigate().to("https://automationexercise.com/products");
        }

        // 🔥 WAIT FOR PAGE ELEMENT NOT URL
        wait.until(ExpectedConditions.visibilityOfElementLocated(allProductsTitle));
    }

    public boolean isAllProductsVisible() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(allProductsTitle)
        ).isDisplayed();
    }

    //  ADD THIS METHOD
    public void searchProduct(String productName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput))
                .sendKeys(productName);

        driver.findElement(searchButton).click();
    }

    //  ADD THIS METHOD
    public boolean isSearchedProductsVisible() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(searchedProductsTitle)
        ).isDisplayed();
    }

    public void addTwoProductsToCart() {

        removeAds();

        List<WebElement> products = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.cssSelector(".product-image-wrapper")));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions actions = new Actions(driver);

        // ===== FIRST PRODUCT =====
        WebElement firstProduct = products.get(0);

        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", firstProduct);
        actions.moveToElement(firstProduct).pause(Duration.ofSeconds(1)).perform();

        WebElement firstAddToCart = firstProduct.findElement(
                By.cssSelector("a.btn.add-to-cart"));

        wait.until(ExpectedConditions.elementToBeClickable(firstAddToCart));
        js.executeScript("arguments[0].click();", firstAddToCart);

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//button[contains(text(),'Continue Shopping')]")))
                .click();

        // ===== SECOND PRODUCT =====
        WebElement secondProduct = products.get(1);

        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", secondProduct);
        actions.moveToElement(secondProduct).pause(Duration.ofSeconds(1)).perform();

        WebElement secondAddToCart = secondProduct.findElement(
                By.cssSelector("a.btn.add-to-cart"));

        wait.until(ExpectedConditions.elementToBeClickable(secondAddToCart));
        js.executeScript("arguments[0].click();", secondAddToCart);

        // Wait a moment for cart update
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector(".modal-content")));

// Click top Cart menu instead of popup
        WebElement cartMenu = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[contains(text(),'Cart')]")));

        cartMenu.click();
    }

    public boolean verifyTwoProductsInCart() {
        return driver.findElements(cartItems).size() >= 2;
    }
}