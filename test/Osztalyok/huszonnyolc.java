package Osztalyok;

import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.*;

public class huszonnyolc
{
    private static int nmechs = 1; //Szerelő játékosok száma
    private static int nsabs = 0; //Szabotőr jáékosok száma
    private static int nturns = 10; //Körök száma
    private static Field f = new Field();
    private static Pipe p = new Pipe(1, true, 5, f.GetCistern(), f);

    @Before
    public void SetUp() throws Exception
    {

    }
    @Test
    public void MapDraw() throws Exception
    {
        f.LoadMap("bin/Tests/27,28,29/test28.txt");
        f.setStartGame(nmechs, nsabs, nturns);
        f.CreatePlayers();
        assertFalse(f.loadSuccess);//Ha sikeres akkor True
    }


}
