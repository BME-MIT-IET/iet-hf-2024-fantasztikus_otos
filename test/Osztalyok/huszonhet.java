package Osztalyok;

import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class huszonhet
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
    public void PlayerBases() throws Exception
    {
        //Szabotőr kezdő pozi
        boolean isOnCist = false;
        if(f.teams.get(0).players.get(0).startPoz.GetId() == f.GetCistern().GetId())
            isOnCist = true;
        assertTrue(isOnCist);

        //Mechanic kezdő pozi
        List<Integer> intList = new ArrayList<>();
        int srcIndex = 0;
            for(Pump p : f.pumps)
                if(p.GetIsSource())
                    intList.add(p.GetId());    //Források Id-ja

           int currId = f.teams.get(1).players.get(0).startPoz.GetId(); //Helyzetének id-ja
           boolean isIn =  intList.contains(currId);
           assertTrue(isIn);//Forráson van a mechanic
    }


}
