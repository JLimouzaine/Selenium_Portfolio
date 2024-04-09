@Swag_Labs
Feature: Product Page Functionalities
  Objective: Verify the Swag Labs Product page loads and works as intended.

  @TC_02
  Scenario Outline: Product Selection
    Given user is on "Swag Labs" website
    And Website finishes loading
    Then user logs in as "standard_user"
    And user adds "<Items>" items to cart
    Then check if "<Count>" items are on cart
    Examples:
      | Items         | Count |
      | Miscellaneous | 2     |
      | Tops          | 3     |
      | Baby          | 1     |

    @TC_03
    Scenario Outline: Checkout Process
      Given user is on "Swag Labs" website
      And Website finishes loading
      Then user logs in as "standard_user"
      And user adds "<Items>" items to cart
      
      Examples:
        | Items         |
        | Miscellaneous |