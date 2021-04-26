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

# Starting up the first time!

The program will create necessary files on startup. Most of the options will be unavailable until you add a course throught the "Add a course"-Button, on the top right side of the basic user interface.

![Basic ui](https://github.com/KirillosTY/Course-Overview-System/blob/f407fd2047e4aed1e5f124bfbf495c0413d34001/Documentation/Kuvat/basic%20UI.png)

## description of buttons, numbers correspond to the objects on the picture above.

1. Allows you to add a course. A course must have a name and a description. Each course will also have editable start and end dates(default 56 days and start date will be current date).
2. Course choser. Allows you to choose the course you want to work on (When creating a course for the first time it will be automatically chosen!).
3. Adds a task under the current course. A task must also have a name and description. Each task will have editable start and end dates (default 7 days, and start date will be current date). To choose the task click on it´s name underneath this button.
4. opens the window to the studying timer settings. it is needed to set preferred options on the studying timer, for ex. how many minutes of work and break do you want to have in your cycles, how many cycles do you wish to have, what counters would you like to see while etc. (Note: cycle represents the number of repeats the work and break timers start a new)
5. Opens the studying timer, with a window for notes and other information chosen to be seen. It will when pressing the "save"-button  it will stop and save the notes under the task and course.
6. Allows you to edit everything related to the course information, including notes from all of the task (note: you can not edit specific tasks through here. To edit tasks choose the course and see number 8 described below).
7. Marking courses as done. Pressing it will remove the course from the list and it to the past courses list.
8. Allows you to edit task information, including notes.
9. Marking tasks as done. Pressing it will remove the task from the list and storing in a donetasks list.
10. Displays current date.
11. Allows you to inspect all of the notes of the currently chosen task (note: no edit possible in this window, read only).
12. Allows you to inspect all of the notes of the currently chosen course (note: no edit possible in this window, read only).
13. General notes allows you to add just basic notes for yourself.
14. Saves what is currently in the textarea.
15. Redos text.
16. clears the textarea
17. Saves all the information and closes all open windows.

Below are detailed instructions on windows opening to the user(ex. add a course).

## Create course/Task

Here is the basic course/task creation view. Important things to know currently: You should only enter 2 digit numbers to the time section(it currently does not display error, but will it will soon). You must also add a name and a description, else it will show the the field in red as an error, as show in the picture below.

![Create C/T](https://github.com/KirillosTY/Course-Overview-System/blob/720f0122139abb98bf4fc25b16d143310d3f236a/Documentation/Kuvat/basicUIcreate.png)


## Study settings


Study settings allows you to make specific choises to what you wish to see in the studying timer. pressing save sets the defaults for the timer (note: motivational message field is currently unassigned and will not be shown in the timer).

![studyset](https://github.com/KirillosTY/Course-Overview-System/blob/720f0122139abb98bf4fc25b16d143310d3f236a/Documentation/Kuvat/BasicUIStartsettings.png)

## Study timer

Once open it will automatically start timer on the task, counting all of the seconds of work youve put in the task itself as well as in the course it is under in. On the left will be displayed the task name and information you have chosen to be displayed. Once the work timer runs out, it will automatically switch to the break timer, this action completes one cycle. The textarea will have all the notes youve currently written under the task. Pressing save will save the notes to the task and course it is related to. If closed from cancel or top right it will not save notes or work hours. 

![studyingtimer](https://github.com/KirillosTY/Course-Overview-System/blob/720f0122139abb98bf4fc25b16d143310d3f236a/Documentation/Kuvat/BasicUIStart.png)










