Feature: Log in to account

  Scenario Outline: user can log in to an existing account

    Given open browser with https://prod-kurs.coderslab.pl/index.php?controller=authentication&back=my-account/
    When incorrect <email> or <password> are provided
    And button signIn pressed
    Then error showed
    And browser closed

    Examples:
    |email            |password   |
    |Janek0@mail.com  |password145|
    |Janek9@mail.com  |password123|