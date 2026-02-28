package com.automation.pages;

import com.automation.base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

public class ProductsPage extends BasePage {

    private WebDriver driver;

    // Locators
    private By allProductsTitle = By.xpath("//h2[contains(text(),'All Products')]");
    private By searchInput = By.id("search_product");
    private By searchButton = By.id("submit_search");
    private By searchedProductsTitle = By.xpath("//h2[text()='Searched Products']");
    private By cartItems = By.xpath("//tr[contains(@id,'product-')]");

    public ProductsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ================= VERIFY ALL PRODUCTS =================
    public boolean isAllProductsVisible() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h2[text()='All Products']")
                )
        ).isDisplayed();
    }

    // ================= SEARCH PRODUCT =================
    public void searchProduct(String productName) {

        WebElement searchBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(searchInput));

        searchBox.clear();
        searchBox.sendKeys(productName);

        driver.findElement(searchButton).click();
    }

    // ================= VERIFY SEARCH RESULT =================
    public boolean isSearchedProductsVisible() {
        return wait.until(
                        ExpectedConditions.visibilityOfElementLocated(searchedProductsTitle))
                .isDisplayed();
    }

    // ================= ADD TWO PRODUCTS =================
    public void addTwoProductsToCart() {

        removeAds();

        List<WebElement> products = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.cssSelector(".product-image-wrapper")));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions actions = new Actions(driver);

        // FIRST PRODUCT
        WebElement firstProduct = products.get(0);

        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", firstProduct);
        actions.moveToElement(firstProduct).pause(Duration.ofSeconds(1)).perform();

        WebElement firstAddToCart = firstProduct.findElement(
                By.cssSelector("a.btn.add-to-cart"));

        js.executeScript("arguments[0].click();", firstAddToCart);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cartModal")));

        WebElement continueShopping = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(text(),'Continue Shopping')]")));

        continueShopping.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("cartModal")));

        // SECOND PRODUCT
        WebElement secondProduct = products.get(1);

        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", secondProduct);
        actions.moveToElement(secondProduct).pause(Duration.ofSeconds(1)).perform();

        WebElement secondAddToCart = secondProduct.findElement(
                By.cssSelector("a.btn.add-to-cart"));

        js.executeScript("arguments[0].click();", secondAddToCart);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cartModal")));

        WebElement closeModal = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(text(),'Continue Shopping')]")));

        closeModal.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("cartModal")));

        // CLICK TOP CART
        WebElement cartMenu = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[@href='/view_cart']")));

        cartMenu.click();
    }

    // ================= VERIFY CART =================
    public boolean verifyTwoProductsInCart() {

        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(cartItems, 1));

        return driver.findElements(cartItems).size() >= 2;
    }
}