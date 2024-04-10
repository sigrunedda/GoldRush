# Version 1.0

Verkefnið er endurbætt útgáfa af GoldRush í Viðmótsforritun vorið 2024. 
Leikur var uppáhalds verkefnið hjá öllum þar sem hann gefur marga möguleika á að bæta við auka virknum sem gera leikinn skemmtilegri og fjölbreytnari.
**Allar grunnkröfurnar eru þær sömu en þó nokkrar viðbætur:**
- Hægt er að velja mismunandi persónur sem uppfylla hlutverk grafarans.
- Óvinur sem endar leikinn ef grafarinn rekst á hann.
  
## **Maven uppsetning**
   ### Maven Compiler Plugin:
  Við notuðum Maven source 21, version 3.11.0. (Group ID: org.apache.maven.plugins)
  ### JavaFX Maven Plugin:
  Við notuðum version 0.0.8. (Group ID: org.openjfx) <br>
  [Sjá nánar í pom.xml](https://github.com/sigrunedda/GoldRush/blob/main/pom.xml)
  
## **Til þess að keyra forritið:** <br>
  Til þess að keyra forritið með Maven þá fer einstaklingur í Maven -> Plugins -> javafx -> javafx:run
  <br> annars er hægt að keyra það í gegnum GoldApplication og ýta á run current file. 
  <br> _GoldApplication er mainClass_
