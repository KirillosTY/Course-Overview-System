### This is a practice project for a course.

# Course Overview System

This application is created to help in managing courses, tasks, notes and projects in general. Mainly by keeping track of deadlines, workhours put in and saving notes.

**Note: You will need to have java 11 installed and environment set for it to operate properly.** 



## Documentation

[Manual](Documentation/Manual.md)

[Functional requirements](Documentation/Vaatimusmäärittely.md)

[Architecture](Documentation/Architecture.md)

[Testing Document](https://github.com/KirillosTY/Course-Overview-System/blob/0c8529ae727e64a3c8188b67150c533cfe7a711c/Documentation/Testing%20Documents.md)

[Workhours](Documentation/workhours.md)

[Sources](https://github.com/KirillosTY/Course-Overview-System/blob/31b5304e729e51ac6abdbc645ae2f2cb87f23967/Documentation/Sources.md)

## Releases

[loppupalautus](https://github.com/KirillosTY/Course-Overview-System/releases/tag/%23loppupalautus)

## Command line tools

Start the program by using the following command:
```
java -jar COSWindows.jar
```
or 
```
java -jar COSLinux.jar
```
**Note: below this line everything is generated to a "target" directory located in the run location. It will be generated after first run.**

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
