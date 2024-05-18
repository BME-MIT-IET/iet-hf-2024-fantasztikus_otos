package Osztalyok;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class PipeLine {

	//A csöveket és a pumpákat vezérli.

//Values
	protected boolean ruined;
	protected List<Player> players = new ArrayList<Player>();
	public boolean inHand;
	protected int id;
	protected PipeLine detachedItem;

	protected Field f;

	protected PipeLineUI pu;

//Actions

	//el kell tárolni a csöveket és pumpákat
	//két függvény, ami visszaed egy-egy pumpa/pipe referenciát id alapján


	public PipeLineUI GetPU(){
		return pu;
	}
	public void SetInHand(boolean newValue) {
		inHand = newValue;
	}

	/**
	 *
	 * @param pl
	 */
	public void AddPlayerOn(Player pl) {
		players.add(pl);
		System.out.println(pl.type() + pl.GetId() + " játékos rálépett a " + this.ToString() + this.id + "-re");
		pl.AddPipeLineItem(this);
	}

	/**
	 *
	 * @return
	 * @throws IOException
	 */
	public boolean GetRuined() throws IOException {
		return ruined;
	}

	/**
	 *
	 * @return
	 */
	public PipeLine GetDetachedItem() {
		return detachedItem;
	}

	/**
	 *
	 * @param p
	 */
	public void SetDetachedItem(PipeLine p) {
		detachedItem = p;
	}

	/**
	 *
	 * @param id
	 */
	public void SetId(int id) {
		this.id = id;
	}

	/**
	 *
	 * @param pi
	 */
	public abstract void StepOff(PipeLine pi);

	/**
	 *
	 * @return
	 */
	public abstract int GetId();

	/**
	 *
	 * @param newValue
	 */
	public abstract void SetRuined(boolean newValue);

	/**
	 *
	 * @param water
	 * @param p
	 * @throws IOException
	 */
	public abstract void AcceptWater(int water, Pump p) throws IOException;

	/**
	 *
	 * @param detach
	 * @param attach
	 */
	public abstract void ChangePump(PipeLine detach, PipeLine attach);

	/**
	 *
	 * @param pu
	 */
	public abstract void AddPump(PipeLine pu);

	/**
	 *
	 */
	public abstract void Ruin();

	/**
	 *
	 * @param pu
	 */
	public abstract void RemovePump(PipeLine pu);

	/**
	 *
	 * @param pu
	 * @param b
	 */
	public abstract void SetAttached(PipeLine pu, boolean b);

	/**
	 *
	 * @param pl
	 */
	public abstract void StepOn(Player pl);

	/**
	 *
	 * @param p
	 * @return
	 */
	public abstract Pump GetOtherNeighbour(Pump p);

	/**
	 *
	 * @return
	 */
	public abstract PipeLine GetRandomNeighbour();

	/**
	 *
	 * @return
	 */
	public String ToString() {
		return "PipeLine";
	}

	/**
	 *
	 * @param i
	 */
	public abstract void SetSticky(int i);

	public abstract void SetSlippery(int i);

	/**
	 *
	 * @param pi
	 */
	public abstract void SetPipeOut(Pipe pi);

	public abstract void Repair();

	/**
	 *
	 * @return
	 */
	public abstract int GetSticky();

	public abstract void AddPipe(Pipe newPipe);
	public abstract  void RemovePipe(Pipe rem);
}