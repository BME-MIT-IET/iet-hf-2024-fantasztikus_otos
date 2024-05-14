package Osztalyok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.Timer;

public class Team
{
	//Megmondja, hogy melyik csapat játszik.

//Values
	private int time;
	List<Player> players = new ArrayList<>();//Ez kell
	private Player[] mec;//Ez se kell - Do
	private Player[] sab;//Ez nem kell -Do

	public Player GetPlayer(int i){
		return players.get(i);
	}

//Actions

	/**
	 *
 	 * @param f
	 * @param b
	 * @param s
	 * @throws IOException
	 */
	public void Turn(Field f, boolean b, String s) throws IOException {
		for(int i = 0; i < players.size(); i++) {
			System.out.printf("\n"+players.get(i).toString() + players.get(i).GetId() + " köre kezdetét vett\n");
			if(!b){
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String c = br.readLine();

				String[] command = c.split(" ");

				Timer timer = new Timer();
				time = 10;

				while(!players.get(i).Play(command,f) && time > 0){
					c = br.readLine();
					command = c.split(" ");
					//players.get(i).Play(command,f);

					timer.schedule(new TimerTask() {
						@Override
						public void run() {
							time -= 1;
						}
					}, 0, 1000);


				}
			}else{
				String[] v = s.split(",");
				for(int j=0; j < v.length; j++){
					String[] command = v[j].split(" ");
					players.get(i).Play(command,f);
				}
			}

		}
	}

	/**
	 *
	 * @param p
	 */
	public void addPlayer(Player p) {
		this.players.add(p);
	}

	/**
	 *
	 * @param i
	 * @return
	 */
	public Player GetPlayers(int i){
		return players.get(i);
	}

	public int GetSize(){
		return players.size();
	}
}
