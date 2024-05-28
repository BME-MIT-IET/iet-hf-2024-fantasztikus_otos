package Osztalyok.StepDefinitions;

import Osztalyok.Field;
import Osztalyok.Pump;
import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

public class StepDefinitionsBreakPump {
    private static int nmechs = 1;
    private static int nsabs = 0;
    private static int nturns = 10;
    private static Field f = new Field();
    private static Pump pu = new Pump(1, 1, 1, 1, f.GetCistern(), f);


    @Given("the map for break pump is loaded from {string}")
    public void the_map_is_loaded_from(String mapPath) throws IOException {
        f.LoadMap(mapPath, 1);
    }

    @Given("the game is set up with {int} mechanic and {int} saboteurs for {int} turns")
    public void the_game_is_set_up_with_mechanic_and_saboteurs_for_turns(int mechanics, int saboteurs, int turns) throws Exception {
        nmechs = mechanics;
        nsabs = saboteurs;
        nturns = turns;
        f.setStartGame(nmechs, nsabs, nturns);
        f.CreatePlayers();
    }

    @When("I step in the game with {string}")
    public void i_step_in_the_game_with(String action) throws IOException {
        f.Step(true, action);
    }

    @When("I ruin the pump")
    public void i_ruin_the_pump() {
        pu.Ruin(pu);
    }

    @Then("the pump should be ruined")
    public void the_pump_should_be_ruined() throws IOException {
        assertTrue(pu.GetRuined());
    }
}


