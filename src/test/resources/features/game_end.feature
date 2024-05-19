Feature: Game End
  As a player
  I want the game to end after a certain number of turns
  So that there is a clear conclusion to the gameplay

  Scenario: The game should end after a certain number of turns
    Given the map for game end is loaded from "bin/Tests/harmincharom/Map.txt"
    And the game is started with 1 mechanic player and 1 saboteur player for 10 turns
    When the game runs for 10 turns
    Then the game should be ended
