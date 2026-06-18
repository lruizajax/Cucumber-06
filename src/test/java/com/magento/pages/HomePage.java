package com.magento.pages;

import org.openqa.selenium.By;

public class HomePage extends BasePage {

    private static final By CREATE_ACCOUNT_LINK = By.linkText("Create an Account");
    private static final By SIGN_IN_LINK = By.linkText("Sign In");

    public void goToRegister() {
        $(CREATE_ACCOUNT_LINK).click();
    }

    public void goToLogin() {
        $(SIGN_IN_LINK).click();
    }
}
