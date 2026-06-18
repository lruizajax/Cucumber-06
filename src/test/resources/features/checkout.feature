Feature: Checkout
  As a customer
  I want to complete the purchase of my products
  So that I can receive my order

	@regression @checkout
  Scenario: Guest purchases a product with standard shipping and payment
    Given I have the product "essenza-mini-coffee" in the cart
    When I complete checkout as a guest with default shipping and payment details
    Then I see the order confirmation with the message "Thank you for your purchase"

  Scenario: Guest purchases multiple products and total includes shipping
    Given I have the products "airpods-pro-2" and "ultraboost-running" in the cart
    When I complete checkout with "Flat Rate" shipping and "Check / Money Order" payment
    Then the order total includes the product cost and shipping

  Scenario: Guest checkout with different billing and shipping address
    Given I have the product "nike-club-hoodie" in the cart
    When I complete checkout with different shipping and billing addresses
    Then the order is generated with both addresses registered correctly
