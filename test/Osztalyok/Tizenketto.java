package Osztalyok;

import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.*;

public class Tizenketto
{
    Field f = new Field();

    @Before
    public void setUp() throws Exception {
        f.LoadMap("bin/Tests/12,9/Map.txt");
        f.CreatePlayers();
    }
    @Test
    public void MoveFromPumpToSlipperyPipe() throws IOException {
        f.Step(true, "PipeMove 1");
        assertTrue(f.GetTeams(1).GetPlayer(0).GetPipeline().GetId() == 3 ||
                f.GetTeams(1).GetPlayer(0).GetPipeline().GetId() == 4);
    }

}
