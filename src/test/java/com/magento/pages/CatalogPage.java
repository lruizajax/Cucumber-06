package com.magento.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CatalogPage extends BasePage {

    private static final By ADD_TO_CART_BUTTON = By.cssSelector("#product-addtocart-button");
    private static final By QUANTITY_INPUT = By.cssSelector("#qty");
    private static final By SUCCESS_MESSAGE = By.cssSelector(".message-success");
    private static final By CART_ICON = By.cssSelector(".action.showcart");
    private static final By SUBTOTAL_ELEMENT = By.cssSelector(".subtotal .price");
    private static final By CART_COUNTER = By.cssSelector(".showcart .counter-number");
    private static final By MINICART_OPEN = By.cssSelector(".block-minicart.active");

    public void setQuantity(int qty) {
        $(QUANTITY_INPUT).clear();
        $(QUANTITY_INPUT).sendKeys(String.valueOf(qty));
    }

    public void clickAddToCart() {
        getWait().until(ExpectedConditions.elementToBeClickable(ADD_TO_CART_BUTTON)).click();
        getWait().until(ExpectedConditions.visibilityOfElementLocated(SUCCESS_MESSAGE));
    }

    public String getSuccessMessage() {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(SUCCESS_MESSAGE));
        return $(SUCCESS_MESSAGE).getText();
    }

    public void openCart() {
        if (driver().findElements(MINICART_OPEN).isEmpty()) {
            getWait().until(ExpectedConditions.elementToBeClickable(CART_ICON)).click();
            getWait().until(ExpectedConditions.visibilityOfElementLocated(SUBTOTAL_ELEMENT));
        }
    }

    public void refreshCartCounter() {
        getWait().until(ExpectedConditions.elementToBeClickable(CART_COUNTER)).click();
    }

    public String getSubtotal() {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(SUBTOTAL_ELEMENT));
        return $(SUBTOTAL_ELEMENT).getText();
    }

    public void waitForCartItemCount(int expected) {
        getWait().until(ExpectedConditions.textToBePresentInElementLocated(CART_COUNTER, String.valueOf(expected)));
    }

    public int getCartItemCount() {
        String text = $(CART_COUNTER).getText().trim();
        return Integer.parseInt(text.replaceAll("[^0-9]", ""));
    }
}
