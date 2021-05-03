# Course Overview System

This application is supposed to help in managing courses, tasks, notes and projects in general.

# Manual

Linkistä COS.jar voit ladata sovelluksen. Sovelluksen voi käynnistää painamalla tai komennolla:
(Huom! tällä hetkellä sovellus odottaa, että käynnistys sijainnisssa on kansio nimeltä "CourseInfo", korjaan tämän pian)

[COS.jar](Documentation/Manual.md)
```
java -jar COS.jar
``` 
Sekä menemällä COS kansioon ja ajamalla seuraavaa komento: 

```
mvn compile exec:java -Dexec.mainClass=cos.controls.MainController
```
Manuaalissa on nyt tarkemmin neuvoa, suosittelen katsomaan.

# English version 
To try the application just download the file COS.jar and open it!

[COS.jar](https://github.com/KirillosTY/Course-Overview-System/blob/ce7a2367f21c8f6ca11ecd88dd96dbb9e89431ef/CourseOverviewSystem/COS/COS.jar)

You can start the file with the command:
```
java -jar COS.jar
``` 
or by going into the main folder named COS and running the following command:

```
mvn compile exec:java -Dexec.mainClass=cos.controls.MainController
```

The manual contains instructions on how to use the application, I recommend reading before using.



## Documentation

[Manual](Documentation/Manual.md)

[Functional requirements](Documentation/Vaatimusmäärittely.md)

[Architecture](https://github.com/KirillosTY/Course-Overview-System/blob/05d7623690ac84c01097ee8f81aee742d673a6e2/Documentation/Architecture.md)

[Workhours](Documentation/workhours.md)

[Sources](https://github.com/KirillosTY/Course-Overview-System/blob/31b5304e729e51ac6abdbc645ae2f2cb87f23967/Documentation/Sources.md)

## Releases

[viikko 5](https://github.com/KirillosTY/Course-Overview-System/releases/tag/viikko5)

## Command line tools

Start the program by using the following command:
```
java -jar COS.jar
```

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




### This project is changing it´s main language to english. Some parts may be in only finnish for now, but will be updated soon.
