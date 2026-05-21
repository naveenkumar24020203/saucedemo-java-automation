Feature: SauceDemo sample flows
  Validate SauceDemo scenarios using Java, Selenium, Cucumber, and TestNG.

  Scenario: Add a product to the cart and verify it appears
    Given I open the SauceDemo login page
    When I login with valid credentials
    Then I should see the products page
    When I add "Sauce Labs Backpack" to cart
    And I open the cart
    Then I should see "Sauce Labs Backpack" in the cart

  Scenario: Remove a product from the cart and verify it is removed
    Given I open the SauceDemo login page
    When I login with valid credentials
    Then I should see the products page
    When I add "Sauce Labs Bike Light" to cart
    And I open the cart
    Then I should see "Sauce Labs Bike Light" in the cart
    And I remove "Sauce Labs Bike Light" from cart
    Then I should not see "Sauce Labs Bike Light" in the cart

  Scenario Outline: Complete checkout flow and confirm order
    Given I open the SauceDemo login page
    When I login with valid credentials
    Then I should see the products page
    When I add "<product>" to cart
    And I open the cart
    Then I should see "<product>" in the cart
    When I checkout with "<firstName>" "<lastName>" "<postalCode>"
    And I finish the purchase
    Then I should see the order confirmation

    Examples:
      | product               | firstName | lastName | postalCode |
      | Sauce Labs Bolt T-Shirt | John      | Smith    | 10001      |

  Scenario: Login with invalid credentials
    Given I open the SauceDemo login page
    When I login with username "locked_out_user" and password "secret_sauce"
    Then I should see an error message "Sorry, this user has been locked out."
