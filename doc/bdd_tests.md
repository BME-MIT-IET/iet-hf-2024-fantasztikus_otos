# BDD tesztek készítése (Cucumber)

## Projekt Célkitűzése

A projekt célja az volt, hogy egy meglévő Java projektet kiegészítsünk BDD tesztekkel. A tesztek lehetőséget biztosítanak a már létező funkciók működésének a teszteléséhez, illetve amennyiben egy teszt nem jól fut le, úgy ki tudjuk javítani a kódot a megfelelő működés eléréséhez.

## Megoldás Menete

### Cucumber Könyvtár Integrálása

A Cucumber könyvtár használatának első lépése a Cucumber hozzáadása a projekt függőségeihez. A Cucumber BDD (Behavior-Driven Development) egy olyan eszköz, amely lehetővé teszi a fejlesztők és a nem technikai szereplők számára, hogy együttműködve írják meg a teszteket egy közös nyelven, például Gherkinben. Ezáltal a tesztelési forgatókönyvek könnyen érthetők és karbantarthatók minden érintett számára, segítve a szoftver minőségének javítását.

### A tesztesetek létrehozása

#### Feature: Breaking a Pump

As a player  
I want to break a pump  
So that I can see if the pump is ruined

Scenario: Break the pump successfully  
Given the map for break pump is loaded from "bin/palya1.txt"  
And the game is set up with 1 mechanic and 0 saboteurs for 10 turns  
When I step in the game with "BreakPump"  
And I ruin the pump  
Then the pump should be ruined

#### Feature: Game End

Scenario: The game should end after a certain number of turns  
Given the map for game end is loaded from "bin/Tests/harmincharom/Map.txt"  
And the game is started with 1 mechanic player and 1 saboteur player for 10 turns  
When the game runs for 10 turns  
Then the game should be ended

#### Feature: Testing Pipe Hole State Manipulation

Scenario: Setting a pipe to ruined state  
Given the game is set up  
When I set the pipe with ID 1 to ruined state  
Then the pipe with ID 1 should be in ruined state

#### Feature: Retool Pump Functionality

As a player  
I want to retool a pump  
So that I can connect it to a pipe

Scenario: Retooling a pump  
Given the map for retool pump is loaded from "bin/palya1.txt"  
And the game is started with 1 mechanic player and 0 saboteur players for 10 turns  
When pump 1 is retooled  
Then pipe 1 should be connected to pump 1

#### Feature: Making a Sticky Pipe

As a player  
I want to create a sticky pipe  
So that I can connect it to a pump

Scenario: Making a Sticky Pipe  
Given the map for making a sticky pipe is loaded from "bin/Tests/Huszonketto/Map.txt"  
And the players are created  
When Pipe 1 moves, Cistern moves, and Pump 2 is lifted up  
Then the pump should not be spawned  
When Pipe 1 moves again  
Then the pump should be spawned

#### Feature: Complex Test 1

Scenario: Complex test 1  
Given complex - the map for complex test is loaded from "bin/Tests/oszetett_2/Map.txt"  
And complex - the game is started with 3 mechanic players and 1 saboteur player for 10 turns  
When complex - player 1, 2, and 3 move to set up the pipeline  
And complex - player 1 moves the pipe to position 8  
And complex - player 1 moves the pump to position 4  
And complex - player 1 detaches the pipe from position 8  
And complex - a new pump is created  
And complex - player 2 lifts up the new pump  
And complex - one turn passes  
And complex - player 1 joins the pipe  
And complex - player 2 moves the pipe to position 8  
And complex - player 2 joins the pump at position 8  
Then complex - verify the game state

### StepDefinitions létrehozása

A StepDefinitions egy olyan része a Cucumber tesztelési keretrendszernek, amelyben definiáljuk a Gherkin nyelven írt tesztesetek lépéseinek megfelelő kódimplementációkat. Ezek a definíciók kapcsolják össze a teszteseteket a tesztelendő alkalmazás valós működésével, és végzik el az egyes lépések során szükséges műveleteket.

A létrehozott StepDefinitions fájlban minden Gherkin lépéshez (mint például a Given, When, Then) meg kell írnunk a hozzá tartozó kódimplementációt, amely lefut a teszt során. Például, ha a Gherkin lépés azt mondja, hogy "Given a map for break pump is loaded from 'bin/palya1.txt'", akkor a megfelelő kódimplementáció ebben a fájlban megkeresi és betölti a 'bin/palya1.txt' elérési útvonalon található térképet.

## Tesztelési Eredmények

A tesztek sikeresen lefutottak, és a funkciók megfelelően működtek. A létrehozott tesztesetek lefedték a projekt főbb funkcióit és biztosították azok helyes működését.

## Tanulságok és Jövőbeli Fejlesztési Lehetőségek

### Tanulságok

- **Könyvtár Integrálás:** A Cucumber könyvtár integrálása az elején nehézkesen sikerült több különböző ok miatt, viszont a későbbiekben tanulva ezekből a hibákből sokkal egyszerűbben és gyorsabban sikerülni fog majd.
- **BDD Előnyei:**  A feature fájl jellege miatt egy mindenki számára könnyen érthető és követhető tesztet lehet írni.

### Jövőbeli Fejlesztési Lehetőségek

- **További Tesztesetek:** További tesztesetek létrehozása a rendszer más funkcióinak lefedésére.
- **Fejlettebb Szenáriók:** Bonyolultabb és összetettebb tesztforgatókönyvek létrehozása, amelyek jobban modellezik a valós felhasználási eseteket.

## Összegzés

A projekt sikeresen demonstrálta a Cucumber BDD hatékonyságát a Java alkalmazások tesztelésében. A létrehozott tesztesetek és a BDD megközelítés használata lehetőséget biztosított a rendszer helyes működésének ellenőrzésére és a jövőbeli fejlesztések és optimalizációk célzott végrehajtására.
