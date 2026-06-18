Feature: Shopping Cart
  As a customer
  I want to manage products in my shopping cart
  So that I can review and adjust my order before checkout

@regression @single_product 
  Scenario: Add product to cart
    Given I am on the product page for "airpods-pro-2"
    When I add the product to the cart
    Then the cart shows the product "Apple AirPods Pro 2"
    And the subtotal is "$249.99"
@regression @multi_product
  Scenario: Add multiple products
    Given I am on the product page for "levis-501-original"
    When I add 1 units to the cart
    And I add the product "rayban-wayfarer" to the cart
    Then the cart has 2 items
    And the subtotal is "$259.98"

  Scenario: Modify quantity in cart
    Given I have the product "airpods-pro-2" in the cart
    When I update the quantity to 3
    Then the product subtotal is "$749.97"

  Scenario: Remove product from cart
    Given I have the product "wh-1000xm6" in the cart
    When I remove the product from the cart
    Then the cart is empty
