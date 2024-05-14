package Osztalyok;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class tiz {

    private static int nmechs = 1; //Szerelő játékosok száma
    private static int nsabs = 0; //Szabotőr jáékosok száma
    private static int nturns = 10; //Körök száma
    private static Field f = new Field();

    @Before
    public void setUp() throws Exception {
        f.LoadMap("bin/Tests/tiz/Map.txt");
        f.setStartGame(nmechs,nsabs,nturns);
        f.CreatePlayers();
    }

    @Test
    public void liftUpPipe() throws IOException {
        f.Step(true, "DetachPipe 5,ThrowPipe,LiftUpPipe 5");
        assertEquals(true, f.PipeSearch(5).GetInHand());
        assertEquals(false, f.PipeSearch(5).GetAttached(f.PumpSearch(2)));
    }

}