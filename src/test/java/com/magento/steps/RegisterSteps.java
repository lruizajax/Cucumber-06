package com.magento.steps;

import org.testng.Assert;

import com.magento.config.ConfigReader;
import com.magento.driver.DriverManager;
import com.magento.pages.HomePage;
import com.magento.pages.RegisterPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegisterSteps {

    private final HomePage homePage = new HomePage();
    private final RegisterPage registerPage = new RegisterPage();

    @Given("User is on the Home Page")
    public void user_is_on_the_home_page() {
        DriverManager.getDriver().get(ConfigReader.getAppUrl());
    }

    @When("User navigates to the Create an Account page")
    public void user_navigates_to_create_account_page() {
        homePage.goToRegister();
    }

    @When("User completes the registration form with valid information")
    public void user_completes_the_registration_form_with_valid_information() {
        registerPage.fillOutRegisterFormWithFakeData();
    }

    @Then("The user account should be created successfully")
    public void the_user_account_should_be_created_successfully() {
        registerPage.clickRegisterButton();
    }

    @Then("A success message “Thank you for registering” should be displayed")
    public void a_success_message_thank_you_for_registering_should_be_displayed() {
        Assert.assertEquals(registerPage.getRegistrationMessage(),
                "Thank you for registering with Main Website Store.");
    }
}
