Feature: New account

  Scenario Outline: user can create new account

    Given an open browser with https://qloapps.coderslab.pl/
    When a Sign in button is pressed
    And email address <emailAddress> is provided
    And create an account button is pressed
    And personal information <firstName>, <lastName>, <password>, <day>, <month>, <year> are provided
    And register button is pressed
    Then account has been created confirmation is displayed
    And close the browser

    Examples:
      | emailAddress | firstName | lastName | password | day | month | year |
      | janek7@op.pl | Janek     | Kowalski | qwerty   | 12  | 4     | 2000 |
      | janek8@op.pl | Janek     | Kowalski | qwerty   | 15  | 6     | 2001 |
      | janek9@op.pl | Janek     | Kowalski | qwerty   | 17  | 9     | 1998 |