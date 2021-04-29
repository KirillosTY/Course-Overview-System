package cos.ui;

import cos.controls.Course;
import cos.controls.CourseHandler;
import cos.controls.MainController;
import cos.controls.Task;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalTime;

public class UiMain {

    @FXML
    protected ComboBox<Course> courselist;

    @FXML
    private Label text;

    @FXML
    private Stage currentStage;

    @FXML
    private Stage mainStage;

    @FXML
    private ListView<Task> tasklist;

    @FXML
    private Button recentTask;

    @FXML
    private Button recentCourse;

    @FXML
    private TextArea generalNotes;

    @FXML
    private Button gNSave;

    @FXML
    private Button gNClear;

    @FXML
    private Button gNRedo;

    @FXML
    private Button editTask;

    @FXML
    private CheckBox doneTask;

    @FXML
    private Button editCourse;

    @FXML
    private CheckBox doneCourse;

    @FXML
    private BorderPane mainBorder;


    private CourseHandler courseHandler;


    @FXML
    private boolean UPCcheck = false;

    @FXML
    public void initialize() {


        courseHandler = MainController.getCourseHandler();

        text.setText(checkTime() + LocalDate.now());

        courselist.setItems(FXCollections.observableList(courseHandler.getCourseList()));
        defaultStart();
        actionSetupsCourse();
        actionSetupTasks();
        updateLists();
        gNsetup();

        setStageWindows();

    }

    private String checkTime() {

        String timeofDay = "Good morning, today is ";

        if (LocalTime.now().isAfter(LocalTime.of(12, 0))) {
            timeofDay = "Good day, today is ";
        } else if (LocalTime.now().isAfter(LocalTime.of(16, 0))) {
            timeofDay = "Good afternoon, today is ";
        } else if (LocalTime.now().isAfter(LocalTime.of(12, 0))) {
            timeofDay = "Evening, today is ";
        }

        return timeofDay;

    }

    public void actionSetupsCourse() {

        courselist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Course>() {
            @Override
            public void changed(ObservableValue<? extends Course> observableValue, Course course, Course t1) {

                if (t1 != null) {
                    courseHandler.setCurrent(courselist.getSelectionModel().getSelectedItem());
                    updateTasks();
                    recent();
                }

            }
        });

