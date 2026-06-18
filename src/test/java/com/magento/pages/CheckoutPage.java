package com.magento.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.magento.config.ConfigReader;
import com.magento.driver.DriverManager;
import net.datafaker.Faker;

public class CheckoutPage extends BasePage {

    private static final By ADD_TO_CART_BUTTON = By.cssSelector("#product-addtocart-button");
    private static final By SUCCESS_MESSAGE = By.cssSelector(".message-success");
    private static final By CART_ICON = By.cssSelector(".action.showcart");
    private static final By MINICART_OPEN = By.cssSelector(".block-minicart.active");
    private static final By PROCEED_TO_CHECKOUT = By.cssSelector("#top-cart-btn-checkout");

    private static final By CUSTOMER_EMAIL = By.cssSelector("#customer-email");
    private static final By FIRST_NAME = By.cssSelector("input[name='firstname']");
    private static final By LAST_NAME = By.cssSelector("input[name='lastname']");
    private static final By STREET = By.cssSelector("input[name='street[0]']");
    private static final By CITY = By.cssSelector("input[name='city']");
    private static final By STATE = By.cssSelector("select[name='region_id']");
    private static final By ZIP = By.cssSelector("input[name='postcode']");
    private static final By PHONE = By.cssSelector("input[name='telephone']");

    private static final By LOADING_MASK = By.cssSelector(".loading-mask");
    private static final By NEXT_BUTTON = By.cssSelector(".button.action.continue.primary");
    private static final By PAYMENT_METHOD = By.cssSelector(".payment-method");
    private static final By PAYMENT_METHOD_LABEL = By.cssSelector(".payment-method-title .label");
    private static final By PLACE_ORDER = By.cssSelector(".payment-method .action.primary.checkout");
    private static final By CHECKOUT_SUCCESS = By.cssSelector(".checkout-success");
    private static final By CONFIRMATION_TITLE = By.cssSelector(".page-title-wrapper span[data-ui-id='page-title-wrapper']");

    private final Faker faker = new Faker();

    public void addProductToCart(String productUrlKey) {
        DriverManager.getDriver().get(ConfigReader.getAppUrl() + "/" + productUrlKey + ".html");
        getWait().until(ExpectedConditions.elementToBeClickable(ADD_TO_CART_BUTTON)).click();
        getWait().until(ExpectedConditions.visibilityOfElementLocated(SUCCESS_MESSAGE));
    }

    public void proceedToCheckout() {
        if (driver().findElements(MINICART_OPEN).isEmpty()) {
            getWait().until(ExpectedConditions.elementToBeClickable(CART_ICON)).click();
        }
        getWait().until(ExpectedConditions.elementToBeClickable(PROCEED_TO_CHECKOUT)).click();
    }

    public void fillGuestShipping() {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(CUSTOMER_EMAIL));

        $(CUSTOMER_EMAIL).sendKeys(faker.internet().emailAddress());
        $(FIRST_NAME).sendKeys(faker.name().firstName());
        $(LAST_NAME).sendKeys(faker.name().lastName());
        $(STREET).sendKeys(faker.address().streetAddress());
        $(CITY).sendKeys(faker.address().city());
        new Select($(STATE)).selectByVisibleText(faker.address().state());
        $(ZIP).sendKeys(faker.address().zipCode());
        $(PHONE).sendKeys(faker.phoneNumber().phoneNumber());
    }

    public void clickNext() {
        getWait().until(ExpectedConditions.invisibilityOfElementLocated(LOADING_MASK));
        WebElement next = getWait().until(ExpectedConditions.elementToBeClickable(NEXT_BUTTON));
        int attempts = 0;
        while (attempts < 5) {
            try {
                next.click();
                return;
            } catch (ElementClickInterceptedException e) {
                getWait().until(ExpectedConditions.invisibilityOfElementLocated(LOADING_MASK));
                attempts++;
            }
        }
        next.click();
    }

    public void placeOrder() {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(PAYMENT_METHOD));
        WebElement paymentLabel = getWait().until(ExpectedConditions.elementToBeClickable(PAYMENT_METHOD_LABEL));
        ((JavascriptExecutor) driver()).executeScript("arguments[0].click();", paymentLabel);
        getWait().until(ExpectedConditions.invisibilityOfElementLocated(LOADING_MASK));
        WebElement placeOrder = getWait().until(ExpectedConditions.presenceOfElementLocated(PLACE_ORDER));
        ((JavascriptExecutor) driver()).executeScript("arguments[0].click();", placeOrder);
    }

    public String getConfirmationMessage() {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(CHECKOUT_SUCCESS));
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(CONFIRMATION_TITLE)).getText();
    }
}
