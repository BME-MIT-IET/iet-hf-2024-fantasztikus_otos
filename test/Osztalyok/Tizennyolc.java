package Osztalyok;

import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.*;

public class Tizennyolc
{
    Field f = new Field();

    @Before
    public void setUp() throws Exception {
        f.LoadMap("bin/Tests/16,17,18/Map.txt");
        f.PipeSearch(1).SetSlippery(0);
        f.PipeSearch(1).SetSticky(2);
        f.CreatePlayers();
    }
    @Test
    public void MoveFromStickyPipeToCistern() throws IOException {
        f.Step(true, "PipeMove 1, CisternMove");

        assertFalse(f.GetTeams(0).GetPlayer(0).GetPipeline() ==f.GetCistern());
    }

}