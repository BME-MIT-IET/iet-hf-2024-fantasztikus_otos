package Osztalyok;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import static org.junit.Assert.assertEquals;
public class Husz {
    Field f = new Field();

    @Before
    public void setUp() throws Exception {
        f.LoadMap("bin/Tests/Tizenkilenc/Map.txt");
        f.CreatePlayers();
    }
    @Test
    public void MakeAStickyPipe() throws IOException {
        f.Step(true, "PipeMove 1,CisternMove,LiftUpPipe 2");
        assertEquals(f.GetTeams(1).GetPlayer(0).item.ToString() +f.GetTeams(1).GetPlayer(0).item.GetId() ,"Pipe2");
    }
}
