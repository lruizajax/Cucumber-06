package com.magento.driver;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.magento.config.ConfigReader;

public class DriverManager {

    private static final ThreadLocal<WebDriverWait> waitThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    public static WebDriverWait getWait() {
        WebDriverWait wait = waitThreadLocal.get();
        if (wait == null) {
            wait = new WebDriverWait(getDriver(), Duration.ofSeconds(ConfigReader.getExplicitTimeout()));
            waitThreadLocal.set(wait);
        }
        return wait;
    }

    public static void initWait() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(ConfigReader.getExplicitTimeout()));
        waitThreadLocal.set(wait);
    }

    public static void cleanUp() {
        waitThreadLocal.remove();
        DriverFactory.quitDriver();
    }
}
