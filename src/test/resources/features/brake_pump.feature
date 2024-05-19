Feature: Breaking a Pump
  As a player
  I want to break a pump
  So that I can see if the pump is ruined

  Scenario: Break the pump successfully
    Given the map for break pump is loaded from "bin/palya1.txt"
    And the game is set up with 1 mechanic and 0 saboteurs for 10 turns
    When I step in the game with "BreakPump"
    And I ruin the pump
    Then the pump should be ruined
