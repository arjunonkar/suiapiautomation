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
        wait.until(
                ExpectedConditions.elementToBeClickable(productsBtn)
        ).click();
    }

    public void acceptCookiesIfPresent() {
        try {
            WebElement consent = wait.until(
                    ExpectedConditions.elementToBeClickable(consentButton)
            );
            consent.click();
        } catch (Exception e) {
            // ignore if not present
        }
    }
}