Feature: User Registration
As a new visitor
I want to create an account
So that I can shop and manage my orders

  Background:
    Given User is on the Home Page

  @regression @register
  Scenario: Successful registration with valid details
    When User navigates to the Create an Account page
    And User completes the registration form with valid information
    Then The user account should be created successfully
    And A success message “Thank you for registering” should be displayed

  Scenario: Registration with an already used email
    When User navigates to the Create an Account page
    And User submits the registration form using an existing email address
    Then The registration should not be completed
    And An error message “The specified email already exists” should be displayed

  Scenario: Registration with missing mandatory fields
    When User navigates to the Create an Account page
    And User submits the registration form without completing the required fields
    Then The registration should not be completed
    And Validation messages should be displayed for each mandatory field
