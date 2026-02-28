package com.automation.pages;

import com.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    private WebDriver driver;

    @FindBy(xpath = "//a[contains(text(),'Home')]")
    private WebElement homeLink;

    @FindBy(xpath = "//a[contains(text(),'Products')]")
    private WebElement productsBtn;

    private By consentButton = By.xpath("//button[contains(text(),'Consent')]");

    public HomePage(WebDriver driver) {
        super(driver); // VERY IMPORTANT
        this.driver = driver;
        PageFactory.initElements(driver, this); // VERY IMPORTANT
    }

    public boolean isHomeVisible() {
        return wait.until(
                ExpectedConditions.visibilityOf(homeLink)
        ).isDisplayed();
    }

    public void clickProducts() {

        try {
            wait.until(ExpectedConditions.elementToBeClickable(productsBtn)).click();
        } catch (Exception e) {
            // fallback if click intercepted
            driver.get("https://automationexercise.com/products");
            return;
        }

        // If redirected to google vignette, force correct URL
        if (driver.getCurrentUrl().contains("google_vignette")) {
            driver.get("https://automationexercise.com/products");
        }

        // Wait for products page URL
        wait.until(ExpectedConditions.urlContains("products"));
    }

    public void acceptCookiesIfPresent() {
        try {
            WebElement consent = driver.findElement(consentButton);
            if (consent.isDisplayed()) {
                consent.click();
            }
        } catch (Exception e) {
            // ignore
        }
    }
}