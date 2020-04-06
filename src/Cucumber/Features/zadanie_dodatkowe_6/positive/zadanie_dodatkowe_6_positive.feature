Feature: Log in to account

  Scenario Outline: user can log in to an existing account

    Given an open browser with https://prod-kurs.coderslab.pl/index.php?controller=authentication&back=my-account/
    When an <email> and <password> are provided
    And button sign in pressed
    Then users account is displayed
    And user is sign out and browser closed

    Examples:
    |email            |password   |
    |Janek0@mail.com  |password123|
    |Janek1@mail.com  |password123|
    |Janek2@mail.com  |password123|
    |Janek3@mail.com  |password123|
    |Janek4@mail.com  |password123|