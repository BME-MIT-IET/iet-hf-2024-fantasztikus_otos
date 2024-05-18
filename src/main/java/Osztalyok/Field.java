package Osztalyok;

import main.SystemMonitor;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Field
{

//Values
	private Cistern cistern = new Cistern(this);
	private int sabPoints;
	private int mecPoints;
	private int turns;
	List<Pump> pumps = new ArrayList<>();
	List<Pipe> pipes = new ArrayList<>();
	List<Team> teams = new ArrayList<>();
	private int mNum = 0;
	private int sNum = 0;
	private int currentRound = 0;
	public Boolean loadSuccess = true; //Betöltéshez kell
	private Random random = new Random();
//Actions

	/**
	 *
	 * @param i
	 * @return
	 */
	public Team GetTeams(int i){
		return teams.get(i);
	}

	/**
	 *
	 * @param amount
	 */
	public void WaterLost(int amount) {
		sabPoints += amount;
		System.out.printf("A szabotőrök pontjai %d ponttal növekedett.%n", sabPoints);
	}

	/**
	 *
	 * @param nmech
	 * @param nsabs
	 * @param nturns
	 */
	public void setStartGame(int nmech, int nsabs, int nturns){
		mNum = nmech;
		sNum = nsabs;
		turns = nturns;
	}

	/**
	 *
	 * @return
	 */
	public boolean mecWinning(){
		return sabPoints < mecPoints;
	}

	/**
	 *
	 * @param amount
	 */
	public void WaterArrived(int amount) {
		mecPoints += amount;
		System.out.printf("A szerelők pontjai %d ponttal növekedett.%n", mecPoints);
	}

	/**
	 *
	 */
	public void NextTurn() {
		for(int i = 0; i < 5; i++) {
			BreakPump(true);
			turns++;
			//Ha elérünk egy bizonyos körszámot, játék vége

			System.out.println("Next Turn Hard Usage:");
			systemMonitor.printSystemUsage();  // Print system usage when game starts

		}
	}

	/**
	 *
	 * @param randomIn
	 */
	public void BreakPump(boolean randomIn) {
		int selectedPump = random.nextInt(pumps.size());
		if(!pumps.get(selectedPump).ruined && !pumps.get(selectedPump).GetIsSource()){
			pumps.get(selectedPump).Ruin(pumps.get(selectedPump));
			System.out.printf("Pump %d pumpa elromlott%n", pumps.get(selectedPump).GetId());
			ImageIcon icon = new ImageIcon("bin/Skins/BrokenPump.png");
			pumps.get(selectedPump).pu.GetButton().setIcon(icon);
		}
	}

	/**
	 *
	 * @param b
	 * @param s
	 * @throws IOException
	 */
	public void Step(boolean b,String s) throws IOException {
		if(!cistern.GetPumpSpawned()){
			cistern.CreateNewPump();
			cistern.SetPumpSpawned(true);
		}
		Pipe pi = cistern.CreateNewPipe();

		BreakPump(true);
		teams.get(0).Turn(this, b, s);
		teams.get(1).Turn(this, b, s);
		WaterStart();
		for(Pump p: pumps){
			p.SetWater();
		}
		for(Pipe p: pipes){
			if(0< p.GetSticky()){
				p.SetSticky(p.GetSticky()-1);
			}
			if(0< p.GetSlippery()){
				p.SetSlippery(p.GetSlippery()-1);
			}
			if(0< p.GetHoleable()){
				p.SetHoleable(p.GetHoleable()-1);
			}
		}
		currentRound++;
	}

	/**
	 *
	 * @return
	 */
	public int GetCurrentRound()
	{
		return this.currentRound;
	}

	/**
	 *
	 * @param turns
	 */
	public void setTurns(int turns) {
		this.turns = turns;
	}

	/**
	 *
	 * @return
	 */
	public int EndGame()
	{
		if(mecPoints > sabPoints)
		{
			System.out.printf("Nyert : Mechanic csapat (%d pont)%n", mecPoints);
			return 1;
		}
		else if(mecPoints == sabPoints)
		{
			System.out.printf("Döntetlen (%d pont)%n", mecPoints);
			return -1;
		}
		else
		{
			System.out.printf("Nyert : Szabotőr csapat (%d pont)%n", sabPoints);
			return 0;
		}
	}

	/**
	 *
	 * @param point
	 */
	public void SetMechPoints(int point)
	{
		this.mecPoints = point;
	}

	/**
	 *
	 * @param point
	 */
	public void SetSabPoints(int point)
	{
		this.sabPoints= point;
	}

	/**
	 *
	 * @return
	 */
	public int GetMechPoints()
	{
		return mecPoints;
	}

	/**
	 *
	 * @return
	 */
	public int GetSabPoints()
	{
		return sabPoints;
	}

	/**
	 *
	 * @return
	 */
	public int Getpipemaxid(){
		return pipes.size();
	}

	/**
	 *
	 * @param p
	 */
	public void SetNewPipe(Pipe p){
		pipes.add(p);
	}

	/**
	 *
	 * @return
	 */
	public int Getpumpmaxid(){
		return pumps.size();
	}

	/**
	 *
	 * @param p
	 */
	public void SetNewPump(Pump p){
		pumps.add(p);
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	public Pipe GetPipe(int id){
		return pipes.get(id);
	}

	/**
	 *
	 * @return
	 */
	public int GetPipeSize(){
		return this.pipes.size();
	}

	/**
	 *
	 * @param pumpId
	 * @return
	 */
	public Pump PumpSearch(int pumpId){
		for(int i = 0; i < pumps.size(); i++) {
			if(pumps.get(i).GetId() == pumpId){
				return pumps.get(i);
			}
		}
		return null;
	}

	/**
	 *
	 * @param pipeId
	 * @return
	 */
	public Pipe PipeSearch(int pipeId){
		for(int i = 0; i < pipes.size(); i++) {
			if(pipes.get(i).GetId() == pipeId){
				return pipes.get(i);
			}
		}
		return null;
	}

	/**
	 *
	 * @param inFile
	 */
	public void LoadMap(String inFile)    //Pálya betöltése txt fájlból
	{


		System.out.println("#Pálya betöltésének megkezdése#");
		// Start measuring CPU and memory before the task
		systemMonitor.startMeasurements();

		for (int xy = 0; xy < 100; xy++) {

			try (BufferedReader br = new BufferedReader(new FileReader(inFile))) {


				System.out.println("-----File sikeresen megtalálva");
				String line;
				while ((line = br.readLine()) != null)                            //Ameddig nem üres a sor
				{
					//Játékosok betöltése

					if (line.startsWith("*PLAYERS*")) {
						System.out.println("----------Játékosok beállítása");
						line = br.readLine();
						String[] playerInfo = line.split(",");
						this.mNum = Integer.parseInt(playerInfo[0]);
						this.sNum = Integer.parseInt(playerInfo[1]);
						this.mecPoints = Integer.parseInt(playerInfo[2]);
						this.sabPoints = Integer.parseInt(playerInfo[3]);
						this.currentRound = Integer.parseInt(playerInfo[4]);
					}
					//Pumpák létrehozása
					else if (line.startsWith("*PUMP*")) {
						System.out.println("----------Pumpák beállítása");
						while (!(line = br.readLine()).isEmpty()) {                //Ameddig nem üres a sor
							String[] pumpInfo = line.split(",");

							//Értékek tárolása átmenetileg
							int pumpId = Integer.parseInt(pumpInfo[0]);
							int amount = Integer.parseInt(pumpInfo[1]);
							boolean source = pumpInfo[2].equals("1");
							boolean broken = pumpInfo[3].equals("1");
							boolean inHand = pumpInfo[4].equals("1");

							Pump pump = new Pump(pumpId, 0, 0, amount, cistern, this);
							pump.inHand = inHand;
							pump.ruined = broken;
							pump.SetIsSource(source);
							pumps.add(pump);

						}
					}
					//Csövek létrehozása
					else if (line.startsWith("*PIPE*")) {
						System.out.println("----------Csövek beállítása");
						while (!(line = br.readLine()).isEmpty()) {                //Ameddig nem üres a sor
							String[] pipeInfo = line.split(",");

							//Értékek tárolása átmenetileg
							int pipeId = Integer.parseInt(pipeInfo[0]);
							int capacity = Integer.parseInt(pipeInfo[1]);
							boolean isOnCistern = pipeInfo[2].equals("1");
							boolean busy = pipeInfo[3].equals("1");
							int hole = Integer.parseInt(pipeInfo[4]);
							int sticky = Integer.parseInt(pipeInfo[5]);
							int slippery = Integer.parseInt(pipeInfo[6]);

							Pipe pipe = new Pipe(pipeId, busy, capacity, cistern, this, sticky, slippery, hole);
							pipe.SetIsOnCistern(isOnCistern);
							if (isOnCistern) pipe.AddPump(cistern);
							pipes.add(pipe);
						}
					}
					//Pumpák beállítása
					else if (line.startsWith("*SET PUMP*")) {
						System.out.println("----------Pumpák bekötése csövekkel");
						while (!(line = br.readLine()).isEmpty()) {                //Ameddig nem üres a sor
							String[] pumpInfo = line.split(",");

							//Értékek tárolása átmenetileg
							int pumpId = Integer.parseInt(pumpInfo[0]);
							int inId = Integer.parseInt(pumpInfo[1]);
							int outId = Integer.parseInt(pumpInfo[2]);


							for (Pump p : pumps)//Kikeressük a pumpát id alapján
							{
								int index = 3;
								for (int i = 0; i < pumpInfo.length - 3; i++)//Sok cső lehet, így az összesen végigmegyünk
								{
									int pipeId = Integer.parseInt(pumpInfo[index++]);//Kell az Id
									for (Pipe pi : pipes)
										if (pi.GetId() == pipeId) p.AddPipe(pi);//Id alapján megkeressük
								}
								if (p.GetId() == pumpId)//Megtaláljuk
								{
									for (Pipe pi : pipes)//Hozzáadjuk a ki/be csöveket
									{
										if (pi.GetId() == inId)
											p.SetPipeIn(pi); //Itt a type honnan kell? Vagy konstans? -Do
										if (pi.GetId() == outId)
											p.SetJoinPipeOut(pi); //Itt a type honnan kell? Vagy konstans? -Do
									}


								}
							}
						}
					}

					//Csövek beállítása
					else if (line.startsWith("*SET PIPE*")) {
						System.out.println("----------Csövek bekötése pumpákba");
						while ((line = br.readLine()) != null && !line.isEmpty()) {
							String[] pipeInfo = line.split(",");

							//Értékek tárolása átmenetileg
							int pipeId = Integer.parseInt(pipeInfo[0]);
							int pump1 = Integer.parseInt(pipeInfo[1]);
							int pump2 = Integer.parseInt(pipeInfo[2]);
							int attachedItem = Integer.parseInt(pipeInfo[3]);
							boolean attachedBoolean = pipeInfo[4].equals("1");
							if (pump1 != 0 || pump2 != 0) {
								//Kikeressük a csövekhez a pumpákat
								for (Pipe pi : pipes) {

								if(pi.GetId() == pipeId) {
									for(Pump p : pumps) {
										if(p.GetId() == pump1) {
											pi.AddPump(p);
											pi.SetAttached(p,true);
										}

										if(p.GetId() == pump2){
											pi.AddPump(p);
											pi.SetAttached(p,true);
										}
									}
								}
							}
						}
						else
						{
							System.out.println("##A csövek nem maradhatnak bekötetlenül. A betöltés sikertelen!##");
							loadSuccess = false;
							return;
						}
					}
				}

					//Csövek beállítása
					else if (line.startsWith("*CISTERN*")) {
						System.out.println("----------Ciszternába menő csövek beállítása");
						while ((line = br.readLine()) != null && !line.isEmpty()) {
							String[] pipeInfo = line.split(",");

							for (int i = 0; i < pipeInfo.length; i++) {
								int currentId = Integer.parseInt(pipeInfo[i]);    //Értékek tárolása átmenetileg
								for (Pipe pi : pipes)
									if (pi.GetId() == currentId) {
										pi.AddPump(this.cistern);
										pi.SetAttached(this.cistern, true);//Cistern-be van kötve
										this.cistern.AddPipeIn(pi);
										pi.SetAttached(this.cistern, true);
									}

							}
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("I/O Hiba az olvasás közben. Bemeneti fájl : " + inFile);
			}
		}

		// After loading everything, print out the resource usage difference
		double cpuLoadDifference = systemMonitor.calculateCpuLoadDifference();
		long memoryUsageDifference = systemMonitor.calculateMemoryUsageDifference();
		System.out.printf("CPU Load Difference after loading map: %.2f%%\n", cpuLoadDifference);
		System.out.printf("Memory Usage Difference after loading map: %d MB\n", memoryUsageDifference);
	}

	/**
	 *
	 * @throws IOException
	 */
	public void WaterStart() throws IOException {
		for(Pump p : pumps){
			if(p.GetWater() > 0) {
				p.Flow(p.GetActiveOut());
			}
		}
	}
	
	public void CreatePlayers() {
		if (loadSuccess) {
			Pump startMec = selectRandomSourcePump();
			createTeamSab();
			createTeamMec(startMec);
			System.out.println("#Játék betöltése sikeresen megtörtént#");
		}
	}

	private void createTeamMec(Pump startMec) {
		Team mec = new Team();
		for(int i = 0; i < mNum; i++) {
			Mechanic m = new Mechanic(false, cistern, startMec);
			m.SetId(i+1);
			mec.addPlayer(m);
		}
		teams.add(mec);
	}
	private void createTeamSab() {
		Team sab = new Team();
		for(int i = 0; i < sNum; i++) {
			Saboteur s = new Saboteur(false, cistern, cistern);
			s.SetId(i+1);
			sab.addPlayer(s);
		}
		teams.add(sab);
	}


	private Pump selectRandomSourcePump() {
		Pump startMec = null;
		int numOfSource = 0;                                    //Megszámoljuk hány forrás van
		for(Pump p : pumps) if(p.GetIsSource()) numOfSource++;
		int sourceIndex = random.nextInt(0, numOfSource);

		int index = 0;
		for(Pump p : pumps)                                        //Minden pumpán végigmegyünk
		{
			if(p.GetIsSource())                                    //Ha forrás
			{
				if(index == sourceIndex)                        //Ha a random érték által megadott sorszámú
				{
					System.out.println("--------------------Start hely id: " + sourceIndex);
					startMec = p;                                //Kezdőállapot beállítása
					break;                                        //Csak opti miatt
				}
				index++;                                        //Hanyadik forrás ++
			}
		}
		return startMec;
	}

	/**
	 *
	 * @param i
	 * @return
	 */
	public Team GetTeam(int i){
		return teams.get(i);
	}

	public List<Pump> GetPumps(){
		return pumps;
	}

	public List<Pipe> GetPipes(){
		return pipes;
	}
	/**
	 *
	 * @return
	 */
	public Cistern GetCistern(){
		return cistern;
	}
}
