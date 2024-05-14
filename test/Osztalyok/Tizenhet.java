package Osztalyok;

import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.*;

public class Tizenhet
{
    Field f = new Field();

    @Before
    public void setUp() throws Exception {
        f.LoadMap("bin/Tests/16,17,18/Map.txt");
        f.GetPipe(0).SetBusy(true);
        f.GetPipe(0).SetSlippery(0);
        f.CreatePlayers();
    }
    @Test
    public void MoveFromCisternToBusyPipe() throws IOException {
        f.Step(true, "PipeMove 1");
        assertTrue(f.GetTeams(0).GetPlayer(0).GetPipeline() ==f.GetCistern());
    }

}