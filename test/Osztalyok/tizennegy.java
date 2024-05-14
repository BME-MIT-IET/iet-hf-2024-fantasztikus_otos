package Osztalyok;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class tizennegy {

    private static int nmechs = 0; //Szerelő játékosok száma
    private static int nsabs = 1; //Szabotőr jáékosok száma
    private static int nturns = 10; //Körök száma
    private static Field f = new Field();

    @Before
    public void setUp() throws Exception {
        f.LoadMap("bin/palya1.txt");
        f.setStartGame(nmechs,nsabs,nturns);
        f.CreatePlayers();
    }

    @Test
    public void moveToBusyPipe() throws IOException {
        f.PipeSearch(8).SetBusy(true);
        f.Step(true, "PipeMove 8");
        assertEquals(f.GetCistern(), f.GetTeam(0).GetPlayers(0).GetPipeline());
    }
}
