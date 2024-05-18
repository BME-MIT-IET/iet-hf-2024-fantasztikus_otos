package Osztalyok;

import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.*;

public class Otodik
{
    private static int nmechs = 1; //Szerelő játékosok száma
    private static int nsabs = 0; //Szabotőr jáékosok száma
    private static int nturns = 10; //Körök száma
    private static Field f = new Field();
    private static Pump pu = new Pump(1, 1, 1, 1, f.GetCistern(), f);
    private static Pipe pi = new Pipe(1, true, 1, f.GetCistern(), f,0,0,0);

    @Before
    public void setUp() throws Exception {
        f.LoadMap("bin/palya1.txt");
        f.setStartGame(nmechs,nsabs,nturns);
        f.CreatePlayers();
    }

    @Test
    public void RetoolPump() throws IOException
    {
        f.Step(true, "Retool 1");
        pu.AddPipe(pi);
        pi.AddPump(pu);
        pu.SetPipeOut(pi);
        assertEquals(pi, pu.GetActiveOut());
    }
}
