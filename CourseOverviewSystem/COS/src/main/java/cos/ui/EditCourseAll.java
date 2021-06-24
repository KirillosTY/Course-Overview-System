package cos.ui;

import cos.controls.Course;
import cos.controls.CourseHandler;
import cos.controls.MainController;
import cos.controls.Task;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.Arrays;

public class EditCourseAll {

    private static CourseHandler cHandler;
    @FXML
    private ComboBox<String> courseListSelector;
    @FXML
    private ComboBox<Course> courseSelector;
    @FXML
    private ListView<Task> taskSelector;
    @FXML
    private Button editC, addC, editT, addT;
    @FXML
    private TextArea courseNotes, taskNotes;
    @FXML
    private String upcoming, current, past;

    @FXML
    private Label courseNotesLab, taskNotesLab;
    @FXML
    private Pagination courseNotesList;

    private ArrayList<Course> currentCourseList;



    @FXML
    public void initialize() {

        cHandler = MainController.getCourseHandler();

        upcoming = "Upcoming courses";
        past = "Past courses";
        current = "Current courses";

        setupControls();
        courseListSelector.setItems(FXCollections.observableList(
                new ArrayList<>(Arrays.asList(current, upcoming, past))));

        currentCourseList = cHandler.getCourseList();

        courseListSelector.getSelectionModel().selectFirst();
        courListUpdate();
        listUISets();

    }


    @FXML
    public void listUISets() {

        taskSelector.setCellFactory(new Callback<ListView<Task>, ListCell<Task>>() {
            @Override
            public ListCell<Task> call(ListView<Task> taskListView) {

                ListCells styled = new ListCells();
                styled.setTextFill(Paint.valueOf("#8D3016"));
                styled.setStyle("-fx-background-color: transparent (#8D3016); ");
                return styled;
            }
        });



    }

    @FXML
    public void courlistSeleUpdate() {
        cHandler = MainController.getCourseHandler();


        String chosenCourseList = courseListSelector.getSelectionModel().getSelectedItem();

        if (chosenCourseList.equals(upcoming)) {
            currentCourseList = cHandler.getUpcomingCourse();

        }
        if (chosenCourseList.equals(past)) {
            currentCourseList = cHandler.getPastCourse();

        }
        if (chosenCourseList.equals(current)) {
            currentCourseList = cHandler.getCourseList();
        }

        courListUpdate();

    }

    @FXML
    public void courListUpdate() {


        courseSelector.setItems(FXCollections.observableList(currentCourseList));

        if (!courseSelector.getItems().isEmpty()) {

            if (courseSelector.getSelectionModel().getSelectedItem() == null) {
                courseSelector.getSelectionModel().selectFirst();

            }
            cHandler.setCurrent(courseSelector.getSelectionModel().getSelectedItem());
            msgSets();
            taskListUpdater();

        } else {
            taskNotes.clear();
            courseNotes.clear();
            taskSelector.setItems(null);
        }


    }


    @FXML
    public void taskListUpdater() {
        ArrayList<Task> combinedArrays = new ArrayList();
        combinedArrays.addAll(cHandler.getCurrent().getTaskList());
        combinedArrays.addAll(cHandler.getCurrent().getDoneTasks());

        taskSelector.setItems(FXCollections.observableList(combinedArrays));

        if (!taskSelector.getItems().isEmpty()) {

            if (taskSelector.getSelectionModel().getSelectedItem() == null) {

                taskSelector.getSelectionModel().selectFirst();
                cHandler.setCurrentTask(taskSelector.getSelectionModel().getSelectedItem());
            }

            msgSetsTask();
        } else {
            taskNotes.clear();
            taskSelector.setItems(null);
        }

    }

    @FXML
    public void setupControls() {

        courseListSelector.setOnAction(updateList -> {
            courlistSeleUpdate();

        });

        courseSelector.getSelectionModel().selectedItemProperty().addListener((observableValue, aBoolean, course) -> {

            if (courseSelector.getSelectionModel().getSelectedItem() != null) {
                cHandler.setCurrent(course);
                courseNotesLab.setText("Course notes: "+course.getName());
                msgSets();
                taskListUpdater();
            } else {
                cHandler.setCurrent(null);
            }

        });

       setupTaskControls();

    }

    @FXML
    public void setupTaskControls(){

        taskSelector.getSelectionModel().selectedItemProperty().addListener((observableValue, aBoolean, task) -> {
            if (taskSelector.getSelectionModel().getSelectedItem() != null) {
                this.taskNotesLab.setText("Task notes: "+task.getName());
                cHandler.setCurrentTask(task);
                msgSetsTask();
            } else {
                cHandler.setCurrentTask(null);
            }
        });
    }

    @FXML
    public void msgSets() {

      courseNotes.setText(cHandler.getCurrent().buildNotes());

    }

    @FXML
    public void msgSetsTask() {
      taskNotes.setText(cHandler.getCurrentTask().buildNotes());
    }


    @FXML
    public void editTask() {
        createEditTask(false);
    }

    @FXML
    public void editCourse() {
        createEditCourse(false);
    }

    @FXML
    public void createTask() {
        createEditTask(true);
    }

    @FXML
    public void createCourse() {
        createEditCourse(true);

    }

    // add the stoppers for opening the windows unless the correct lists are in place...
    @FXML
    public void createEditCourse(boolean create) {
        closeAllExceptECA();
        try {
            Stage editStage = UiMainStart.viewChanger("CreateCourse.fxml", "Create course", true);

            if (editStage != null) {

                if (!create) {
                    if (cHandler.getCurrent() == null) {
                        return;
                    }
                    editStage.setTitle("Edit course");
                    editStage.setUserData(cHandler.getCurrent());
                }
                editStage.setOnHidden(updates -> {
                    courlistSeleUpdate();
                    courListUpdate();
                    MainController.getInformationHandler().saveCourseHandler(MainController.getCourseHandler());
                });
                editStage.showAndWait();
            }
        } catch (Exception ECAec) {
            UiMainStart.popupText("Creating and editing courses", "Something went wrong opening this the timer window, please restart the application");
        }
    }


    @FXML
    public void createEditTask(boolean create) {
        closeAllExceptECA();
        if (cHandler.getCurrent() == null) return;

        try {
            Stage editStage = UiMainStart.viewChanger("TaskEditCreation.fxml", "Create task", true);

            if (editStage != null) {
                if (!create) {
                    if (cHandler.getCurrentTask() == null) {
                        return;
                    }
                    editStage.setTitle("Edit task");
                    editStage.setUserData(cHandler.getCurrentTask());
                }
                editStage.setOnHidden(update -> {
                    taskListUpdater();
                    courListUpdate();
                });
                editStage.showAndWait();
            }
        } catch (Exception ECAec) {
            UiMainStart.popupText("Creating and editing tasks", "Something went wrong opening this the timer window, please restart the application");
        }
    }

    public void closeAllExceptECA() {


        Platform.runLater(() -> {

            for (Stage s : UiMainStart.loadNeeded) {
                s.close();
            }
        });

    }

    @FXML
    public void close() {

        Stage closer = (Stage) courseListSelector.getScene().getWindow();
        MainController.getInformationHandler().saveCourseHandler(cHandler);
        closer.close();
    }


}
