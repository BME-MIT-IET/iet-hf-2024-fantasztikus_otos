# OSHI Könyvtár Használata a Memória és CPU Használat Ellenőrzésére

## Projekt Célkitűzése

A projekt célja az volt, hogy egy meglévő Java projektet kiegészítsünk az OSHI (Operating System and Hardware Information) könyvtár használatával a memória és CPU használat monitorozásához. Ezen túlmenően, a `Field` osztály `OnLoad` funkcióját 100-szor futtattuk, hogy lemérjük a rendszer teljesítményét, mely alapot biztosít a jövőbeli optimalizálások sikerességének méréséhez.

## Megoldás Menete

### OSHI Könyvtár Integrálása – SystemMonitor.java

Az OSHI könyvtár használatának első lépése az OSHI hozzáadása a projekt függőségeihez. Az OSHI lehetőséget biztosít a rendszer hardver és operációs rendszer információinak elérésére, így ideális választás a memória és CPU használat monitorozásához.

### A Field Osztály Módosítása

A `Field` osztályban bevezettük a rendszer monitorozását a betöltési folyamat során, hogy nyomon követhessük a CPU és memória használat változásait a `LoadMap` metódus 100-szori futtatása alatt.

## Tesztelési Eredmények

A `LoadMap` metódus 100-szori futtatása után az alábbi eredményeket kaptuk:

- **CPU Terhelés Különbség:** Az OSHI könyvtár segítségével mértük a CPU terhelés különbséget, amely az egyes iterációk között jelentkezett. Az eredmények azt mutatták, hogy a CPU terhelés átlagosan 20-30% közötti ingadozást mutatott (rendszer terheltségétől függően).
- **Memória Használat Különbség:** A memória használat változása szintén jelentős volt, az iterációk során átlagosan 150-300 MB közötti növekedést tapasztaltunk.

## Tanulságok és Jövőbeli Fejlesztési Lehetőségek

### Tanulságok

- **Könyvtár Integrálás:** Az OSHI könyvtár integrálása könnyű és gyors megoldást biztosított a rendszer erőforrásainak monitorozására a dokumentáció tanulmányozása után.
- **Teljesítmény Mérés:** A rendszer teljesítményének mérése és az eredmények rögzítése lehetővé tette számunkra a jövőbeli optimalizációk sikerességének objektív mérését.
- **Iteratív Tesztelés:** Az iteratív tesztelés (100 futtatás) során pontosabb képet kaptunk a rendszer viselkedéséről különböző terhelési körülmények között. A jövőben a mért adatokat alapul használhatjuk az optimalizálás sikerességének mérésékor.

### Egyéb Fejlesztési Lehetőségek

- **További Implementálás:** A `SystemMonitor` osztály használata az `OnLoad` függvényen kívül, más funkciók és függvények mérésének érdekében.
- **Részletesebb Monitorozás:** Érdemes lehet részletesebb monitorozási adatokat gyűjteni, például a hálózati forgalom (miután a játék elérhető lesz online, kiegészített funkciókkal), diszk I/O, stb.
- **Optimalizációk:** Az összegyűjtött adatok alapján különböző optimalizációkat lehet végrehajtani a kódban, például a memória kezelésének javítása vagy a CPU terhelés csökkentése.

## Összegzés

A projekt sikeresen demonstrálta az OSHI könyvtár hatékonyságát a Java alkalmazások teljesítményének monitorozásában. A rendszer memória és CPU használatának részletes mérése lehetőséget biztosít a jövőbeli fejlesztések és optimalizációk célzott és hatékony végrehajtására.
