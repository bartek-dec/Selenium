Feature: Change user information after login

  Scenario: Change user birthday and sign up for a newsletter
    Given User is logged in to CodersLab shop
    When User goes to UserInformationPage
    And User sign up for newsletter
    And Change birthday
    And User saves information
    Then User sees Information successfully updated.
    And user closes browser