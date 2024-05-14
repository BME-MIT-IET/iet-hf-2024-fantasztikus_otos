package Osztalyok;

import java.util.Random;

public class Saboteur extends Player{
		//A szabotőrök tevékenységeit és mozgásukat kezeli.

//Constructor

	/**
	 *
	 * @param b
	 * @param cis
	 * @param startPoz
	 */
	public Saboteur(boolean b, Cistern cis, PipeLine startPoz){
		super(b,cis, startPoz);
	}
	
//Actions

	/**
	 *
	 * @return
	 */
	public String toString(){
		return "Saboteur";
	}

	/**
	 *
	 */
	@Override
	public void CisternMove() {
		System.out.println("Sabotőr nem tud cisternára lépni");
	}

	@Override
	public void Repair() {
	}

	/**
	 *
	 * @param pi
	 */
	@Override
	public void LiftUpPipe(Pipe pi) {
	}

	/**
	 *
	 * @param pu
	 */
	@Override
	public void LiftUpPump(Pump pu) {}

	/**
	 *
	 * @param pi1
	 * @param pu2
	 */
	@Override
	public void JoinPump(Pipe pi1, Pump pu2,Field f) {}


	public void Slip()
	{
		//generalunk egy random szamot, ameddig csuszossa fogjuk tenni a csovet
		Random r = new Random();
		int max = 4;
		//beallitjuk a cso csuszossagat
		pipeline.SetSlippery(r.nextInt(max) + 2);
		System.out.println(pipeline.ToString() + pipeline.GetId() + " csövet csúszóssá tettük " + r + " körig");
	}

	/**
	 *
	 * @param command
	 * @param f
	 * @return
	 */
	@Override
	public boolean Play(String[] command, Field f) {
		if(super.Play(command,f)){
			return true;
		}
		switch(command[0]){
			case "Slip":
			Slip();
			return true;
		}
		return false;
	}

	/**
	 *
	 * @return
	 */
	@Override
	public String type()
	{
		return "Saboteur";
	}
}
