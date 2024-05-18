package Osztalyok;

import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.*;

public class Masodik
{

    private static Field f = new Field();

    @Before
    public void setUp() throws Exception {
        f.LoadMap("bin/Tests/Masodik/Map.txt");
        f.CreatePlayers();
        f.PipeSearch(1).Ruin();
    }
    @Test
    public void WaterFromPumpToBrokenPipe() throws IOException {
        f.PumpSearch(3).Flow(f.PipeSearch(1));
        assertEquals(0,f.PumpSearch(4).GetAmount());
    }
}
