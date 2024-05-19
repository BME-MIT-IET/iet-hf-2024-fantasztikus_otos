package Osztalyok;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Cistern extends PipeLine {
	
//Values
	private boolean pumpSpawned = false;
	private List<Pipe> pipes = new ArrayList<Pipe>();
	Field f;

	public Cistern(Field _f){
		pu = new PipeLineUI("bin/Skins/cistern.png");
		f = _f;
	}


//Actions

	/**
	 *
	 */
	public void CreateNewPump()
	{
			SetPumpSpawned(true);
			System.out.println("Létrejött egy új pumpa a ciszternában.");
	}

	/**
	 *
	 */
	public Pipe CreateNewPipe()
	{
			int id = f.Getpipemaxid()+1;
			Pipe pi = new Pipe(id,false, 50, this, f,0,0,0);	//Konstruktor: nem foglalt, 250 kapacitás, ciszternán van
			f.SetNewPipe(pi);
			pi.AddPump(this);
			pi.AddPump(this);
			pi.SetIsOnCistern(true);//Beállítjuk, hogy ciszternán van
			AddPipeIn(pi);			//Bemeneti csövet beállítjuk neki
		return pi;
	}

	/**
	 *
	 * @param pspawn
	 */
	public void SetPumpSpawned(boolean pspawn)
	{
		pumpSpawned = pspawn;
	}

	/**
	 *
	 * @return
	 */
	public boolean GetPumpSpawned(){
		return pumpSpawned;
	}

	/**
	 *
	 * @param water
	 * @param pi
	 * @throws IOException
	 */
	public void AcceptWater(int water, Pipe pi) throws IOException {
		//Eltávolítunk belőle water egységnyi vizet
		f.WaterArrived(water);
		System.out.println("Víz érkezett a ciszternába.");
	}

	/**
	 *
	 * @param p
	 */
	public void AddPipeIn(Pipe p)
	{
		pipes.add(p);
		System.out.println("Pipe"+p.GetId() + " a ciszternába csatlakoztatott cső lett");
	}

	/**
	 *
	 * @param pi
	 */
	@Override
	public void StepOff(PipeLine pi) {
		System.out.println("Cisternán áll");
	}

	/**
	 *
	 * @return
	 */
	@Override
	public int GetId() {
		return 0;
	}

	/**
	 *
	 * @param newValue 
	 */
	@Override
	public void SetRuined(boolean newValue) { /* This method does nothing, as the game logic doesn't let the Cistern have a ruined state. */ }

	/**
	 *
	 * @param water
	 * @param p
	 */
	@Override
	public void AcceptWater(int water, Pump p) { /* Cistern does not accept water directly from a pump. */ }


	@Override
	public void Repair() { /* Cistern does not require repairs. */ }

	/**
	 *
	 * @param detach
	 * @param attach
	 */
	@Override
	public void ChangePump(PipeLine detach, PipeLine attach) { /* Cistern does not support changing pumps. */ }

	/**
	 *
	 * @param pu
	 */
	@Override
	public void AddPump(PipeLine pu) { /* Cistern does not support adding pumps. */ }

	/**
	 *
	 */
	@Override
	public void Ruin() { /* Cistern cannot be ruined. */ }

	/**
	 *
	 * @param pu
	 */
	@Override
	public void RemovePump(PipeLine pu) { /* Cistern does not support removing pumps. */ }

	/**
	 *
	 * @param pu
	 * @param b
	 */
	@Override
	public void SetAttached(PipeLine pu, boolean b) { /* Cistern does not support setting attachment status. */ }

	/**
	 *
	 * @param pl
	 */
	@Override
	public void StepOn(Player pl) { /* Cistern does not handle player stepping on it. */ }

	/**
	 *
	 * @param p
	 * @return
	 */
	@Override
	public Pump GetOtherNeighbour(Pump p) {
		return null;
	}

	/**
	 *
	 * @return
	 */
	@Override
	public PipeLine GetRandomNeighbour() {
		return null;
	}

	/**
	 *
	 * @param i
	 */
	@Override
	public void SetSticky(int i) { /* Cistern does not support setting sticky status. */}

	@Override
	public void SetSlippery(int i) {
		/* Cistern does not support setting slippery status. */
	}

	/**
	 *
	 * @param pi
	 */
	@Override
	public void SetPipeOut(Pipe pi) { /* Cistern does not support setting output pipe. */}

	/**
	 *
	 * @return
	 */
	@Override
	public int GetSticky() {
		return 0;
	}

	@Override
	public void AddPipe(Pipe newPipe) {
		/* Cistern does not support adding pipes. */
	}

	@Override
	public void RemovePipe(Pipe rem) {
		/* Cistern does not support removing pipes. */
	}

}
