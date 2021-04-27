package courseoverviewsystem.cos;

import controls.Course;
import controls.CourseHandler;
import controls.MainController;
import controls.Task;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

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


    private CourseHandler courseHandler;

    private ArrayList<Stage> stageControls;


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
                stageControls = (ArrayList<Stage>) text.getScene().getWindow().getUserData();
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
            for(Stage s: stageControls){
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


    @FXML
    public void viewChanger(String resource, String windowName) throws Exception {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));

            Parent root = loader.load();

            Scene viewC = new Scene(root);


            currentStage = new Stage();

            currentStage.setScene(viewC);

            currentStage.setTitle(windowName);

            stageControls.add(currentStage);

            mainStage = (Stage) text.getScene().getWindow();


        } finally {

        }

    }

    @FXML
    public void taskEdit() {

        if (courseHandler.getCurrentTask() != null) {
            try {
                viewChanger("TaskEditCreation.fxml",
                        "Edit current task");


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

        if (courseHandler.getCurrent() != null) {
            try {
                viewChanger("CreateCourse.fxml",
                        "Edit current Course");

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

        if (courseHandler.getCurrentTask() != null) {

            try {
                viewChanger("studyStart.fxml",
                        "Workhour counter has started!");


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

        try {
            viewChanger("studySettings.fxml",
                    "Study settings");

            currentStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void taskSet() {

        if (courseHandler.getCurrent() != null) {

            try {
                viewChanger("TaskEditCreation.fxml",
                        "Task Creation");

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
    public void courseSet() {

        try {
            viewChanger("CreateCourse.fxml",
                    "Create course!");
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
    public void PopupText() {

        try {
            viewChanger("PopupText.fxml",
                    MainController.getPopupText()[0]);

            currentStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    @FXML
    public void errorHandler() {

        System.out.println("Et ole valinnut tehtävää");

    }
}
