package Osztalyok;

import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.*;

public class Harmadik
{
    private static int nmechs = 1; //Szerelő játékosok száma
    private static int nsabs = 0; //Szabotőr jáékosok száma
    private static int nturns = 10; //Körök száma
    private static Field f = new Field();
    private Pipe pi = new Pipe(1, true, 5, f.GetCistern(), f,0,0,0);

    @Before
    public void SetUp() throws Exception
    {
        f.LoadMap("bin/palya1.txt");
        f.setStartGame(nmechs,nsabs,nturns);
        f.CreatePlayers();
        pi.SetRuined(true);
    }

    @Test
    public void RepairPipe() throws IOException
    {

        assertTrue(pi.GetRuined());
        pi.Repair();
        assertFalse(pi.GetRuined());
    }
}
