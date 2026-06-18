package com.magento.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.magento.config.ConfigReader;
import com.magento.driver.DriverManager;

public abstract class BasePage {

    private WebDriver driver;
    private WebDriverWait wait;

    protected WebDriver driver() {
        if (driver == null) {
            this.driver = DriverManager.getDriver();
            this.wait = DriverManager.getWait();
        }
        return driver;
    }

    protected WebDriverWait getWait() {
        driver();
        return wait;
    }

    protected WebElement $(By locator) {
        return driver().findElement(locator);
    }

    protected String getBaseUrl() {
        return ConfigReader.getAppUrl();
    }
}
