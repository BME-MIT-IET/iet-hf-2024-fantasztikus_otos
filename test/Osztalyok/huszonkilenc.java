package Osztalyok;

import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.*;

public class huszonkilenc
{
    private static int nmechs = 1; //Szerelő játékosok száma
    private static int nsabs = 1; //Szabotőr jáékosok száma
    private static int nturns = 10; //Körök száma
    private static Field f = new Field();
    //private static Pipe p = new Pipe(1, true, 5, f.GetCistern(), f);

    @Before
    public void SetUp() throws Exception
    {
        f.LoadMap("bin/Tests/27,28,29/palya27-29.txt");
        f.setStartGame(nmechs, nsabs, nturns);
        f.CreatePlayers();
    }
    @Test
    public void StartPoz() throws Exception
    {
        //A csapatot a field listájában lévő első csapat kezdi.

        boolean SabStart = false;

        if(f.teams.get(0).players.get(0).type().equals("Saboteur"))//Biztosan szabotőr objektum van-e itt
            SabStart = true;

        assertTrue(SabStart);
    }


}
