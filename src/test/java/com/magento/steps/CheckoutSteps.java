package com.magento.steps;

import org.testng.Assert;

import com.magento.pages.CheckoutPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CheckoutSteps {

    private final CheckoutPage checkoutPage = new CheckoutPage();

    @Given("I have the product {string} in the cart")
    public void i_have_the_product_in_the_cart(String productUrlKey) {
        checkoutPage.addProductToCart(productUrlKey);
    }

    @When("I complete checkout as a guest with default shipping and payment details")
    public void i_complete_checkout_as_guest() {
        checkoutPage.proceedToCheckout();
        checkoutPage.fillGuestShipping();
        checkoutPage.clickNext();
        checkoutPage.placeOrder();
    }

    @Then("I see the order confirmation with the message {string}")
    public void i_see_order_confirmation(String expectedMessage) {
        String message = checkoutPage.getConfirmationMessage();
        Assert.assertTrue(message.contains(expectedMessage),
                "Expected confirmation to contain '" + expectedMessage + "' but got: " + message);
    }
}
