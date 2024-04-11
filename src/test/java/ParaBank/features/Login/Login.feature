@ParaBank
Feature: Log in Functionalities
  Objective: Verify the ParaBank log in page loads correctly and navigation links are functional.

  @TC_06
  Scenario: Customer Registration
    Given user is on "Para Bank" website
    And Website finishes loading
    Then user clicks on "Register" link
    And user enters their data
    Then user enters their username and password
    And user confirms their password
    And user clicks on "Register" button
    Then user is logged in

  @TC_07
  Scenario: Verify Customer Login
    Given user is on "Para Bank" website
    And Website finishes loading
    Then user logs in to their bank account
    And user clicks on "Log In" button
    Then check if user is logged in

@TC_11
  Scenario: Verify Customer Log out
  Given user is on "Para Bank" website
  And Website finishes loading
  Then user logs in to their bank account
  And user clicks on "Log In" button
  And Website finishes loading
  Then user clicks on "Log Out" link
  And user verifies if they logged out
