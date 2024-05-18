package Osztalyok;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
public class Harmincegy {
    Field f = new Field();

    @Before
    public void setUp() throws Exception {
        f.LoadMap("bin/Tests/Harmincegy/Map.txt");
        f.CreatePlayers();
    }
    @Test
    public void MakeAStickyPipe() throws IOException {
        f.Step(true, "PipeMove 1,Hole");
        f.Step(true, "RepairPipe 1");
        f.Step(true, "Hole");
        assertEquals(f.GetTeams(1).GetPlayer(0).pipeline.GetRuined() ,false);
    }
}
