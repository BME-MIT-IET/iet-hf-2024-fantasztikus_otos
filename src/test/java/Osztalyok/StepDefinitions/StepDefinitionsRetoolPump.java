package Osztalyok.StepDefinitions;

import Osztalyok.Field;
import Osztalyok.Pump;
import Osztalyok.Pipe;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class StepDefinitionsRetoolPump {
    Field f = new Field();
    Pump pu;
    Pipe pi;

    @Given("the map for retool pump is loaded from {string}")
    public void loadMap(String mapPath) throws Exception {
        f.LoadMap(mapPath, 1);
    }

    @Given("the game is started with {int} mechanic player and {int} saboteur players, for {int} turns")
    public void startGame(int nMechs, int nSabs, int nTurns) {
        f.setStartGame(nMechs, nSabs, nTurns);
        f.CreatePlayers();
    }

    @When("pump {int} is retooled")
    public void retoolPump(int pumpNumber) throws IOException {
        pu = new Pump(pumpNumber, 1, 1, 1, f.GetCistern(), f);
        pi = new Pipe(1, true, 1, f.GetCistern(), f, 0, 0, 0);
        f.Step(true, "Retool " + pumpNumber);
        pu.AddPipe(pi);
        pi.AddPump(pu);
        pu.SetPipeOut(pi);
    }

    @Then("pipe {int} should be connected to pump {int}")
    public void verifyPipeConnectedToPump(int pipeNumber, int pumpNumber) {
        assertEquals(pi, pu.GetActiveOut());
    }
}
