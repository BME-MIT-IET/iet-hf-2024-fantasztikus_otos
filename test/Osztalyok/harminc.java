package Osztalyok;

import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.*;

public class harminc
{
    private static int nmechs = 1; //Szerelő játékosok száma
    private static int nsabs = 1; //Szabotőr jáékosok száma
    private static int nturns = 10; //Körök száma
    private static Field f = new Field();
    //private static Pipe p = new Pipe(1, true, 5, f.GetCistern(), f);

    @Before
    public void SetUp() throws Exception
    {
        f.LoadMap("bin/Tests/30,32/palya30.txt");
        f.setStartGame(nmechs, nsabs, nturns);
        f.CreatePlayers();
    }
    @Test
    public void StartPoz() throws Exception
    {
        //Szabotőr kezdd
        boolean SabStart = false;

        if(f.teams.get(0).players.get(0).type().equals("Saboteur"))//Biztosan szabotőr objektum van-e itt
            SabStart = true;

        assertTrue(SabStart);//Szabotőr van

        f.Step(true, "Skip");//Lemegy a kör

        //Mechanic jön
        boolean MecFollow = false;

        if(f.teams.get(1).players.get(0).type().equals("Mechanic"))//Biztosan szabotőr objektum van-e itt
            MecFollow = true;

        assertTrue(MecFollow);//Mechanic van
    }


}
