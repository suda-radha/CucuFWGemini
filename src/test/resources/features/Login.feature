Feature: Login Page Functionality

  Scenario Outline: Data-driven login for different roles
    Given I navigate to the login page
    When I enter username "<username>" and password "<password>"
    And I select the "<role>" radio button
    And I select "<occupation>" from the occupation dropdown
    And I check the terms and conditions checkbox
    And I click the Sign In button
    Then I should be redirected to the product page

    Examples:
      | username           | password          | role  | occupation |
      | rahulshettyacademy | Learning@830$3mK2 | Admin | Teacher    |
      | rahulshettyacademy | Learning@830$3mK2 | User  | Consultant |