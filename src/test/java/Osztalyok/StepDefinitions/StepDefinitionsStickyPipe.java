package Osztalyok.StepDefinitions;

import Osztalyok.Field;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class StepDefinitionsStickyPipe {
    Field f = new Field();

    @Given("the map for making a sticky pipe is loaded from {string}")
    public void loadMapForStickyPipe(String mapPath) throws Exception {
        f.LoadMap(mapPath, 1);
    }

    @Given("the players are created")
    public void createPlayers() {
        f.CreatePlayers();
    }

    @When("Pipe {int} moves, Cistern moves, and Pump {int} is lifted up")
    public void stepWithPipeAndPump(int pipeNumber, int pumpNumber) throws IOException {
        f.Step(true, "PipeMove " + pipeNumber + ",CisternMove,LiftUpPump " + pumpNumber);
    }

    @Then("the pump should not be spawned")
    public void verifyPumpNotSpawned() {
        assertEquals(false, f.GetCistern().GetPumpSpawned());
    }

    @When("Pipe {int} moves again")
    public void stepWithPipeAgain(int pipeNumber) throws IOException {
        f.Step(true, "PipeMove " + pipeNumber);
    }

    @Then("the pump should be spawned")
    public void verifyPumpSpawned() {
        assertEquals(true, f.GetCistern().GetPumpSpawned());
    }
}
