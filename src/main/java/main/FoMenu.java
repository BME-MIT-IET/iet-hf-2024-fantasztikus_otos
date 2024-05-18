package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import Osztalyok.*;

public class FoMenu {
	private static int nmechs = 2; //Szerelő játékosok száma
	private static int nsabs = 1; //Szabotőr jáékosok száma
	private static int nturns = 10; //Körök száma

	private static Field f = new Field();

	private static SystemMonitor systemMonitor = new SystemMonitor(); // System monitor

	/**
	 *
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException
	{
		new MenuUI(f);
		StartGame();
		/*while(true)
		{	//Menüpontok kiiratása
			System.out.printf("0 - Exit\n");
			System.out.printf("1 - Start Game\n");
			System.out.printf("2 - Rules\n");
			System.out.printf("3 - Settings\n");

			System.out.printf("-> ");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String menu = br.readLine();
			System.out.printf("\n");

			switch(menu)
			{
				case "0":
					System.exit(0);
					break;
				case "1":
					StartGame();
					break;
				case "2":
					Rules();
					break;
				case "3":
					Settings();
					break;
			}

			//menu = br.readLine();		//Üres olvasás, hogy ne lépjen ki azonnal a program
			//System.out.printf("\n\n");
		}*/

	}

	/**
	 *
	 */
	public static void Rules(){
		System.out.printf("A drukmákori sivatagon át bonyolult csőrendszer szállítja a vizet a hegyi forrásokból a sivatagon túl elterülő városok ciszternáiba. A csőrendszer egyszerű, elágazás nélküli csövekből és a csövekhez csatlakozó aktív elemekből (forrás, ciszterna, napelemmel működő vízátemelő pumpa stb.) áll. Egy pumpa több (de a pumpára jellemző véges számú) csövet is összeköthet, és minden pumpán külön-külön állítható, hogy éppen melyik belekötött csőből melyik másik csőbe pumpáljon, azonban egyszerre csak egy bemenete és egy kimenete lehet. A többi rákötött cső eközben el van zárva. A pumpák véletlen időközönként el tudnak romlani, ilyenkor megszűnik az adott pumpánál a vízáramlás. A pumpák mindegyike rendelkezik egy víztartállyal, amit a víz átemelése közben használ átmeneti tárolóként. A pumpa csak akkor tud vizet pumpálni egy csőbe, ha a cső szabad kapacitása ezt lehetővé teszi.\n" +
				"A csőhálózat bővíthető, változtatható. A csövek kellően rugalmasak ahhoz, hogy az egyik végüket lecsatlakoztatva egy másik aktív elemhez elvihetők és ott felcsatlakoztathatók legyenek. A ciszternáknál folyamatosan készülnek az új csövek, amelyek egyik vége a ciszternához kapcsolódik, a másik azonban szabad. A szabad végű csövekből a csőbe betáplált víz a homokba folyik.\n" +
				"\n" +
				"A csőhálózatot a szerelők tartják karban. Ők javítják meg az elromlott pumpákat, ők állítják át a pumpákat, hogy mindig a lehető legtöbb víz tudjon áthaladni a hálózaton, és ha egy cső kilyukad, az ő dolguk a cső megfoltozása is. A kilyukadt csövekből a víz kifolyik, a csövek végén lévő pumpához már nem jut belőle. A szerelők dolga a ciszternáknál lévő szabad csövekkel a hálózat kapacitásának növelése. A szerelők a ciszternáknál magukhoz tudnak venni új pumpát is, amit egy cső közepén tudnak elhelyezni. A csövet ehhez ketté kell vágni, és a két végét a pumpához kell csatlakoztatni.\n" +
				"\n" +
				"A hálózaton élnek a nomád szabotőrök is, akik a pumpákat tudják átállítani és a csöveket szokták kilyukasztani.\n" +
				"\n" +
				"Mivel a sivatag veszélyes hely, a szerelők és a szabotőrök csak a csőhálózaton haladhatnak. A pumpáknál kikerülhetik egymást, de a csöveken már nem tudnak elmenni egymás mellett, egy csövön egyszerre csak egy ember állhat.\n" +
				"\n" +
				"A játékot a két csapat legalább 2-2 játékossal játssza. A szabotőrök dolga, hogy minél több víz folyjon el a lyukakon, a szerelők pedig azon dolgoznak, hogy minél több víz jusson a ciszternákba. Az a csapat nyer, amelyik a játék végére több vizet szerez.\n");

	}

	/**
	 *
	 * @throws IOException
	 */
	public static void Settings() throws IOException {
		boolean back = true;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(back){
			int dif = 0;
			System.out.printf("Jelenleg: " + nmechs + " szerelő játékos van beállítva\n");
			System.out.printf("Jelenleg: " + nsabs + " szabotőr játékos van beállítva\n");
			System.out.printf("Jelenleg: " + nturns + " kör mennyiség van beállítva\n");
			String input = br.readLine();
			String[] inputsplit = input.split(" ");
			//Turn number
			//
			switch(inputsplit[0]){
				case "Increase":
					 dif = Integer.parseInt(inputsplit[2]);
					switch (inputsplit[1]){
						case "Mechanic":
							nmechs += dif;
							break;
						case "Saboteur":
							nsabs += dif;
							break;
						default :
							System.out.printf("Hibás bevitel\n");
					}
					break;
				case "Decrease":
					 dif = Integer.parseInt(inputsplit[2]);
					switch (inputsplit[1]){
						case "Mechanic":
							if(nmechs-dif > 0){nmechs -= dif;}
							else{System.out.printf("Túl kevés játékos maradna a pályán a szerelő csapatból.\n");}
							break;
						case "Saboteur":
							if(nsabs-dif > 0){nsabs -= dif;}
							else{System.out.printf("Túl kevés játékos maradna a pályán a szabotőr csapatból.\n");}
							break;
						default :
							System.out.printf("Hibás bevitel\n");
					}
					break;
				case "Back":
					back = false;
					break;
				case "Turn":
					dif = Integer.parseInt(inputsplit[1]);
					if(dif > 0 ){nturns = dif;}
					else{System.out.printf("Túl kevés kör maradna.\n");}
					break;
				default :
					System.out.printf("Hibás bevitel\n");
			}
		}
	}

	/**
	 *
	 * @throws IOException
	 */
	public static void StartGame() throws IOException {




		f.LoadMap("bin/palya1.txt");



	}

}

