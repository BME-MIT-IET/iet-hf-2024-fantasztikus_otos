Feature: Making a Sticky Pipe
  As a player
  I want to create a sticky pipe
  So that I can connect it to a pump

  Scenario: Making a Sticky Pipe
    Given the map for making a sticky pipe is loaded from "bin/Tests/Huszonketto/Map.txt"
    And the players are created
    When Pipe 1 moves, Cistern moves, and Pump 2 is lifted up
    Then the pump should not be spawned
    When Pipe 1 moves again
    Then the pump should be spawned
