Feature: Testing pipe hole state manipulation

  Scenario: Setting a pipe to ruined state
    Given the game is set up
    When I set the pipe with ID 1 to ruined state
    Then the pipe with ID 1 should be in ruined state
