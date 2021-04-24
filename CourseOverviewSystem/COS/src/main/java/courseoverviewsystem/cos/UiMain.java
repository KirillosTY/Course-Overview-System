package courseoverviewsystem.cos;

import Controls.Course;
import Controls.CourseHandler;
import Controls.MainController;
import Controls.Task;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    private ListView<Task> tasklist;

    @FXML
    private Button recentTask;

    @FXML
    private Button recentCourse;

    @FXML
    private TextArea generalNotes;

    private CourseHandler courseHandler;


    @FXML
    public void initialize() {

        courseHandler = MainController.getCourseHandler();

        text.setText(checkTime() + LocalDate.now());

        updateLists();

        defaultStart();

        actionSetups();

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

    public void actionSetups(){

        courselist.setOnMouseClicked(e -> {
            updateLists();
        });

        courselist.setOnAction(taskUpdate -> {

            courseHandler.setCurrent(courselist.getSelectionModel().getSelectedItem());

            if (courseHandler.getCurrent() != null && courselist.getSelectionModel().getSelectedItem() != null) {
                updateTasks();
            }
        });

        tasklist.setOnMouseClicked(currentTask -> {
            if(tasklist.getSelectionModel().getSelectedItem() != null) {

                courseHandler.setCurrentTask(tasklist.getSelectionModel().getSelectedItem());
            }

        });

    }

    public void defaultStart(){

        courselist.setVisibleRowCount(5);

        courselist.setViewOrder(-2);

        if(courseHandler.getCurrentTask() != null){
            recent();
        }
        generalNotes.setText(courseHandler.getNotesOverall());

        courselist.setPlaceholder(new Label("Add a course to start!"));
    }


    @FXML
    public void updateTasks() {

        tasklist.getItems().setAll(courseHandler.getCurrent().getTaskList());

        tasklist.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        if(courseHandler.getCurrentTask() == null){
            tasklist.setPlaceholder(new Label("Add tasks!"));
        } else if(courseHandler.getCurrent().getTaskList().contains(courseHandler.getCurrentTask())){
            tasklist.getSelectionModel().select(courseHandler.getCurrentTask());
        }else {
            tasklist.setPlaceholder(new Label("Add tasks!"));
        }
    }

    @FXML
    public void updateLists() {

        courselist.setItems(FXCollections.observableList(courseHandler.getCourseList()));

        if (courseHandler.getCurrent() != null) {
            courselist.getSelectionModel().select(courseHandler.getCurrent());
            updateTasks();

        } else {
            courselist.setPlaceholder(new Label("Add a course to start!"));
            tasklist.setPlaceholder(new Label("Add tasks!"));
        }

    }


    @FXML
    public void  recent(){

        recentTask.setOnAction(notesView -> {
            MainController.setPopupText(new String[]{
                    courseHandler.getCurrentTask().toString(),
                    courseHandler.getCurrentTask().getNotes()});
            PopupText();
        });

        recentCourse.setOnAction(notesView -> {
            MainController.setPopupText(new String[]{
                    courseHandler.getCurrent().toString(),
                    courseHandler.getCurrent().getNotes()});
            PopupText();
        });

        recentTask.setText(courseHandler.getCurrentTask().toString() + " notes");

        recentCourse.setText(courseHandler.getCurrent().toString()+ " notes");
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
        } finally {

        }

    }

    @FXML
    public void studyStart() {

        if(courseHandler.getCurrentTask() != null) {

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

        if(courseHandler.getCurrent() != null ) {

            try {
                viewChanger("TaskEditCreation.fxml",
                        "Task settings");

                currentStage.setOnHidden((e)->{

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
            currentStage.setOnHidden((e)->{

                updateLists();
                updateTasks();
            });

            currentStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @FXML
    public void PopupText(){

        try {
            viewChanger("PopupText.fxml",
                    MainController.getPopupText()[0]);

            currentStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @FXML
    public void errorHandler(){

        System.out.println("Et ole valinnut tehtävää");

    }
}
