package Osztalyok;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotEquals;

public class Huszonnegy {
    Field f = new Field();

    @Before
    public void setUp() throws Exception {
        f.LoadMap("bin/Tests/Huszonnegy/Map.txt");
        f.CreatePlayers();
    }
    @Test
    public void MakeAStickyPipe() throws IOException {
        f.Step(true, "PipeMove 1,Sticky");
        assertNotEquals(f.GetTeams(1).GetPlayer(0).GetPipeline().GetSticky(),0);

    }
}
