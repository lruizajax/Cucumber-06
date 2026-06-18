package com.magento.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import net.datafaker.Faker;

public class RegisterPage extends BasePage {

    private static final By FIRST_NAME_INPUT = By.id("firstname");
    private static final By LAST_NAME_INPUT = By.id("lastname");
    private static final By EMAIL_INPUT = By.id("email_address");
    private static final By PASSWORD_INPUT = By.id("password");
    private static final By CONFIRM_PASSWORD_INPUT = By.id("password-confirmation");
    private static final By REGISTER_BUTTON = By.cssSelector("button[title='Create an Account']");
    private static final By SUCCESS_MESSAGE = By.xpath("//div[contains(@class,'message-success')]//div[contains(.,'Thank you for registering')]");

    public void fillOutRegisterForm(String fname, String lname, String email, String pass, String repass) {
        $(FIRST_NAME_INPUT).sendKeys(fname);
        $(LAST_NAME_INPUT).sendKeys(lname);
        $(EMAIL_INPUT).sendKeys(email);
        $(PASSWORD_INPUT).sendKeys(pass);
        $(CONFIRM_PASSWORD_INPUT).sendKeys(repass);
    }

    public void fillOutRegisterFormWithFakeData() {
        Faker faker = new Faker();
        String password = faker.regexify("[A-Z][a-z]{5}[0-9]{2}[@#$%]");

        $(FIRST_NAME_INPUT).sendKeys(faker.name().firstName());
        $(LAST_NAME_INPUT).sendKeys(faker.name().lastName());
        $(EMAIL_INPUT).sendKeys(faker.internet().emailAddress());
        $(PASSWORD_INPUT).sendKeys(password);
        $(CONFIRM_PASSWORD_INPUT).sendKeys(password);
    }

    public void clickRegisterButton() {
        $(REGISTER_BUTTON).click();
    }

    public String getRegistrationMessage() {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(SUCCESS_MESSAGE));
        return $(SUCCESS_MESSAGE).getText();
    }
}
