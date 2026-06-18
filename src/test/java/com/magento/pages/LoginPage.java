package com.magento.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    private static final By EMAIL_INPUT = By.id("email");
    private static final By PASSWORD_INPUT = By.id("password");
    private static final By LOGIN_BUTTON = By.cssSelector("button.action.login.primary");

    private static final By WELCOME_MESSAGE = By.cssSelector(".greet.welcome span.logged-in");

    public void fillOutLoginForm(String email, String password) {
        $(EMAIL_INPUT).sendKeys(email);
        $(PASSWORD_INPUT).sendKeys(password);
    }

    public void clickLoginButton() {
        $(LOGIN_BUTTON).click();
    }

    public boolean isLoggedIn() {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(WELCOME_MESSAGE));
        return true;
    }

    public String getWelcomeMessage() {
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(WELCOME_MESSAGE)).getText();
    }
}
