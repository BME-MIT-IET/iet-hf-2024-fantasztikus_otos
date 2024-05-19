Feature: Retool Pump Functionality
  As a player
  I want to retool a pump
  So that I can connect it to a pipe

  Scenario: Retooling a pump
    Given the map for retool pump is loaded from "bin/palya1.txt"
    And the game is started with 1 mechanic player and 0 saboteur players, for 10 turns
    When pump 1 is retooled
    Then pipe 1 should be connected to pump 1
