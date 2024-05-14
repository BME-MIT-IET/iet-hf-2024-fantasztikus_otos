package Osztalyok;

import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.*;

public class huszonhat
{
    private static int nmechs = 1; //Szerelő játékosok száma
    private static int nsabs = 1; //Szabotőr jáékosok száma
    private static int nturns = 1; //Körök száma
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
    public void WinningTeam() throws Exception
    {
        //Hol nézzük, hogy ki nyer?
        f.setTurns(nturns);
        f.SetMechPoints(30);
        int winner = -2;

        for(int i = 0; i < nturns; i++)
        {
            f.Step(true, "Skip");
            winner = f.EndGame();
        }

        assertEquals(1, winner);
    }


}
