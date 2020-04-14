Feature: Login to existing account

  Scenario: Existing user can login to the account

    Given an open browser with https://prod-kurs.coderslab.pl/index.php?controller=authentication&back=my-account
    When insert correct email bartek@mail.com and password qwerty
    And click submit button
    Then user name Bartek Bartek displayed