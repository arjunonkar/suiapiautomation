package com.automation.pages;

import com.automation.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends BasePage {

    private WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);   // VERY IMPORTANT
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Your cart methods here
}