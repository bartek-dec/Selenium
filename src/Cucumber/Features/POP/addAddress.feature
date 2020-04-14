Feature: Add new address after login

  Scenario Outline: Add user's address after login
    Given user logged in to his account
    When user goes to addresses page
    And provides <address> and <city> and <postalCode> and <country> and saves
    Then new address is added
    And user closes the browser

    Examples:
    |address  |city    |postalCode  |country|
    |Address 1|Warszawa|12345       |17     |
    |Address 2|Lublin  |12389       |17     |
    |Address 3|Wroclaw |98765       |17     |