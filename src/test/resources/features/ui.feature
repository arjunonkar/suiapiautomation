Feature: UI Automation - AutomationExercise Website

  Background:
    Given User launches browser
    And User navigates to "https://automationexercise.com"

  @SearchProduct
  Scenario: Verify user can search products successfully

    Then Home page should be visible
    When User clicks Products
    Then All Products page should be visible
    When User searches for "Blue Top"
    Then Searched Products should be visible


  @AddToCart
  Scenario: Verify user can add multiple products to cart

    Then Home page should be visible
    When User clicks Products
    Then All Products page should be visible
    When User adds two products to cart
    Then Both products should be in cart