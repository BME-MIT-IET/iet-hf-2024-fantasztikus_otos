Feature: Complex Test 1

  Scenario: Complex test 1
    Given complex - the map for complex test is loaded from "bin/Tests/oszetett_2/Map.txt"
    And complex - the game is started with 3 mechanic players and 1 saboteur player for 10 turns
    When complex - player 1, 2, and 3 move to set up the pipeline
    And complex - player 1 moves the pipe to position 8
    And complex - player 1 moves the pump to position 4
    And complex - player 1 detaches the pipe from position 8
    And complex - a new pump is created
    And complex - player 2 lifts up the new pump
    And complex - one turn passes
    And complex - player 1 joins the pipe
    And complex - player 2 moves the pipe to position 8
    And complex - player 2 joins the pump at position 8
    Then complex - verify the game state