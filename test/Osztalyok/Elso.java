package Osztalyok;

import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.*;

public class Elso
{
    private static int nmechs = 1; //Szerelő játékosok száma
    private static int nsabs = 0; //Szabotőr jáékosok száma
    private static int nturns = 10; //Körök száma
    private static Field f = new Field();
    //private static Pipe p = new Pipe(1, true, 5, f.GetCistern(), f);

    @Before
    public void SetUp() throws Exception
    {
        f.LoadMap("bin/palya1.txt");
        f.setStartGame(nmechs, nsabs, nturns);
        f.CreatePlayers();

    }

    @Test
    public void Hole() throws IOException
    {
        f.PipeSearch(1).SetRuined(true);

        f.PipeSearch(1).SetRuined(true);
        assertTrue( f.PipeSearch(1).GetRuined());
    }
}
