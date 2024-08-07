Feature: Product search and selection - positive flow

  Scenario: User adds a product to the basket
    Given Home page is opened
    When I click Urządzenia dropdown button in top menu
    Then I see submenu dropdown list
    And I click on Smartfony under Bez abonamentu
    Then I see product grid with smartphones
    And I click on the first product
    Then I see product view page
    And I add product to the basket
    Then I see basket main page
    And I see correct prices of chosen product in the basket
    And I go back to the home page
    Then I see in the basket icon one product added