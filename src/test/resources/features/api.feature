Feature: API Testing

  @API
  Scenario: Get all products API
    Given User calls products API
    Then Response status should be 200