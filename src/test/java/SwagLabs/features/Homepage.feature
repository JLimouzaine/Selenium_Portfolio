@Swag_Labs
  Feature: Homepage Functionalities
    Objective: Verify the Swag Labs homepage loads correctly and navigation links are functional.

    @TC_01
    Scenario Outline: User Log in
      Given user is on "Swag Labs" website
      And Website finishes loading
      Then user logs in as "<username>"
      Then user is redirected to products page

      Examples:
        | username                |
        | standard_user           |
        | problem_user            |
        | performance_glitch_user |
        | error_user              |
        | visual_user             |


    @TC_04
    Scenario: User Logout
      Given user is on "Swag Labs" website
      And Website finishes loading
      Then user logs in as "standard_user"
      And user logs out


    @TC_05
    Scenario:
      Given user is on "Swag Labs" website
      And Website finishes loading
      Then user logs in as "locked_out_user"
      Then check if user is locked
