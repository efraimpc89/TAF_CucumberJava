@Login
Feature: Sauce Demo Login
  Login scenarios for sauce demo

  Scenario: S01 - Valid Login
    When user enters valid username
    And user enters valid password
    And click on login button
    Then I validate the user logged in

  Scenario: S02 - Empty Password
    When user enters valid username
    And user leaves password empty
    And click on login button
    Then I validate the "Epic sadface: Password is required..." message is displayed

  Scenario: S03 - Invalid Login
    When user enters valid username as "random_username"
    And user leaves password empty as "random_password"
    And click on login button
    Then I validate the "Epic sadface: Username and password do not match any user in this service" message is displayed
