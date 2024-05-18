package Osztalyok;

public class Mechanic extends Player
{
//Constructor

	/**
	 *
	 * @param b
	 * @param cis
	 * @param startPoz
	 */
	public Mechanic(boolean b, Cistern cis, PipeLine startPoz){
		super(b,cis, startPoz);
	}

//Actions


	/**
	 *
	 * @return
	 */
	public String toString(){
		return "Mechanic";
	}

	/**
	 *
	 */
	public void CisternMove()
	{
		pipeline.StepOff(c);
	}


	public void Repair() {
		if (pipeline.ruined) {
			pipeline.Repair();
		}

	}

	/**
	 *
	 * @param pi
	 */
	public void LiftUpPipe(Pipe pi)
	{
		pi.SetInHand(true);
		System.out.println("Felemeljük a" + pi.ToString() + pi.GetId() +"csövet");
	}

	/**
	 *
	 * @param pu
	 */
	public void LiftUpPump(Pump pu)
	{
		pu.SetInHand(true);
	}

	/**
	 *
	 * @param pipe
	 * @param pump
	 * @param f
	 */
	public void JoinPump(Pipe pipe, Pump pump,Field f)
	{
		PipeLine pipeLine = pipe.GetPump(0);
		int id = 0;
		if(pipeline.GetPU().GetX()>pipe.GetPump(1).GetPU().GetX()||pipeline.GetPU().GetY()>pipe.GetPump(1).GetPU().GetY())
			id = 1;
		Pipe newPipe = new Pipe(f.Getpipemaxid(), false, 30, c, f,0,0,0);	//Létrehozunk egy új csövet
		f.SetNewPipe(newPipe);
		newPipe.AddPump(pump);	//A cső pumps listájába felvesszük az egyik pumpát, a type a megfelelőő kiiratásért felel, később nem lesz.
		newPipe.AddPump(pipe.GetPump(id));
		pipe.GetPump(id).AddPipe(newPipe);
		pipe.GetPump(id).RemovePipe(pipe);

		pipe.ChangePump(pipe.GetPump(id),pump);
		pipe.SetAttached(pump,true);

		pump.AddPipe(pipe);

		pump.AddPipe(newPipe);
		newPipe.SetAttached(pump,true);
		newPipe.SetAttached(pipeLine,true);//Hozzáadjuk a másik pumpát is

		pump.SetInHand(false);



	}

	@Override
	public void Slip() {

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
			case "RepairPipe":

				Repair();
				return true;

			case "CisternMove":
				//CisternMove();
				return false;

			case "RepairPump":
				Repair();
				return true;

			case "JoinPump":
				JoinPump(f.PipeSearch(Integer.parseInt(command[2])), f.PumpSearch(Integer.parseInt(command[4])),f);
				return true;

			case "LiftUpPipe":
				LiftUpPipe(f.PipeSearch(Integer.parseInt(command[1])));
				this.item = f.PipeSearch(Integer.parseInt(command[1]));
				return true;


			case "LiftUpPump":
				int id = f.Getpumpmaxid()+1;
				Pump p = new Pump(id,0, 0, 0, c, f);
				f.SetNewPump(p);
				 LiftUpPump(p);
				 c.SetPumpSpawned(false);
				this.item =p;
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
		return "Mechanic";
	}

}
