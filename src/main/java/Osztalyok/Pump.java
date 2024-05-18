package Osztalyok;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Pump extends PipeLine{
	//Egy pumpát valósít meg.
//Values
	private int activeIn;
	private int activeOut;
	private int amount;

	private int water;
	private boolean isSource;

	Cistern c;

	Field f;
	private List<Pipe> pipes = new ArrayList<Pipe>();

	
//Constructor

	/**
	 *
	 * @param id
	 * @param activeIn
	 * @param activeOut
	 * @param amount
	 * @param _c
	 * @param _f
	 */
	public Pump(int id, int activeIn, int activeOut, int amount, Cistern _c, Field _f)
	{
		this.id = id;
		this.activeIn = activeIn;
		this.activeOut = activeOut;
		this.water = amount;
		c=_c;
		f =_f;
		if(water > 0){pu = new PipeLineUI("bin/Skins/PumpWithWater.png");}
		else if(water == 0){pu = new PipeLineUI("bin/Skins/Pump.png");}
	}

//Actions

	/**
	 *
 	 * @return
	 */
	@Override
	public String ToString(){
		return "Pump";
	}

	/**
	 *
	 * @param b
	 */
	public void SetIsSource(boolean b){
		this.isSource = b;
	}

	/**
	 *
	 * @return
	 */
	public boolean GetIsSource(){
		return this.isSource;
	}

	/**
	 *
	 * @return
	 */
	public int GetAmount(){
		return this.amount;
	}

	/**
	 *
	 * @return
	 */
	public int GetWater(){
		return this.water;
	}

	/**
	 *
	 */
	public void SetWater(){
		water += amount;
		amount = 0;
		if(water > 0 && ruined == false) {
			ImageIcon icon = new ImageIcon("bin/Skins/PumpWithWater.png");
			this.pu.GetButton().setIcon(icon);
		}
		else if(water == 0 && !ruined){
			ImageIcon icon = new ImageIcon("bin/Skins/Pump.png");
			this.pu.GetButton().setIcon(icon);
		}
	}

	/**
	 *
	 * @return
	 */
	public Pipe GetActiveOut(){
		return pipes.get(activeOut);
	}

	public void Repair()
	{
		if(this.ruined){
			this.SetRuined(false);
			//akció vége
			System.out.println("A " + this + " megjavul.\n");
			if(water == 0) {
				ImageIcon icon = new ImageIcon("bin/Skins/Pump.png");
				this.pu.SetIcon(icon);
				this.pu.GetButton().setIcon(icon);
			}
			else if(water > 0){
				ImageIcon icon = new ImageIcon("bin/Skins/PumpWithWater.png");
				this.pu.SetIcon(icon);
				this.pu.GetButton().setIcon(icon);
			}
		}
		else{
			System.out.println("A " + this + " nem volt elromolva.\n");
		}

	}

	/**
	 *
	 * @param p
	 * @throws IOException
	 */
	public void Flow(Pipe p) throws IOException
	{
			int water = p.FlowTrough();
			p.AcceptWater(water, this);
			System.out.printf(water + " mennyiségű víz sikeresen átfolyt Pump" + this.id + " pumpából.\n" );
	}

	/**
	 *
	 * @param water
	 * @param pu
	 */
	public void AcceptWater(int water, Pump pu) {
		amount += water;
		System.out.printf("Pump" + this.id + " pumpába ");
	}

	/**
	 *
	 * @param water
	 */
	public void RemoveWater(int water)
	{
		if(this.isSource == false){
			amount -= water;
			System.out.println("Pump" + this.id +" pumpából " + water + " víz folyt ki." );
		}

	}

	/**
	 *
	 * @param p
	 */
	@Override
	public  void  AddPipe(Pipe p)
	{
		pipes.add(p);	//pipes listához hozzáadjuk a paraméterként kapott csövet

	}

	/**
	 *
	 * @param p
	 */
	public void Ruin(Pump p) 
	{
		//Egy pumpát elrontott állapotúra állít
		SetRuined(true);	//Logikai változó értékének megváltoztatása Set-el
	}

	/**
	 *
	 * @param p
	 */
	public void SetPipeOut(Pipe p)
	{
			this.activeOut = pipes.indexOf(p); 	//A kimenő pumpa indexét állítjuk be a pipes listából
			Pump pu = p.GetOtherNeighbour(this);
			if(pu != null) {
			//if(pu.GetId() == 0){ c.SetPipeOut(p); }
			//else{
			pu.SetPipeIn(p);
			//}
		}
	}

	/**
	 *
	 * @return
	 */
	@Override
	public int GetSticky() {
		return 0;
	}

	/**
	 *
	 * @param p
	 */
	public void SetJoinPipeOut(Pipe p)
	{
		this.activeOut = pipes.indexOf(p); 	//A kimenő pumpa indexét állítjuk be a pipes listából
	}

	/**
	 *
	 * @param p
	 */
	public void SetPipeIn(PipeLine p)
	{
			this.activeIn = pipes.indexOf(p);
	}

	/**
	 *
	 * @param pi
	 */
	@Override
	public void StepOff(PipeLine pi) {
		//System.out.println("Pumpán áll");
	}

	/**
	 *
	 * @return
	 */
	@Override
	public int GetId() {
		return id;
	}

	/**
	 *
	 * @param newValue
	 */
	@Override
	public void SetRuined(boolean newValue) {
		ruined = newValue;
	}

	/**
	 *
	 * @param detach
	 * @param attach
	 */
	@Override
	public void ChangePump(PipeLine detach, PipeLine attach) {}

	/**
	 *
	 * @param pu
	 */
	@Override
	public void AddPump(PipeLine pu) {}

	/**
	 *
	 */
	@Override
	public void Ruin() {}

	/**
	 *
	 * @param pu
	 */
	@Override
	public void RemovePump(PipeLine pu) {}

	/**
	 *
	 * @param pu
	 * @param b
	 */
	@Override
	public void SetAttached(PipeLine pu, boolean b) {}

	/**
	 *
	 * @param pl
	 */
	@Override
	public void StepOn(Player pl) {}

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
	public void SetSticky(int i) {}

	@Override
	public void SetSlippery(int i) {

	}
	@Override
	public void RemovePipe(Pipe rem){
		if(pipes.contains(rem))
		{
			int id = pipes.indexOf(rem);
			pipes.remove(id);
		}
	}
}
