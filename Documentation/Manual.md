# Manual

Linkistä COS.jar voit ladata sovelluksen. Sovelluksen voi käynnistää painamalla tai komennolla:

[COS.jar](https://github.com/KirillosTY/Course-Overview-System/raw/3c356b95f53e11128cf166d50db6a25c6d4c198c/CourseOverviewSystem/COS/COS.jar)
```
java -jar COS.jar
``` 
Sekä menemällä COS kansioon ja ajamalla seuraavaa komento: 

```
mvn compile exec:java -Dexec.mainClass=Controls.MainController
```
Tällä hetkellä vain "Start studying"-napista käynnistyvä laskuri toimii ja tiedoston luonti. 
 
# English version 
To try the application just download the file COS.jar and open it!

[COS.jar](https://github.com/KirillosTY/Course-Overview-System/raw/3c356b95f53e11128cf166d50db6a25c6d4c198c/CourseOverviewSystem/COS/COS.jar)

You can start the file with the command:
```
java -jar COS.jar
``` 
or by going into the main folder named COS and running the following command:

```
mvn compile exec:java -Dexec.mainClass=Controls.MainController
```

Currently the only working thing is the work counter, which starts to count the time youve put in. Start it by pressing the "Start studying"-button.





