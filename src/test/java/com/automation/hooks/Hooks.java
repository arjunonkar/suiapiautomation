package com.automation.hooks;

import com.automation.base.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;

import java.io.ByteArrayInputStream;

public class Hooks {

    @Before
    public void setUp() {
        DriverFactory.initDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                byte[] screenshot = DriverFactory.takeScreenshot();
                scenario.attach(screenshot, "image/png", scenario.getName());
            }
        } finally {
            DriverFactory.quitDriver();
        }
    }
}