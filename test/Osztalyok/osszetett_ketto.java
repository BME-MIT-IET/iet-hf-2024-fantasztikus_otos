package Osztalyok;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class osszetett_ketto {
    private static int nmechs = 3; //Szerelő játékosok száma
    private static int nsabs = 1; //Szabotőr jáékosok száma
    private static int nturns = 10; //Körök száma
    private static Field f = new Field();

    @Before
    public void setUp() throws Exception {
        f.LoadMap("bin/Tests/oszetett_2/Map.txt");
        f.setStartGame(nmechs,nsabs,nturns);
        f.CreatePlayers();
    }

    @Test
    public void osszetett2() throws IOException {
        f.GetTeams(1).GetPlayer(0).SetPipeLine(f.GetCistern());
        f.GetTeams(1).GetPlayer(1).SetPipeLine(f.GetCistern());
        f.GetTeams(1).GetPlayer(2).SetPipeLine(f.GetCistern());
        f.GetTeams(1).GetPlayer(0).PipeMove(f.PipeSearch(8));
        f.GetTeams(1).GetPlayer(0).PumpMove(f.PumpSearch(4));
        f.GetTeams(1).GetPlayer(0).DetachPipe(f.PipeSearch(8));
        f.GetCistern().CreateNewPump();
        int id = f.Getpumpmaxid()+1;
        Pump p = new Pump(id,0,0, 0, f.GetCistern(), f);
        f.SetNewPump(p);
        f.GetCistern().SetPumpSpawned(false);
        f.GetTeams(1).GetPlayer(1).LiftUpPump(p);
        f.Step(true,"");
        f.GetTeams(1).GetPlayer(0).JoinPipe();
        f.GetTeams(1).GetPlayer(1).PipeMove(f.PipeSearch(8));
        f.GetTeams(1).GetPlayer(1).JoinPump(f.PipeSearch(8), f.PumpSearch(id),f);

    }
}
