# Architecture

The packaging is 3 layered as follows:

![packkakaakkge](https://github.com/KirillosTY/Course-Overview-System/blob/ae2dc247ba94b4fdc5c6fccf949489bcfee66a59/Documentation/Kuvat/mehss.png)

cos.ui contains all UI related JAVAFX, which pull FXML files from resources.

cos.controls contains all of the control and logic.

cos.informationprocessing contains all related to reading, writing or configuring data.



## User Interface

The UI has 2 main interface classes:

1. Main UI

2. Overview of all tasks and courses


and 4 smaller ones: 

1. create or Edit Course
2. create or Edit Task
3. Study Timer
4. Study Settings

Each of these is defined in their own class file and have an UI layout done in a separate FXML file. The Main UI stays open at all times and holds access to all other windows. Opening a window will show it up next to the Main UI. Only one window can be open alongside the Main UI, if another one is opened it will close the one already up. Opening the "Overview of all tasks and courses" window will disable the Main UI, until closed. If Main UI is closed all other windows close with it.

The interface has been separated from most of logics and all data it accesses is through the MainController class. Any updates to any list, task or course will be updated through the MainController.

## Logic and Controls

### Datastructure

![kuva](https://github.com/KirillosTY/Course-Overview-System/blob/6b6db77303968a59c16b1be127e098d9267351ab/Documentation/Kuvat/classConnections.png)

Classes MainController, CourseHandler, Course, BasicTask, Task, Settings and WorkHourCounter handle all the logic and controlling of data. **Note: Task class in itself is currently nothing more than an extension of BasicTask, but is left as it is to because it allows expanding more easily.**

MainController class is mainly an access point to the UI and for starting the application with the proper data. This class is also an access point to handling Information.

CourseHandler class handles everything related to courses and is the main access point for handling anything course, task, or workhourcounter related: adding, removing, updating dates and setting to the right lists, passing them along to the maincontroller etc...

Course class handles everything related to tasks.


![Classpackage](https://github.com/KirillosTY/Course-Overview-System/blob/f4bf9b8cbc197528eba51a5baf8f8ad824e2fe5e/Documentation/Kuvat/COS.jpg)


### Informationprocessing

All the information is written or read through the cos.informationprocessing package and the only class in it. Currently it is not in the standard DAO implementation.

InformationHandler class reads and writes any object through with handling some of the errors in itself. It also has defaults for course and settings creation, loading or writing. Variables SettingsURL and CourseHandlerURL define where the files are read from and written to, including the names of the files.

#### Files

The application creates 2 files on startup: courselist.bin and settings.bin. courselist.bin contains a coursehandler object which has all the courses and lists, settings.bin has study timer settings.

The information for both files are saved as binary object types using the Serializable class.

## Main functions

**Important note: No UI has direct access to the cos.controls package. If below there is for example an access to the  course class it happens with the following call:**
```
MainController.getCourseHandler().getCurrent().createTask(Task task)
```

**Also the method viewChanger is a static method from UIMainStart class, but is left out to make reading clearer**


### Creating courses/Tasks

![CreateTask/Course](https://github.com/KirillosTY/Course-Overview-System/blob/ae2dc247ba94b4fdc5c6fccf949489bcfee66a59/Documentation/Kuvat/Course%20Create.png)

When the user presses the "Add a course/task"-button the UIMain checks if there are any other open windows and closes them with the closeAllExceptMain() method, then calls a static method viewChanger to set the proper stage. 

In then initialization of this window it gets all proper data, sets the proper values on place. "TextField setups"  makes sure no unwanted characters enter the field. courseLoad() method gets the userdata of this scene which defines if the window goes to edit mode or creation mode, if the return is null then it is in creation mode. Then the window opens up to the user.

When the user has put all necessary inputs and presses the "save"-button the saveCourse() method starts and runs  method isAcceptable(), which checks if all inputs are proper, if not it sets the ones that arent to default and waits for the user to press save again. Then passes the input data to CourseHandler to create a new course. Inside the CourseHandler class it then decides on which courselist the new course is added to based on end date and start date. 

The creation window closes and the UIMain updates necessary lists. With task creation the steps are almost identical to but it also updates tasklists after exit.

### Study timer
![StudyTimer](https://github.com/KirillosTY/Course-Overview-System/blob/694128636e073eb435237641177449b16d723e3a/Documentation/Kuvat/studyStartFinished.png) 

When the user presses the "Start studying"-button the  UIMain checks if there are any other open windows and closes them with the closeAllExceptMain() method, then calls a static method viewChanger to set the proper stage. 


In the initialization of this window it loads the settings and a workHourCounter from the task which is currently worked on. The defaultStart() sets the proper values in place and startAnimation(whc) starts the timers.

After "done"-button is pressed it starts the breakAnimationMethod(), which runs the saveNotes() that save the notes to the task and the course it is under in. 
then the sets the overall count to the Tasks WorkHourCounter and saves the current CourseHandler while closing the window.

After exit UIMain updates the lists.

## Other actions

Most of the other windows follow this same pattern: Check what resources to use, implement them, save user input and update lists.
Almost every action updates the lists, mostly to prevent error, but also to allow for a better user experience.

## Improvement requiring aspects

### UI
Creating and editing tasks/courses are currently pretty much the same class and could be reduced to one class to avoid duplicate code. The UI also looks different on linux and needs to be modified a bit differently for it. Toolbar tips only apply to the UIMain class currently. There should also be more feedback to the user on what to do and how to operate. The FXML files are currently okay, but the cell factory could not be set from there and should most likely have an separate class to allow easier modification in the future, UIMain class also has the ListCells class in it. 


### Controls

Currently CourseHandler class handles the current task under work, but it would  make more sense for the course class to have it under control...This would solve a few problems. Error handling should be done in a better fashion to allow the user to possibly fix the problem that occurs...But most of it is currently hidden.

### DataStructure 

Currently all courses and task are saved into an single courselist.bin type of file, which involves risks if lost. This file will need to be broken into pieces to assure data is not lost due to simple errors... SQL Implementation could be much better for this. Changing the InformationHandler to an interface would also be much smarter and then saving the courses, tasks and settings in different classes. 





