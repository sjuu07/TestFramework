Feature: Navigate to a URL

  @smoke
  Scenario: Verify that a new user log in, and place an order
    Given user opens the browser
    When user navigates to url
    And click menu to provide login credentials
    Then verify the page title