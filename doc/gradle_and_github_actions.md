# Github Actions "Java CI with Gradle" és "SonarCloud" ellenőrzők

## Célkitűzések

A projektfeladat egyik legelső lépéseként szerettük volna, ha a kódminőség biztosítását automatizálva, nem csak lokálisan, hanem github felületen is ellenőrizni tudnánk valamilyen CI-CD pipeline használatával. Ennek elérésére a projektben elkészített `build.gradle` file felhasználásával egy teljes fordítási folyamat sikerességét, valamint a SonarCloud statikus analízis és hibaellenőrző szolgáltatásainak eredményességét szerettük volna felhasználni.

## Megoldás menete

### Gradle build

**TODO: gradle összeállítása**

### Github ellenőrzők

A github ellenőrzők beállításai során úgy jártunk el, hogy azok minden `push` vagy `pull_request`-re a `main` vagy `dev` branchek esetén fussanak. Ez számunkra a projekt tekintetében elégséges volt, hiszen az ilyen ellenőrzők futtatása kellően erőforrásigényes, nekünk pedig ezen a kettő branchen volt a legfontosabb a lefutásuk, amennyiben egy éppen aktív munka alatt álló ágon történnek frissítések (pl.: `push` vagy `PR`), fölöslegesen terhelnék a rendszert.

További megfontolásunk volt, hogy a `dev` ágra csak az ellenőrzők sikeres lefutása, a `main`-re viszont a sikeres lefutáson túl még külön review is szükséges a tényleges mergeléshez. 

Az `build.gradle` megfelelő elkészítése után az oldal beépített szolgáltatásait igénybe véve a workflow file az oldal által legenerálódik, így itt ennek a tartalmának ellenőrzésén kívül több dolgunk nem igazán volt. Természetesen a file hitelességének ellenőrzése (például a workflow során `checkout`, `setup-java`), és a megfelelő JDK verzió kiválasztása a mi felelősségünk volt.

Másodsorban a SonarCloud ellenőrzőjét szerettük volna a projektbe integrálni, melyhez a szolgáltatás oldalán a gyakorlaton tanultaknak megfelelően tudtuk a projekt github repository-jához a szükséges analízis módszert rendelni. Esetünkben ez továbbra is gradle segítségével fordítás. Az oldal által generált secret-et, valamint a szükséges kódot (build.gradle extra konfigurációs értékek, workflow file) beállítottuk a projektben.

## Eredmények

A fordítási folyamat és github ellenőrzők sikeres beállítása után meggyőződhettünk róla, hogy minden egyes `main` vagy `dev` branchre történő közvetlen `push` vagy `PR` művelet esetén az ellenőrzők lefutnak, az esetlegesen felmerülő hibá(ka)t jelezve. Így biztosak lehetünk abban, hogy a két legfontosabb fejlesztési ágra csak sikeresen tesztelt és jóminőségű kódot töltünk fel.
