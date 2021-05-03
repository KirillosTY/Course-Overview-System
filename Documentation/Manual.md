# Manual

Linkistä COS.jar voit ladata sovelluksen. Sovelluksen voi käynnistää painamalla tai komennolla:

[COS.jar](https://github.com/KirillosTY/Course-Overview-System/blob/ce7a2367f21c8f6ca11ecd88dd96dbb9e89431ef/CourseOverviewSystem/COS/COS.jar)
```
java -jar COS.jar
``` 
Sekä menemällä COS kansioon ja ajamalla seuraavaa komento: 

```
mvn compile exec:java -Dexec.mainClass=cos.controls.MainController
```
Tällä hetkellä vain "Start studying"-napista käynnistyvä laskuri toimii ja tiedoston luonti. 
 
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

# Starting up the first time!

The program will create necessary files on startup, in to a folder named "CourseInfo", make sure folder exists in start location. Most of the options will be unavailable until you add a course throught the "Add a course"-Button, on the top right side of the basic user interface.

![Basic ui](https://github.com/KirillosTY/Course-Overview-System/blob/70a100f37b0efd8ca2bef58922deba15c53bd424/Documentation/Kuvat/basic%20UI.png)

## Description of buttons, numbers correspond to the objects on the picture above.



Below are detailed instructions on windows opening to the user(ex. add a course).

## Create course/Task

Here is the basic course/task creation view. Important things to know currently: You should only enter 2 digit numbers to the time section(it currently does not display error, but will it will soon). You must also add a name and a description, else it will show the the field in red as an error, as show in the picture below.

![Create C/T](https://github.com/KirillosTY/Course-Overview-System/blob/70a100f37b0efd8ca2bef58922deba15c53bd424/Documentation/Kuvat/basicUIcreate.png)


## Study settings


Study settings allows you to make specific choises to what you wish to see in the studying timer. pressing save sets the defaults for the timer.

![studyset](https://github.com/KirillosTY/Course-Overview-System/blob/70a100f37b0efd8ca2bef58922deba15c53bd424/Documentation/Kuvat/BasicUIStartsettings.png)

## Study timer

Once open it will automatically start timer on the task, counting all of the seconds of work youve put in the task itself as well as in the course it is under in. On the left will be displayed the task name and information you have chosen to be displayed. Once the work timer runs out, it will automatically switch to the break timer, this action completes one cycle. The textarea will have all the notes youve currently written under the task. Pressing save will save the notes to the task and course it is related to. If closed from cancel or top right it will not save notes or work hours. 

![studyingtimer](https://github.com/KirillosTY/Course-Overview-System/blob/70a100f37b0efd8ca2bef58922deba15c53bd424/Documentation/Kuvat/BasicUIStart.png)

## Edit and Create courses/Tasks




![EditCreateAll](https://github.com/KirillosTY/Course-Overview-System/blob/70a100f37b0efd8ca2bef58922deba15c53bd424/Documentation/Kuvat/editCreateAll.png)





