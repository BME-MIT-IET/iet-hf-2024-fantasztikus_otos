package Osztalyok;

import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.*;

public class Hatodik
{
    private static int nmechs = 1; //Szerelő játékosok száma
    private static int nsabs = 0; //Szabotőr jáékosok száma
    private static int nturns = 10; //Körök száma
    private static Field f = new Field();
    private static Pump pu = new Pump(1, 1, 1, 1, f.GetCistern(), f);

    @Before
    public void setUp() throws Exception {
        f.LoadMap("bin/palya1.txt");
        f.setStartGame(nmechs,nsabs,nturns);
        f.CreatePlayers();
    }

    @Test
    public void BreakPump() throws IOException
    {
        f.Step(true, "BreakPump");
        pu.Ruin(pu);
        assertEquals(true, pu.GetRuined());
    }
}
