package Osztalyok;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class oszetett_egy {
    private static int nmechs = 1; //Szerelő játékosok száma
    private static int nsabs = 2; //Szabotőr jáékosok száma
    private static int nturns = 10; //Körök száma
    private static Field f = new Field();

    @Before
    public void setUp() throws Exception {
        f.LoadMap("bin/Tests/oszetett_1/Map.txt");
        f.setStartGame(nmechs,nsabs,nturns);
        f.CreatePlayers();
    }

    @Test
    public void osszetett1() throws IOException {
        f.GetTeams(0).GetPlayer(0).SetPipeLine(f.GetTeams(1).GetPlayer(0).GetPipeline());
        f.GetTeams(0).GetPlayer(1).SetPipeLine(f.GetTeams(1).GetPlayer(0).GetPipeline());
        f.GetTeams(0).GetPlayer(1).Retool(f.PipeSearch(3));
        f.GetTeams(0).GetPlayer(0).PipeMove(f.PipeSearch(3));
        f.GetTeams(0).GetPlayer(0).Hole();
        f.Step(true, "");
        f.GetTeams(0).GetPlayer(0).PumpMove(f.PumpSearch(2));
        f.GetTeams(1).GetPlayer(0).PipeMove(f.PipeSearch(3));
        f.GetTeams(1).GetPlayer(0).RepairPipe();
        f.Step(true, "");


    }
}
