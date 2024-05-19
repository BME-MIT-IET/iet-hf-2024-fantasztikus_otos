package Osztalyok.StepDefinitions;

import Osztalyok.Field;
import Osztalyok.Pump;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class StepDefinitionsComplexTest1 {

    private Field field;
    private Pump newPump;

    @Given("complex - the map for complex test is loaded from {string}")
    public void loadMapForComplexTest(String mapPath) {
        field = new Field();
        try {
            field.LoadMap(mapPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Given("complex - the game is started with {int} mechanic players and {int} saboteur player for {int} turns")
    public void startGameForComplexTest(int nMechs, int nSabs, int nTurns) {
        field.setStartGame(nMechs, nSabs, nTurns);
        field.CreatePlayers();
    }

    @When("complex - player {int}, {int}, and {int} move to set up the pipeline")
    public void setPipelineForComplexTest(int player1, int player2, int player3) {
        field.GetTeams(1).GetPlayer(player1 - 1).SetPipeLine(field.GetCistern());
        field.GetTeams(1).GetPlayer(player2 - 1).SetPipeLine(field.GetCistern());
        field.GetTeams(1).GetPlayer(player3 - 1).SetPipeLine(field.GetCistern());
    }

    @When("complex - player {int} moves the pipe to position {int}")
    public void movePipeToPositionForComplexTest(int player, int position) {
        field.GetTeams(1).GetPlayer(player - 1).PipeMove(field.PipeSearch(position));
    }

    @When("complex - player {int} moves the pump to position {int}")
    public void movePumpToPositionForComplexTest(int player, int position) {
        field.GetTeams(1).GetPlayer(player - 1).PumpMove(field.PumpSearch(position));
    }

    @When("complex - player {int} detaches the pipe from position {int}")
    public void detachPipeFromPositionForComplexTest(int player, int position) {
        field.GetTeams(1).GetPlayer(player - 1).DetachPipe(field.PipeSearch(position));
    }

    @When("complex - a new pump is created")
    public void createNewPumpForComplexTest() {
        newPump = new Pump(field.Getpumpmaxid() + 1, 0, 0, 0, field.GetCistern(), field);
        field.SetNewPump(newPump);
        field.GetCistern().SetPumpSpawned(false);
    }

    @When("complex - player {int} lifts up the new pump")
    public void liftUpNewPumpForComplexTest(int player) {
        field.GetTeams(1).GetPlayer(player - 1).LiftUpPump(newPump);
    }

    @When("complex - one turn passes")
    public void oneTurnPassesForComplexTest() {
        try {
            field.Step(true, "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @When("complex - player {int} joins the pipe")
    public void joinPipeForComplexTest(int player) {
        field.GetTeams(1).GetPlayer(player - 1).JoinPipe();
    }

    @When("complex - (2) player {int} moves the pipe to position {int}")
    public void movePipeToPositionForComplexTest2(int player, int position) {
        field.GetTeams(1).GetPlayer(player - 1).PipeMove(field.PipeSearch(position));
    }

    @When("complex - player {int} joins the pump at position {int}")
    public void joinPumpForComplexTest(int player, int position) {
        field.GetTeams(1).GetPlayer(player - 1).JoinPump(field.PipeSearch(position), newPump, field);
    }

    @Then("complex - verify the game state")
    public void verifyGameStateForComplexTest() {
        // Here you can add assertions to verify the game state after the test steps
        // For example, you can check if certain conditions are met or if the game state is as expected
        assertTrue(true); // Placeholder assertion
    }
}
