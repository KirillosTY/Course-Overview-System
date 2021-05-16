# How to run! 
To try the application just download the file COS.jar and open it!

[COS.jar](CourseOverviewSystem/COS/COS.jar)

You can start the file with the command:
```
java -jar COS.jar
``` 
or by going into the main folder named COS and running the following command:

```
mvn compile exec:java -Dexec.mainClass=cos.controls.MainController
```

# Starting up the first time!

The program will create necessary files on startup in the apps starting location: "courselist.bin" and "settings.bin". 
Most of the options will be unavailable until you add a course throught the "Add a course"-Button. 
### Important note: If you wish to choose another location where the files should be created to you will need create a config.properties to the run location and add the following lines:
``` 
CourseHandler=courselist.bin
Settings=settings.bin
```
By modifying each part after "="-symbol you may choose your location, just make sure it is a valid path. For example :
``` 
CourseHandler=/home/user/courselist.bin
Settings=/home/defnotmine/settings.bin
```
#### however this new config file will need to present at the run location.

![Basic ui](https://github.com/KirillosTY/Course-Overview-System/blob/70a100f37b0efd8ca2bef58922deba15c53bd424/Documentation/Kuvat/basic%20UI.png)

## Description of buttons, numbers correspond to the objects on the picture above.

1. This section manages the current course list. You may add, choose and mark them as done. This section does not include past or upcoming courses, see section 3 for more. Upcoming courses will automatically update to this section when time is due.

2. This section manages the current tasklist. You may add, choose and mark them as done. This section does include tasks done, see section 3 for more.

3. This button opens a window where you can view, edit, add or remove tasks and courses from all lists. See topic section  "Edit and Create courses/Tasks" for more details.


4. Button on the left named "Start studying" starts the timer on the current task. Button on the right, with the settings icon allows you to manage settings for this timer. see topic sections "Study settings" and "Study timer" for more detail.

5. This button saves the current state of all courses and tasks and closes all windows. To reduce possible errors, please us this when closing.


Below are detailed instructions on windows opening to the user(ex. add a course).

## 1-2 Create course/Task

Here is the basic course/task creation view. Important things to know currently: You should only enter 2 digit numbers to the time section(it currently does not display error, but will it will soon). You must also add a name and a description, else it will show the the field in red as an error. 

**Note: Courses will be automatically added to 3 different lists based on the start and end date. First, if the start date is in the future and end date has yet to pass, it will be put on the Upcoming courses list. Second, if the start date has passed and end date is in the future it will be  put on the current list. Third, if the end date is in the past it will be put on the Past courses list.**

![Create C/T](https://github.com/KirillosTY/Course-Overview-System/blob/70a100f37b0efd8ca2bef58922deba15c53bd424/Documentation/Kuvat/basicUIcreate.png)

## 3.Edit and Create courses/Tasks



![EditCreateAll](https://github.com/KirillosTY/Course-Overview-System/blob/70a100f37b0efd8ca2bef58922deba15c53bd424/Documentation/Kuvat/editCreateAll.png)

1. Here you can see which courselist you would like to have under inspection: Upcoming courses, current courses or past courses. 

2. This is a read only window, to edit or remove courses/Tasks use the Edit course/task buttons.



## 4. Study settings


Study settings allows you to make specific choises to what you wish to see in the studying timer. Pessing save sets the defaults for the timer.

![studyset](https://github.com/KirillosTY/Course-Overview-System/blob/70a100f37b0efd8ca2bef58922deba15c53bd424/Documentation/Kuvat/BasicUIStartsettings.png)

## 4.Study timer

Once open it will automatically start timer on the task, counting all of the seconds of work youve put in the task itself as well as in the course it is under in. On the left will be displayed the task name and information you have chosen to be displayed. Once the work timer runs out, it will automatically switch to the break timer, this action completes one cycle. The textarea will have all the notes youve currently written under the task. Pressing save will save the notes to the task and course it is related to. If closed from cancel or top right it will not save notes or work hours. 

![studyingtimer](https://github.com/KirillosTY/Course-Overview-System/blob/70a100f37b0efd8ca2bef58922deba15c53bd424/Documentation/Kuvat/BasicUIStart.png)



