package Osztalyok;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class het_nyolc {

    private static int nmechs = 1; //Szerelő játékosok száma
    private static int nsabs = 1; //Szabotőr jáékosok száma
    private static int nturns = 10; //Körök száma
    private static Field f = new Field();

    @Before
    public void setUp() throws Exception {
        f.LoadMap("bin/palya1.txt");
        f.setStartGame(nmechs,nsabs,nturns);
        f.CreatePlayers();
    }

    //7-es teszt
    @Test
    public void createNewPump() {
        assertEquals(false, f.GetCistern().GetPumpSpawned());
        if(!f.GetCistern().GetPumpSpawned()){
            f.GetCistern().CreateNewPump();
            assertEquals(true, f.GetCistern().GetPumpSpawned());
        }
    }

    //8-as teszt
    @Test(timeout = 1000)
    public void createNewPipe() {
        for(int i=0; i < nturns; i++){
            f.GetCistern().CreateNewPipe();
        }
    }
}