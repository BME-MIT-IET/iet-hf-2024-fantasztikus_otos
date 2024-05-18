package Osztalyok;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class Huszonharom {

    Field f = new Field();

    @Before
    public void setUp() throws Exception {
        f.LoadMap("bin/Tests/Huszonharom/Map.txt");
        f.CreatePlayers();
    }
    @Test
    public void MoveFromStickyPipe() throws IOException {
            f.Step(true, "PipeMove 1,PumpMove 2");
            assertEquals(f.GetTeams(1).GetPlayer(0).GetPipeline().ToString() +f.GetTeams(1).GetPlayer(0).GetPipeline().GetId(),"Pipe1");

        }
    }

