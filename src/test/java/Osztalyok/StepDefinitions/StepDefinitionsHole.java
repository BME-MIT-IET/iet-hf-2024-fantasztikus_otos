package Osztalyok.StepDefinitions;

import Osztalyok.Field;
import Osztalyok.Pipe;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

import static org.junit.Assert.*;

public class StepDefinitionsHole {
    private Field field;
    private Pipe pipe;

    @Given("the game is set up")
    public void setUpGame() {
        field = new Field();
        try {
            field.LoadMap("bin/palya1.txt");
            field.setStartGame(1, 0, 10); // Assuming 1 mechanic player, 0 saboteur players, and 10 turns
            field.CreatePlayers();
        } catch (Exception e) {
            e.printStackTrace();
            fail("Failed to set up the game");
        }
    }

    @When("I set the pipe with ID {int} to ruined state")
    public void setPipeToRuinedState(int pipeId) {
        pipe = field.PipeSearch(pipeId);
        assertNotNull("Pipe with ID " + pipeId + " should exist", pipe);
        pipe.SetRuined(true);
    }

    @Then("the pipe with ID {int} should be in ruined state")
    public void verifyPipeIsRuined(int pipeId) throws IOException {
        assertNotNull("Pipe should not be null", pipe);
        assertTrue("Pipe should be in ruined state", pipe.GetRuined());
    }
}
