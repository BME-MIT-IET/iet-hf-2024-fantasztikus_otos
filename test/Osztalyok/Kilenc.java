package Osztalyok;

import org.hamcrest.core.AnyOf;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class Kilenc
{
    private static int nmechs = 1; //Szerelő játékosok száma
    private static int nsabs = 1; //Szabotőr jáékosok száma
    private static int nturns = 10; //Körök száma
    private static Field f = new Field();

    @Before
    public void setUp() throws Exception {
        f.LoadMap("bin/Tests/12,9/Map.txt");
        f.setStartGame(nmechs,nsabs,nturns);
        f.CreatePlayers();
    }
    @Test
    public void WaterFromPumpToPipeToPump() throws IOException {
        f.PumpSearch(3).Flow(f.PipeSearch(1));
        assertEquals(50,f.PumpSearch(4).GetAmount());
    }
}

