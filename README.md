# Course Overview System

This application is supposed to help in managing courses, tasks, notes and projects in general.

# Manual

Linkistä COS.jar voit ladata sovelluksen. Sovelluksen voi käynnistää painamalla tai komennolla:
(Huom! tällä hetkellä sovellus odottaa, että käynnistys sijainnisssa on kansio nimeltä "CourseInfo", korjaan tämän pian)

[COS.jar](CourseOverviewSystem/COS/COS.jar)
```
java -jar COS.jar
``` 
Sekä menemällä COS kansioon ja ajamalla seuraavaa komento: 

```
mvn compile exec:java -Dexec.mainClass=cos.controls.MainController
```
```
mvn clean javafX:run
```
Manuaalissa on nyt tarkemmin neuvoa, suosittelen katsomaan.

# English version 
To try the application just download the file COS.jar and open it!

[COS.jar](CourseOverviewSystem/COS/COS.jar)

You can start the file with the command:
```
java -jar COS.jar
``` 
or by going into the main folder named COS and running the following commands:

```
mvn compile exec:java -Dexec.mainClass=cos.controls.MainController
```

```
mvn clean javafX:run
```

The manual contains instructions on how to use the application, I recommend reading before using.



## Documentation

[Manual](Documentation/Manual.md)

[Functional requirements](Documentation/Vaatimusmäärittely.md)

[Architecture](Documentation/Architecture.md)

[Workhours](Documentation/workhours.md)

[Sources](https://github.com/KirillosTY/Course-Overview-System/blob/31b5304e729e51ac6abdbc645ae2f2cb87f23967/Documentation/Sources.md)

## Releases

[viikko 5](https://github.com/KirillosTY/Course-Overview-System/releases/tag/viikko5)

## Command line tools

Start the program by using the following command:
```
java -jar COS.jar
```
** Note below this line everything is generated to "target" directory located in the run in location. It will be generated after first run.

Create a jar file by using the following command:
```
mvn package
```

Start testing by using the following command:
```
mvn test
```

Get a report of the tests using the following command:
```
mvn test jacoco:report
```
Run checkstyle with the following commnad:
```
mvn jxr:jxr checkstyle:checkstyle
``` 

Generate java documentation with the following command:
```
mvn javadoc:javadoc
``` 




### This project is changing it´s main language to english. Some parts may be in only finnish for now, but will be updated soon.
