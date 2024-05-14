package Osztalyok;

import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.*;

public class huszonot
{
    private static int nmechs = 0; //Szerelő játékosok száma
    private static int nsabs = 1; //Szabotőr jáékosok száma
    private static int nturns = 10; //Körök száma
    private static Field f = new Field();
    private static Pipe p = new Pipe(1, true, 5, f.GetCistern(), f);

    @Before
    public void SetUp() throws Exception
    {
        f.LoadMap("bin/Tests/25/palya25.txt");
        f.setStartGame(nmechs, nsabs, nturns);
        f.CreatePlayers();
    }
    @Test
    public void StepBackCistern() throws Exception
    {
        f.Step(true, "PipeMove 8"); //Ciszternáról lelépni
        f.Step(true, "CisternMove"); //Vissza a ciszternára -> nem tud
        boolean steppedOnCistern = false;

        if(f.teams.get(0).players.get(0).pipeline.equals(f.GetCistern()))
            steppedOnCistern = true;
        assertFalse(steppedOnCistern);
    }
}
