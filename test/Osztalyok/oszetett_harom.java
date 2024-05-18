package Osztalyok;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class oszetett_harom {
    private static int nturns = 2; //Körök száma
    private static Field f = new Field();

    @Before
    public void setUp() throws Exception {
        f.LoadMap("bin/Tests/osszetett_3/Map.txt");
        f.setTurns(nturns);
        f.CreatePlayers();
        f.GetTeams(0).GetPlayer(0).PipeMove(f.PipeSearch(1));

        f.GetTeams(0).GetPlayer(0).PumpMove(f.PumpSearch(1));

        f.GetTeams(1).GetPlayer(0).PipeMove(f.PipeSearch(2));

        f.GetTeams(1).GetPlayer(0).PumpMove(f.PumpSearch(2));
        f.SetSabPoints(10);
        f.SetMechPoints(10);
    }

    @Test
    public void osszetett3() throws IOException {

        f.GetTeams(0).GetPlayer(0).PipeMove(f.PipeSearch(1));
        f.GetTeams(0).GetPlayer(0).Hole();
        f.GetTeams(1).GetPlayer(0).PipeMove(f.PipeSearch(2));
        f.GetTeams(1).GetPlayer(0).PumpMove(f.PumpSearch(1));
        f.Step(true,"");
        f.GetTeams(1).GetPlayer(0).Retool(f.PipeSearch(2));f.Step(true,"");f.Step(true,"");
        assertFalse(f.mecWinning());
        assertTrue(f.GetMechPoints() == 30 && f.GetSabPoints() == 40);
    }
}
