package com.magento.steps;

import org.testng.Assert;

import com.magento.config.ConfigReader;
import com.magento.driver.DriverManager;
import com.magento.pages.CatalogPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CatalogSteps {

    private final CatalogPage catalogPage = new CatalogPage();

    @Given("I am on the product page for {string}")
    public void i_am_on_the_product_page_for(String productUrlKey) {
        DriverManager.getDriver().get(ConfigReader.getAppUrl() + "/" + productUrlKey + ".html");
    }

    @When("I add the product to the cart")
    public void i_add_the_product_to_the_cart() {
        catalogPage.clickAddToCart();
    }

    @When("I add {int} units to the cart")
    public void i_add_units_to_the_cart(int qty) {
        catalogPage.setQuantity(qty);
        catalogPage.clickAddToCart();
    }

    @When("I add the product {string} to the cart")
    public void i_add_the_product_to_the_cart(String productUrlKey) {
        DriverManager.getDriver().get(ConfigReader.getAppUrl() + "/" + productUrlKey + ".html");
        catalogPage.clickAddToCart();
    }

    @Then("the cart shows the product {string}")
    public void the_cart_shows_the_product(String productName) {
        String message = catalogPage.getSuccessMessage();
        Assert.assertTrue(message.contains(productName),
                "Expected message to contain '" + productName + "' but got: " + message);
    }

    @Then("the cart has {int} items")
    public void the_cart_has_items(int expectedCount) {
        catalogPage.waitForCartItemCount(expectedCount);
        Assert.assertEquals(catalogPage.getCartItemCount(), expectedCount);
    }

    @Then("the subtotal is {string}")
    public void the_subtotal_is(String expectedSubtotal) {
        catalogPage.openCart();
        Assert.assertEquals(catalogPage.getSubtotal(), expectedSubtotal);
    }
}
