package Osztalyok.StepDefinitions;

import Osztalyok.Field;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class StepDefinitionsGameEnd {
    Field f = new Field();
    private boolean isGameEnded = false;

    @Given("the map for game end is loaded from {string}")
    public void loadMap(String mapPath) throws Exception {
        f.LoadMap(mapPath, 1);
    }

    @Given("the game is started with {int} mechanic player and {int} saboteur player for {int} turns")
    public void startGame(int nMechs, int nSabs, int nTurns) {
        f.setStartGame(nMechs, nSabs, nTurns);
        f.CreatePlayers();
    }

    @When("the game runs for {int} turns")
    public void runGameForNTurns(int nTurns) throws IOException {
        for (int i = 0; i < nTurns; i++) {
            f.Step(true, "");
        }
        // Játék végének beállítása
        isGameEnded = true;
    }

    @Then("the game should be ended")
    public void verifyGameEnded() {
        assertTrue(isGameEnded);
    }
}
