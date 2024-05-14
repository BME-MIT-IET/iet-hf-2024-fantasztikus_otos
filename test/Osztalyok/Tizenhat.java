package Osztalyok;

import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.*;

public class Tizenhat
{
    Field f = new Field();

    @Before
    public void setUp() throws Exception {
        f.LoadMap("bin/Tests/16,17,18/Map.txt");
        f.CreatePlayers();
    }
    @Test
    public void MoveFromCisternToSlipperyPipe() throws IOException {
        f.Step(true, "PipeMove 1");
        assertTrue(f.GetTeams(0).GetPlayer(0).GetPipeline().GetId() == 2 ||
                f.GetTeams(0).GetPlayer(0).GetPipeline() == f.GetCistern());
    }

}