Feature: Add some various ingredients to the inventory of the coffee maker
  The user will input a specific amount for each ingredients to be added to the
  inventory of the coffee maker.
  A default coffee maker will start with 15 of each ingredients.

  Background:
    Given A default coffee maker

  Scenario: add some coffee to the coffee maker
    When I add 20 coffee to the coffee maker
    Then Coffee maker have 35 coffee

  Scenario: add some milk to the coffee maker
    When I add 10 milk to the coffee maker
    Then Coffee maker have 25 milk

  Scenario: add some sugar to the coffee maker
    When I add 15 sugar to the coffee maker
    Then Coffee maker have 30 sugar

  Scenario: add some chocolate to the coffee maker
    When I add 50 chocolate to the coffee maker
    Then Coffee maker have 65 chocolate

  Scenario: add some chocolate and coffee to the coffee maker
    When I add 10 chocolate to the coffee maker
    And I add 15 coffee to the coffee maker
    Then Coffee maker have 30 coffee, 15 milk, 15 sugar, 25 chocolate

  Scenario: add some milk and sugar to the coffee maker
    When I add 5 milk to the coffee maker
    And I add 20 sugar to the coffee maker
    Then Coffee maker have 15 coffee, 20 milk, 35 sugar, 15 chocolate

  Scenario: add some milk to the coffee maker twice
    When I add 5 milk to the coffee maker
    And I add 10 milk to the coffee maker
    Then Coffee maker have 15 coffee, 30 milk, 15 sugar, 15 chocolate

  Scenario: add some sugar to the coffee maker twice
    When I add 15 sugar to the coffee maker
    And I add 1 sugar to the coffee maker
    Then Coffee maker have 15 coffee, 15 milk, 31 sugar, 15 chocolate

  Scenario: add all the ingredients to the inventory of the coffee maker
    When I add 15 coffee to the coffee maker
    And I add 20 milk to the coffee maker
    And I add 5 sugar to the coffee maker
    And I add 30 chocolate to the coffee maker
    Then Coffee maker have 30 coffee, 35 milk, 20 sugar, 45 chocolate

  Scenario: add all the ingredients except sugar to the inventory of the coffee maker
    When I add 15 coffee to the coffee maker
    And I add 20 milk to the coffee maker
    And I add 0 sugar to the coffee maker
    And I add 30 chocolate to the coffee maker
    Then Coffee maker have 30 coffee, 35 milk, 15 sugar, 45 chocolate