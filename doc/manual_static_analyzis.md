# SonarCloud beüzemelése, futtatása és jelzett hibák átnézése, manuális kód 

## Projekt Célkitűzése

A SonarCloud beüzemelésének célja az volt, hogy automatikusan ellenőrizze és javítsa a kódminőséget, növelje a biztonságot és a fejlesztési hatékonyságot, valamint biztosítsa a projekt megbízhatóságát és fenntarthatóságát.

## Megoldás Menete

### SonarCloud összekötése a GitHub tárhellyel

A SonarCloud felületén a tárhely kiválasztását követően létrehozhatjuk az összeköttetést, ezt úgy tudjuk megtenni, hogy Analysis Method-ot választunk, jelen esetben github-ot, ugyanis github felületén található a tárhelyünk. Első lépésként a generált SONAR_TOKEN-t illesztjük bele a programunkba, majd pedig a gradle opció alatt található kóddal kiegészítjük a build.gradle, majd pedig a build.yml fájljainkat (mi ezt egy új fájl létrehozásával végeztük el, sonar.yml néven megtalálható).

### SonarCloud analízis vizsgálata

Az összekötés után, lefutott a SonarCloud vizsgálat, melynek eredménye 400+ Maintainability hiba lett. A fontosabb hibákat átnéztük, és priorizáltuk. 

## Hibák javítása

A fontosabb figyelmeztetések kijavításra kerültek: 
1. Add a nested comment explaining why this method is empty, throw an UnsupportedOperationException or complete the implementation. - src/main/java/Osztalyok/Cistern.java
2. Save and re-use this "Random". - src/main/java/Osztalyok/Field.java
3. Define a constant instead of duplicating this literal " pont)" 3 times. && Delete some comments and unused programcode - src/main/java/Osztalyok/Field.java
4. Refactor this method to reduce its Cognitive Complexity from 19 to the 15 allowed. - src/main/java/Osztalyok/Field.java
5. Rename field "joinpipe" to prevent any misunderstanding/clash with field "joinPipe". - src/main/java/Osztalyok/GameUI.java
6. Add a default case to this switch. - src/main/java/Osztalyok/Mechanic.java

### Tanulságok a SonarCloud analízis eszközről

- **Kódminőség Javítása:** A SonarCloud segít azonosítani és kijavítani a kódminőségi problémákat, például a hibákat, kódsmell-eket és biztonsági réseket. Ez növeli a kód olvashatóságát és karbantarthatóságát.
- **Átláthatóság és Nyomon követhetőség:** A SonarCloud részletes riportokat készít a kód állapotáról, amelyek segítségével a csapat és a vezetőség átláthatja a kód minőségét és fejlődését, illetve nyomon követheti a javításokat és fejlesztéseket.
- **Biztonság Fokozása:** A SonarCloud képes azonosítani a potenciális biztonsági rést a kódban, ezzel hozzájárulva a projekt biztonságosabbá tételéhez.
- **Megbízhatóság Növelése:** A kódminőség folyamatos ellenőrzésével a SonarCloud hozzájárul a projekt megbízhatóságának növeléséhez, csökkentve a hibák és rendszerleállások kockázatát.

## Összegzés

A SonarCloud beüzemelése és futtatása sikeresen hozzájárult a kódminőség javításához, a fejlesztési hatékonyság növeléséhez. Az automatikus elemzés és a részletes riportok segítségével a projekt fenntarthatósága jelentősen javult.
