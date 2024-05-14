package Osztalyok;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class tizenharom {

    private static int nmechs = 1; //Szerelő játékosok száma
    private static int nsabs = 0; //Szabotőr jáékosok száma
    private static int nturns = 10; //Körök száma
    private static Field f = new Field();

    @Before
    public void setUp() throws Exception {
        f.LoadMap("bin/Tests/tizenharom/Map.txt");
        f.setStartGame(nmechs,nsabs,nturns);
        f.CreatePlayers();
    }

    //13-as teszt
    @Test
    public void detachPipe() throws IOException {
        f.Step(true, "DetachPipe 5");
        assertEquals(true, f.PipeSearch(5).GetInHand());
    }

}
