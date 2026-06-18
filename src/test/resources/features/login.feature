Feature: User Login
As a registered customer
I want to log in and log out securely
So that I can access my account and shop with confidence

  Background:
    Given User is on the Home Page

  @regression @login
  Scenario: Successful login with valid credentials
    When User navigates to the Sign In page
    And User signs in using valid credentials
    Then User should be redirected to the Dashboard
    And A welcome message should be displayed

  Scenario: Unsuccessful login with invalid credentials
    When User navigates to the Sign In page
    And User signs in using invalid credentials
    Then Access to the account should be denied
    And An error message “Login was unsuccessful” should be displayed

  Scenario: Successful logout
    Given User is authenticated in the application
    When User logs out from the application
    Then User should be redirected to the Home Page
    And A confirmation message “You have been logged out successfully” should be displayed
