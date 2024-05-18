package Osztalyok;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Pipe extends PipeLine{
	//Egy csövet valósít meg.
	
//Values
	private int sticky;
	private int slippery;
	private int holeable;
	private boolean busy;
	private int capacity;
	private boolean isOnCistern;
	boolean inHand;
	private List<PipeLine> pumps = new ArrayList<PipeLine>();
	private boolean attachedFirst;
	private boolean attachedSecond;
	private Cistern cis;
	private Field field;

//Constructor

	/**
	 *
	 * @param id
	 * @param busy
	 * @param capacity
	 * @param c
	 * @param f
	 */
	public Pipe(int id, boolean busy, int capacity,Cistern c,Field f,int _sticky, int _slippery, int _holeable)
	{
		this.id = id;
		this.busy = busy;
		this.capacity = capacity;
		inHand = false;
		cis = c;
		field = f;
		sticky = _sticky;
		slippery = _slippery;
		holeable = _holeable;
		pu = new PipeLineUI("bin/Skins/cso.png");
	}

//Actions

	/**
	 *
	 * @param id
	 * @return
	 */
	public PipeLine GetPump(int id){
		return pumps.get(id);
	}

	/**
	 *
	 * @param detach
	 * @param attach
	 */
	public void ChangePump(PipeLine detach, PipeLine attach)
	{

		if(pumps.contains(detach)){
			int i = pumps.indexOf(detach);
			pumps.remove(detach);
			pumps.add(i,attach);
			SetAttached(attach,true);

			System.out.println("eltávolítottuk a "+ detach.GetId()+"pumpát és hozzáadtuk a " +attach.GetId()+"-t");
			if(attach == cis)
				isOnCistern = true;
		}
	}

	/**
	 *
	 * @return
	 */
	public boolean GetInHand(){
		return inHand;
	}

	/**
	 *
	 * @return
	 */
	public int FlowTrough()
	{
		return GetCapacity();
	}

	/**
	 *
	 * @param pu
	 */
	public void AddPump(PipeLine pu)
	{
		if(pumps.size() <= 2)
			pumps.add(pu);
	}

	/**
	 *
	 */
	public void Ruin()
	{	
		if(this.holeable == 0)
		{
			this.SetRuined(true);
			System.out.println("A játékos kilyukasztja a " + this.ToString() + this.GetId() + " csövet");
			if (sticky == 0 && slippery == 0) {
				ImageIcon icon = new ImageIcon("bin/Skins/lyukascso.png");
				this.pu.SetIcon(icon);
			} else if (sticky == 0 && slippery > 0) {
				ImageIcon icon = new ImageIcon("bin/Skins/BrokenSlipperyPipe.png");
				this.pu.SetIcon(icon);
			} else if (sticky > 0 && slippery == 0) {
				ImageIcon icon = new ImageIcon("bin/Skins/BrokenStickyPipe.png");
				this.pu.SetIcon(icon);
			}
			this.pu.resize(this.pu.GetWidth(), this.pu.GetHeight());;
		}
		else{
			System.out.println(this.ToString() + this.GetId() +" nem lyukasztható ki még " +this.holeable + " körig");
		}
	}

	/**
	 *
	 * @return
	 */
	public int GetHoleble()
	{
		return holeable;
	}

	/**
	 *
	 */
	public void Repair()
	{
		this.SetRuined(false);
		this.SetHoleable(3);
		System.out.println(holeable);
		System.out.println(this.ToString() + this.GetId() +" cső megjavítva");
		if (sticky == 0 && slippery == 0) {
			ImageIcon icon = new ImageIcon("bin/Skins/Repaired.png");
			this.pu.SetIcon(icon);
		} else if (sticky == 0 && slippery > 0) {
			ImageIcon icon = new ImageIcon("bin/Skins/HealedSlipperyPipe.png");
			this.pu.SetIcon(icon);
		} else if (sticky > 0 && slippery == 0) {
			ImageIcon icon = new ImageIcon("bin/Skins/HealedStickyPipePipe.png");
			this.pu.SetIcon(icon);
		}
		this.pu.resize(this.pu.GetWidth(), this.pu.GetHeight());
	}

	/**
	 *
	 * @return
	 */
	public int GetCapacity()
	{
		return capacity;
	}

	/**
	 *
	 * @param pu
	 */
	public void RemovePump(PipeLine pu)
	{
		SetAttached(pu,false);
	}

	/**
	 *
	 * @param pu
	 * @param b
	 */
	public void SetAttached(PipeLine pu, boolean b)
	{
		if(pumps.indexOf(pu) == 0){
			attachedFirst = b;
		}else if(pumps.indexOf(pu) == 1)
			attachedSecond = b;
	}

	/**
	 *
	 * @param pi
	 */
	public  void StepOff(PipeLine pi){
		if(IsNeighbour(pi) && (sticky == 0 || players.get(0).GetLastmove())) {
			SetBusy(false);
			pi.AddPlayerOn(players.get(0));
			players.remove(0);
			System.out.println("A "+pi.GetId()+"pumpa/cisternára léptünk");
		}
		else if(sticky > 0){
			System.out.println("A játékos nem léphet át, mert ragadós Pipe" + this.id +"-on áll.");
		}
	}

	/**
	 *
	 * @return
	 */
	@Override
	public String ToString(){
		return "Pipe";
	}

	/**
	 *
	 * @param pl
	 */
	public void StepOn(Player pl)
	{
		if(GetBusy()){
			System.out.println("Vannak a csövön");
		}
		if(!GetBusy() &&  GetAttached(pl.GetPipeline()) &&  !inHand) //
		{
			if (GetSlippery() == 0)
			{
				AddPlayerOn(pl);
				SetBusy(true);
				//pl.AddPipeLineItem(this);
			}
			else
			{
				PipeLine pu  = GetRandomNeighbour();
				pu.AddPlayerOn(pl);
				pl.AddPipeLineItem(pu);
			}
		}
	}

	/**
	 *
	 * @param water
	 * @param pu
	 * @throws IOException
	 */
	public void AcceptWater(int water,Pump pu) throws IOException {
		Pump pump = pu;
		pump.RemoveWater(water);
		if(!GetRuined() && GetBothAttached())
		{
			Pump pi = GetOtherNeighbour(pu);
			if(pi.GetId() == 0){ cis.AcceptWater(water,this); }
			else{
				pi.AcceptWater(water,pu);

			}

		}
		else
		{
			field.WaterLost(water);
		}

	}

	/**
	 *
	 * @return
	 */
	public int GetSlippery()
	{
		return slippery;
	}

	/**
	 *
	 * @param b
	 */
	public void SetBusy(boolean b)
	{
		busy = b;
	}

	/**
	 *
	 * @param i
	 */
	public void SetSticky(int i) {
		sticky = i;
		if (i > 0) {
			System.out.println("Pipe" + this.id + " ragadós lett.");
			if (ruined == false && holeable == 0) {
				ImageIcon icon = new ImageIcon("bin/Skins/Sticky.png");
				this.pu.SetIcon(icon);
			} else if (ruined == true && holeable == 0) {
				ImageIcon icon = new ImageIcon("bin/Skins/BrokenStickyPipe.png");
				this.pu.SetIcon(icon);
			} else if (ruined == false && holeable > 0) {
				ImageIcon icon = new ImageIcon("bin/Skins/HealedStickyPipe.png");
				this.pu.SetIcon(icon);
			}
			this.pu.resize(this.pu.GetWidth(), this.pu.GetHeight());
		} else {
			if (ruined == false && holeable == 0) {
				ImageIcon icon = new ImageIcon("bin/Skins/cso.png");
				this.pu.SetIcon(icon);
			}
			else if(ruined && holeable == 0) {
				ImageIcon icon = new ImageIcon("bin/Skins/lyukascso.png");
				this.pu.SetIcon(icon);
			} else if (ruined == false && holeable > 0) {
				ImageIcon icon = new ImageIcon("bin/Skins/Repaired.png");
				this.pu.SetIcon(icon);
			}

		}
		this.pu.resize(this.pu.GetWidth(), this.pu.GetHeight());
	}

	/**
	 *
	 * @return
	 */
	public int GetSticky()
	{
		return sticky;
	}

	@Override
	public void AddPipe(Pipe newPipe) {

	}

	@Override
	public void RemovePipe(Pipe rem) {

	}

	/**
	 *
	 * @param pu
	 * @param b
	 */
	public void SetOnPump(Pump pu, boolean b) {}

	/**
	 *
	 * @param i
	 */
	public void SetHoleable(int i)
	{
		holeable = i;
		if(i == 0){
			if (sticky == 0 && slippery == 0) {
				ImageIcon icon = new ImageIcon("bin/Skins/cso.png");
				this.pu.SetIcon(icon);
			} else if (sticky == 0 && slippery > 0) {
				ImageIcon icon = new ImageIcon("bin/Skins/Slippery.png");
				this.pu.SetIcon(icon);
			} else if (sticky > 0 && slippery == 0) {
				ImageIcon icon = new ImageIcon("bin/Skins/Sticky.png");
				this.pu.SetIcon(icon);
			}
		}
		this.pu.resize(this.pu.GetWidth(), this.pu.GetHeight());
	}

	/**
	 *
	 * @param i
	 */
	public void SetSlippery(int i) {
		slippery = i;
		if (i > 0) {
			System.out.println("Pipe" + this.id + " ragadós lett.");
			if (ruined == false && holeable == 0) {
				ImageIcon icon = new ImageIcon("bin/Skins/Slippery.png");
				this.pu.SetIcon(icon);
			} else if (ruined == true && holeable == 0) {
				ImageIcon icon = new ImageIcon("bin/Skins/BrokenSlipperyPipe.png");
				this.pu.SetIcon(icon);
			} else if (ruined == false && holeable > 0) {
				ImageIcon icon = new ImageIcon("bin/Skins/HealedSlipperyPipe.png");
				this.pu.SetIcon(icon);
			}

		} else {
			if (!ruined && holeable == 0) {
				ImageIcon icon = new ImageIcon("bin/Skins/cso.png");
				this.pu.SetIcon(icon);
			} else if (ruined && holeable == 0) {
				ImageIcon icon = new ImageIcon("bin/Skins/lyukascso.png");
				this.pu.SetIcon(icon);
			} else if (ruined == false && holeable > 0) {
				ImageIcon icon = new ImageIcon("bin/Skins/Repaired.png");
				this.pu.SetIcon(icon);
			}
		}
		this.pu.resize(this.pu.GetWidth(), this.pu.GetHeight());
	}

	/**
	 *
	 * @param b
	 */
	public void SetInHand(boolean b)
	{
		inHand = b;
	}

	/**
	 *
	 * @param b
	 */
	@Override
	public void SetRuined(boolean b){
		ruined = b;
	}

	/**
	 *
	 * @param id
	 */
	public void SetId(int id) {this.id = id;}

	/**
	 *
	 * @param p
	 * @return
	 */
	public boolean GetBusy(Pipe p) {return false;}

	/**
	 *
	 * @return
	 */
	public int GetId(){return this.id;}

	/**
	 *
	 * @return
	 */
	public boolean GetBusy()
	{
		return this.busy;
	}

	/**
	 *
	 * @param p
	 * @return
	 */
	public Pump GetOtherNeighbour(Pump p)
	{
		if(GetBothAttached()) {
			if(this.isOnCistern){
				Pump cistern = new Pump(0, 0, 0, 0, cis, f);
				return cistern;
			}
			else{
				return (Pump) pumps.get((pumps.indexOf(p)+1) % 2);
			}

		}
		return null;
	}

	/**
	 *
	 * @param type
	 * @return
	 */
	public boolean GetRuined(int type)
	{
		return ruined;
	}

	/**
	 *
	 * @return
	 */
	public int GetHoleable()
	{
		return this.holeable;
	}

	/**
	 *
	 * @return
	 */
	public int getSlippery()
	{
		return this.slippery;
	}

	/**
	 *
	 * @return
	 */
	public PipeLine GetRandomNeighbour()
	{
		Random rand = new Random();
		int i = rand.nextInt(2);
		return pumps.get(i);
	}

	/**
	 *
	 * @param pi
	 */
	@Override
	public void SetPipeOut(Pipe pi) {}

	/**
	 *
	 * @param type
	 * @return
	 */
	public int GetSticky(int type)
	{
		return this.sticky;
	}

	/**
	 *
	 * @param p
	 * @return
	 */
	public boolean GetAttached(PipeLine p)
	{
		if(pumps.indexOf(p) == 0){
			return attachedFirst;
		}else if(pumps.indexOf(p) == 1)
			return attachedSecond;
		else
			return false;
	}

	/**
	 *
	 * @return
	 */
	public boolean GetBothAttached()
	{
		return(attachedFirst && attachedSecond);
	}

	/**
	 *
	 * @param b
	 */
	public void SetIsOnCistern(boolean b){
		isOnCistern = b;
	}

	/**
	 *
	 * @return
	 */
	public boolean GetIsOnCistern(){
		return isOnCistern;
	}

	/**
	 *
	 * @param pi
	 * @return
	 */
	public boolean IsNeighbour(PipeLine pi){
		return pumps.contains(pi);
	}

}
