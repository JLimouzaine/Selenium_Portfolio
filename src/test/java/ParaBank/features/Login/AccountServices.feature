@ParaBank
Feature: Account Services Functionalities
  Objective: Verify the ParaBank account services page loads correctly and navigation links are functional.

  @TC_08
  Scenario Outline: Create an account
    Given user is on "Para Bank" website
    And Website finishes loading
    Then user logs in to their bank account
    And user clicks on "Log In" button
    And Website finishes loading
    Then user clicks on "Open New Account" link
    Then user selects "<Account Type>" type
    Then user clicks on "Open New Account" button
    And Website finishes loading
    And account is created

    Examples:
      | Account Type |
      | Checking     |
      | Savings      |

    @TC_09
    Scenario: Transfer Funds
      Given user is on "Para Bank" website
      And Website finishes loading
      Then user logs in to their bank account
      And user clicks on "Log In" button
      And Website finishes loading
      Then user clicks on "Transfer Funds" link
      And Website finishes loading
      And user enters amount to be transferred
      And user clicks on "Transfer" button
      And Website finishes loading
      Then user checks if money was transferred

    @TC_10
    Scenario: View Transaction History
      Given user is on "Para Bank" website
      And Website finishes loading
      Then user logs in to their bank account
      And user clicks on "Log In" button
      And Website finishes loading
      Then user clicks on "Find Transactions" link
      And user finds transaction by today's date
      Then user checks if a Transaction was made

    @TC_12
    Scenario: Update Contact Information
      Given user is on "Para Bank" website
      And Website finishes loading
      Then user logs in to their bank account
      And user clicks on "Log In" button
      And Website finishes loading
      Then user clicks on "Update Contact Info" link
      And user updates their contact info
      And user clicks on "Update Profile" button
      Then user verifies if contact info was updated


