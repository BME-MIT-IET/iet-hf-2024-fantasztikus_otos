package Osztalyok;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotEquals;

public class Harmincnegy {
    Field f = new Field();

    @Before
    public void setUp() throws Exception {
        f.LoadMap("bin/Tests/Harmincnegy/Map.txt");
        f.CreatePlayers();
    }
    @Test
    public void MoveFromStickyPipe() throws IOException {
        f.Step(true, "DetachPipe 1");
        assertNotEquals(f.GetTeams(1).GetPlayer(0).item,null);

    }
}
