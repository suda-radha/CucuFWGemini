Feature: Shop page functionality testing

  Background: Successful login to the shop page
    Given I navigate to the login page
    When I enter username "rahulshettyacademy" and password "Learning@830$3mK2"
    And I check the terms and conditions checkbox
    And I click the Sign In button
    Then I should be redirected to the product page

  # --- Test 1: Cart Counter ---
 # Scenario Outline: Add specific products and verify cart count
  #  Given I am on the shop page
   # When I click the Add button for "<productName>"
    #Then the cart badge should display "<expectedCount>"

    #Examples:
    #  | productName    | expectedCount |
    #  | iphone X       | 1             |
     # | Samsung Note 8 | 1             |

  # --- Test 2: Summary List ---
  Scenario Outline: Verify products in the checkout summary
    Given I am on the shop page
    When I add "<item1>" to the cart
    And I add "<item2>" to the cart
    And I click on the "Checkout" button
    Then I should see "<item1>" and "<item2>" in the summary list

    Examples:
      | item1    | item2          |
      | iphone X | Samsung Note 8 |

  # --- Test 3: Navigation Flow ---
  @focus
  Scenario Outline: Navigate to delivery page for different items
    Given I am on the shop page
    And I have added "<product>" to my cart
    When I click on the "Checkout" button
    And I click the final "Checkout" button on the summary page
    Then I should be navigated to the "Delivery Location" page

    Examples:
      | product    |
      | Blackberry |
      | Nokia Edge |
