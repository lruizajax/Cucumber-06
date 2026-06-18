package com.magento.steps;

import org.testng.Assert;

import com.magento.config.ConfigReader;
import com.magento.driver.DriverManager;
import com.magento.pages.HomePage;
import com.magento.pages.LoginPage;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

    private final HomePage homePage = new HomePage();
    private final LoginPage loginPage = new LoginPage();

    @When("User navigates to the Sign In page")
    public void user_navigates_to_the_sign_in_page() {
        DriverManager.getDriver().get(ConfigReader.getAppUrl());
        homePage.goToLogin();
    }

    @When("User signs in using valid credentials")
    public void user_signs_in_using_valid_credentials() {
        loginPage.fillOutLoginForm("lruizajax@gmail.com", "Password123$");
        loginPage.clickLoginButton();
    }

    @Then("User should be redirected to the Dashboard")
    public void user_should_be_redirected_to_the_dashboard() {
        Assert.assertTrue(loginPage.isLoggedIn());
    }

    @Then("A welcome message should be displayed")
    public void a_welcome_message_should_be_displayed() {
        String welcome = loginPage.getWelcomeMessage();
        Assert.assertTrue(welcome.contains("Welcome"),
                "Expected welcome message to contain 'Welcome' but got: " + welcome);
    }

    @When("User signs in using invalid credentials")
    public void user_signs_in_using_invalid_credentials() {
        loginPage.fillOutLoginForm("invalid@example.com", "WrongPassword");
        loginPage.clickLoginButton();
    }
}
