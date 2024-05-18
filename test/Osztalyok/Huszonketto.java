package Osztalyok;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import static org.junit.Assert.assertEquals;
public class Huszonketto {
    Field f = new Field();

    @Before
    public void setUp() throws Exception {
        f.LoadMap("bin/Tests/Huszonketto/Map.txt");
        f.CreatePlayers();
    }
    @Test
    public void MakeAStickyPipe() throws IOException {
        f.Step(true, "PipeMove 1,CisternMove,LiftUpPump 2");
        assertEquals(f.GetCistern().GetPumpSpawned() ,false);
        f.Step(true, "PipeMove 1");
        assertEquals(f.GetCistern().GetPumpSpawned() ,true);
    }
}
