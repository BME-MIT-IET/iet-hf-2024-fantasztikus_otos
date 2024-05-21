Feature: Testing pipe hole state manipulation
  As a tester
  I want to manipulate the state of pipe holes
  So that I can verify the functionality of setting pipes to a ruined state

  Scenario: Setting a pipe to ruined state
    Given the game is set up
    When I set the pipe with ID 1 to ruined state
    Then the pipe with ID 1 should be in ruined state