        doneCourse.selectedProperty().addListener((observableValue, aBoolean, t1) -> {
            Course tempCourse = courseHandler.getCurrent();
            courseHandler.setCurrent(null);
            courseHandler.setCurrentTask(null);
            courseHandler.markCourseAsDone(tempCourse, false);
            tasklist.getItems().clear();
            updateLists();
            doneCourse.setSelected(false);

        });




    }

    public void actionSetupTasks(){

        tasklist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Task>() {
            @Override
            public void changed(ObservableValue<? extends Task> observableValue, Task task, Task t1) {
                if (tasklist.getSelectionModel().getSelectedItem() != null) {

                    courseHandler.setCurrentTask(tasklist.getSelectionModel().getSelectedItem());

                    recent();
                }
            }
        });

        doneTask.selectedProperty().addListener((observableValue, aBoolean, t1) -> {
            if(courseHandler.getCurrentTask() != null) {
                Task tempT = courseHandler.getCurrentTask();
                courseHandler.setCurrentTask(null);
                courseHandler.getCurrent().removeTask(tempT);
                updateLists();
                doneTask.setSelected(false);
            } else {
                doneTask.setSelected(false);
            }

        });

    }

    public void setStageWindows(){


        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                mainStage = (Stage) text.getScene().getWindow();
            }
        });

    }

    public void defaultStart() {
        courselist.setVisibleRowCount(5);

        courselist.setViewOrder(-2);

        tasklist.setMaxWidth(200);

        if (courseHandler.getCurrentTask() != null) {
            recent();
        }

        courselist.setPlaceholder(new Label("Add a course to start!"));
    }




    @FXML
    public void updateTasks() {
        tasklist.setItems(FXCollections.observableList(courseHandler.getCurrent().getTaskList()));

        tasklist.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        if (courseHandler.getCurrentTask() == null) {
            tasklist.setPlaceholder(new Label("Add tasks!"));
        } else if (courseHandler.getCurrent().getTaskList().contains(courseHandler.getCurrentTask())) {
            tasklist.getSelectionModel().select(courseHandler.getCurrentTask());
        } else {
            tasklist.setPlaceholder(new Label("Add tasks!"));
        }

    }

    @FXML
    public void updateLists() {

        courselist.setItems(FXCollections.observableList(MainController.getCourseHandler().getCourseList()));

        if (courseHandler.getCurrent() != null) {
            courselist.getSelectionModel().select(courseHandler.getCurrent());
            updateTasks();

        } else {
            courselist.setPlaceholder(new Label("Add a course to start!"));
            tasklist.setPlaceholder(new Label("Add tasks!"));
        }

    }

    @FXML
    public void recentTask(){

        recentTask.setOnAction(notesView -> {
            if(courseHandler.getCurrentTask() != null) {
                MainController.setPopupText(new String[]{
                        courseHandler.getCurrentTask().toString(),
                        courseHandler.getCurrentTask().getNotes()});
                PopupText();
            }
        });

        if (courseHandler.getCurrentTask() != null) {
            recentTask.setText(courseHandler.getCurrentTask().toString() + " notes");
        } else {
            recentTask.setText("Task notes(empty)");
        }

    }

    @FXML
    public void recentCourse(){

        recentCourse.setOnAction(notesView -> {
            if(courseHandler.getCurrent() != null) {
                MainController.setPopupText(new String[]{
                        courseHandler.getCurrent().toString(),
                        courseHandler.getCurrent().getNotes()});
                PopupText();

            }
        });

        if(courseHandler.getCurrent() != null) {
            recentCourse.setText(courseHandler.getCurrent().toString() + " notes");
        } else {
            recentCourse.setText("Course notes(empty)");
        }
    }


    @FXML
    public void recent() {

        recentCourse();
        recentTask();
    }

    @FXML
    public void gNsetup() {

        generalNotes.setText(courseHandler.getNotesOverall());


        gNSave.setOnAction(saveGN -> {
            courseHandler.setNotesOverall(generalNotes.getText());
        });

        gNRedo.setOnAction(redoGN -> {
            generalNotes.undo();
        });

        gNClear.setOnAction(clearGN -> {

            generalNotes.clear();

        });


    }

    @FXML
    public void mainIsDone(){
        if(currentStage != null){
            for(Stage s: UiMainStart.stageControls){
                s.close();
            }
        }

        mainStage = (Stage) text.getScene().getWindow();
        mainStage.close();

    }

    @FXML
    public void courseIsDone() {

        if (courseHandler.getCurrent() != null) {

            courseHandler.markCourseAsDone(courseHandler.getCurrent(),false);
            courseHandler.setCurrent(null);
            courseHandler.setCurrentTask(null);

        }
        updateLists();
        recent();

    }

    @FXML
    public void taskIsDone() {

        if (courseHandler.getCurrentTask() != null) {

            courseHandler.getCurrent().removeTask(courseHandler.getCurrentTask());
            courseHandler.setCurrentTask(null);


        }
        updateLists();
        updateTasks();
        recent();
    }


    public void closeAllExceptMain(){

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                UiMainStart.stageControls.forEach(s ->{
                    if(s != mainStage && s != currentStage){
                        s.close();
                    }
                });

            }
        });
    }

    public void disableMain(boolean usable){

        mainBorder.setDisable(usable);

    }


    @FXML
    public void taskEdit() {
        closeAllExceptMain();

        if (courseHandler.getCurrentTask() != null) {

            try {
               currentStage = UiMainStart.viewChanger("TaskEditCreation.fxml",
                        "Edit current task", false);


                currentStage.setUserData(courseHandler.getCurrentTask());
                currentStage.setOnHidden(updateRec ->{
                    recent();
                });

                currentStage.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

            errorHandler();
        }

    }

    @FXML
    public void courseEdit() {
        closeAllExceptMain();

        if (courseHandler.getCurrent() != null) {

            try {
                currentStage = UiMainStart.viewChanger("CreateCourse.fxml",
                        "Edit current Course",false);

                currentStage.setUserData(courseHandler.getCurrent());
                currentStage.setOnHidden(update ->{
                    updateLists();
                    recent();
                });
                currentStage.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

            errorHandler();
        }

    }

    @FXML
    public void studyStart() {
        closeAllExceptMain();

        if (courseHandler.getCurrentTask() != null) {

            try {
                currentStage =  UiMainStart.viewChanger("studyStart.fxml",
                        "Workhour counter has started!", false);


                currentStage.showAndWait();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            errorHandler();
        }

    }

    @FXML
    public void studySet() {
        closeAllExceptMain();

        try {
            currentStage = UiMainStart.viewChanger("studySettings.fxml",
                    "Study settings", false);

            currentStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void taskCreate() {
        closeAllExceptMain();

        if (courseHandler.getCurrent() != null) {

            try {
                currentStage = UiMainStart.viewChanger("TaskEditCreation.fxml",
                        "Task Creation", false);

                currentStage.setOnHidden((e) -> {

                    updateLists();
                    updateTasks();
                });

                currentStage.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            errorHandler();
        }
    }


    @FXML
    public void courseCreate() {
        closeAllExceptMain();

        try {
            currentStage = UiMainStart.viewChanger("CreateCourse.fxml",
                    "Course creation!",false);
            currentStage.setOnHidden((e) -> {

                updateLists();
                recent();

            });

            currentStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @FXML
    public void pastUpcomingCurrentEdit(){

        closeAllExceptMain();
        disableMain(true);
        try {

            currentStage = UiMainStart.viewChanger("EditCourseAll.fxml",
                    "Manage all courses through here!", false);

            currentStage.setOnHidden(e ->{
                    disableMain(false);
                System.out.println("käytiin");

        });
            currentStage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    @FXML
    public void PopupText() {
        closeAllExceptMain();

        try {
            currentStage =  UiMainStart.viewChanger("PopupText.fxml",
                    MainController.getPopupText()[0], false);

            currentStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    @FXML
    public void errorHandler() {
        closeAllExceptMain();

        System.out.println("Et ole valinnut tehtävää");

    }
}
