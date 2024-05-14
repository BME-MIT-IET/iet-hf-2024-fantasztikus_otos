package Osztalyok;

import java.util.Random;

public abstract class Player {
	//A játékosok tevékenységeit kezeli.

//Values
	//epp a jatekos kore van-e
	protected boolean active;

	//a jatekos kezeben levo palyaelem (ha nics semmi a kezeben akkor null
	protected PipeLine item;

	//az a palyaelem, amin eppen tartozkodik
	protected PipeLine pipeline;

	protected Cistern c;

	protected  PipeLine startPoz;

	protected int id = 0;

	private PlayerUI pu;

	boolean lastmove = false;

	boolean pickedupPipe = false;

	public PlayerUI GetPU(){
		return pu;
	}

//Constructor

	/**
	 *
	 * @param b
	 * @param cis
	 * @param startPoz
	 */
	public Player(boolean b, Cistern cis, PipeLine startPoz){
		active = b;
		item = null;
		pipeline = startPoz;
		c = cis;
		this.startPoz = startPoz;
		pu = new PlayerUI();

	}

//Actions

	/**
	 *
	 * @return
	 */
	public boolean GetpickedupPipe(){
		return pickedupPipe;
	}
	public void SetpickedupPipe(boolean _pickedupPipe){
		 pickedupPipe = _pickedupPipe;
	}

	public PipeLine GetPipeline(){
		return pipeline;
	}

	public boolean GetLastmove(){
		return lastmove;
	}

	public void SetLastmove(boolean _lastmove){
		lastmove = _lastmove;
	}

	/**
	 *
	 * @param p
	 */
	public void SetPipeLine(PipeLine p){
		pipeline = p;
	}

	/**
	 *
	 * @return
	 */
	public int GetId(){
		return id;
	}

	/**
	 *
	 * @param i
	 */
	public void SetId(int i){
		id = i;
		pu.SetIdandType(id, type());
	}

	/**
	 *
	 * @param p: a cso, amire lepni szeretnenk
	 */
	public void PipeMove(Pipe p)
	{
		//ralepunk a csore
		p.StepOn(this);
	}

	/**
	 *
	 * @param pu: a pumpa, amire lepni szeretnenk
	 */
	public void PumpMove(PipeLine pu)
	{
		pipeline.StepOff(pu);
	}

	/**
	 *
	 * @param pi: a cső, amire a pumpa kimenetet állitani szeretnénk
	 */
	public void Retool(Pipe pi)
	{
			//beallitjuk a kimeneti csovet
			pipeline.SetPipeOut(pi);
			//lekerjuk a cso masik vegen levo pumpat, majd ott beallitjuk a csovet, mint bementi cso

			System.out.println("beállitottuk a " + pipeline.toString() + pipeline.GetId() + " pumpa kimenetét " + pi +
					" csőre, és a " + pi.GetOtherNeighbour((Pump) pipeline).ToString() +  pi.GetOtherNeighbour((Pump) pipeline).GetId() +
					" pumpa bemeneti csövet pedig a " + pi.ToString() + pi.GetId() + " csőre");
	}

	/**
	 *
	 * @param b: az active uj erteke
	 */
	public void SetActive(boolean b) {
		active = b;
		System.out.println("beállitottuk az active értéket " + b +"-ra/re");
	}

	/**
	 *
	 */
	public void JoinPipe() {
		//atallitjuk, hogy a regi pumpa helyett az uj legyen eltarolva, amihez csatlakoztatjuk
		item.ChangePump(item.GetDetachedItem(), pipeline);
		item.SetInHand(false);
		System.out.println("hozzacsatlakoztattuk a " + item + " csovet a " + pipeline + "pumpahpz");
		SetItem(null);

	}

	/**
	 *
	 * @param pi
	 */
	public void DetachPipe(Pipe pi)
	{
		if (pi.IsNeighbour(pipeline) && item == null && !pi.GetInHand()) {

			pi.RemovePump(pipeline);
			pi.SetDetachedItem(pipeline);
			SetItem(pi);
			pi.SetInHand(true);
			System.out.println("Lecsatlakoztattuk a " + pi.ToString() + pi.GetId() + " csövet a " + pipeline.ToString() + pipeline.GetId() + "pumpáról");
		}
		else
			System.out.println("Nem tudtuk lecsatlakoztatni okok:van a kezünkben valami;nincs mellettünk a cső; más kezében van a cső");
	}

	/**
	 *
	 */
	public void ThrowPipe()
	{
		if(item != null) {
			item.SetInHand(false);
			item.ChangePump(item.GetDetachedItem(), pipeline);
			item.SetAttached(pipeline, false);
			System.out.println("eldobtuk " + item.ToString() + item.GetId() + " csövet");
			SetItem(null);
		}
	}

	/**
	 *
	 */
	public void Sticky()
	{

		//generalunk egy random szamot, ameddig ragadossa fogjuk tenni a csovet
		Random r = new Random();
		int max = 4;
		//beallitjuk a cso ragadossagat
		pipeline.SetSticky(r.nextInt(max) + 2);
		System.out.println(pipeline.ToString() + pipeline.GetId() + " csövet ragadossa tettük " + r + " körig");
	}

	/**
	 *
	 */
	public void Hole()
	{
		//Meghívja a Ruin függvényt a megkapott csőre, mely értékként magát kapja
		pipeline.Ruin();
	}

	/**
	 *
	 * @param newPipeLine: az uj palyaelem, amin a jatekos tartozkodik
	 */
	public void AddPipeLineItem(PipeLine newPipeLine)
	{
		pipeline = newPipeLine;
		System.out.println("Hozzáadtuk a " + newPipeLine.ToString() + newPipeLine.GetId() + " pályaelemet a játékoshoz");
	}

	/**
	 *
	 */
	public abstract void CisternMove();

	public abstract void Repair();

	/**
	 *
	 * @param pi
	 */
	public abstract void LiftUpPipe(Pipe pi);

	/**
	 *
	 * @param pu
	 */
	public abstract void LiftUpPump(Pump pu);

	/**
	 *
	 * @param pipe
	 * @param pump
	 * @param f
	 */
	public abstract void JoinPump(Pipe pipe,Pump pump, Field f);

	public abstract void Slip();

	/**
	 *
	 * @param p: a palyaelem, amit a kezebe vett
	 */
	public void SetItem(PipeLine p){
		item = p;
	}

	public PipeLine GetItem(){
		return item;
	}

	/**
	 *
	 * @param command
	 * @param f
	 * @return
	 */
	public boolean Play(String[] command, Field f){
		switch(command[0]){
			case "PipeMove":
				PipeMove(f.PipeSearch(Integer.parseInt(command[1])));
				return false;

			case "PumpMove":
				PumpMove(f.PumpSearch(Integer.parseInt(command[1])));
				return false;

			case "Retool":
				Retool(f.PipeSearch(Integer.parseInt(command[1])));
				return true;

			case "JoinPipe":
				JoinPipe();
				return true;

			case "DetachPipe":
				DetachPipe(f.PipeSearch(Integer.parseInt(command[1])));
				return true;

			case "ThrowPipe":
				ThrowPipe();
				return true;

			case "Sticky":
				Sticky();
				return true;

			case "Hole":
				Hole();
				return true;

			case "CisternMove":
				CisternMove();
				return false;

		}
		return false;
	}

	/**
	 *
	 * @return
	 */
	public String type()
	{
		return "Jatekos";
	}
}
