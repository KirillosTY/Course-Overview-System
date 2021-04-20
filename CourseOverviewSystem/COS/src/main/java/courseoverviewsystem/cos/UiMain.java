package courseoverviewsystem.cos;

import Controls.Course;
import Controls.CourseHandler;
import Controls.MainController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
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
    private CourseHandler courseHandler;

    @FXML
    private ToggleGroup TaskButtons;

    @FXML
    private ToggleButton task1;

    @FXML
    private ToggleButton task2;

    @FXML
    private ToggleButton task3;

    @FXML
    private ToggleButton task4;

    @FXML
    public void initialize() {

        courseHandler = MainController.getCourseHandler();

        text.setText(checkTime() + LocalDate.now());

        TaskButtons.getToggles().get(0).isSelected();

        courselist.setPlaceholder(new Label("Add a course to start!"));


        courselist.setOnMouseClicked(e -> {
            updateLists();
        });

        courselist.setOnAction(taskUpdate -> {

            courseHandler.setCurrent(courselist.getSelectionModel().getSelectedItem());

            if (courseHandler.getCurrent() != null && courselist.getSelectionModel().getSelectedItem() != null) {
                updateTasks();
            }
        });

        courselist.setVisibleRowCount(5);
        courselist.setViewOrder(-2);
        updateLists();


    }


    private String checkTime() {

        String timeofDay = "Good morning, today is ";

        if (LocalTime.now().isAfter(LocalTime.of(12, 00))) {
            timeofDay = "Good day, today is ";
        } else if (LocalTime.now().isAfter(LocalTime.of(16, 00))) {
            timeofDay = "Good afternoon, today is ";
        } else if (LocalTime.now().isAfter(LocalTime.of(12, 00))) {
            timeofDay = "Evening, today is ";
        }

        return timeofDay;

    }

    @FXML
    public void updateTasks() {

        if (!courseHandler.getCurrent().getTaskList().isEmpty()) {
            task1.setText(courseHandler.getCurrent().getTaskList().get(0).toString());
        }
    }

    @FXML
    public void updateLists() {
        courseHandler = MainController.getCourseHandler();
        courselist.setItems(FXCollections.observableList(MainController.getCourseHandler().getCourseList()));

        if (courselist.getSelectionModel().getSelectedItem() != null) {
            courselist.getSelectionModel().selectFirst();
        }


    }

    @FXML
    public void viewChanger(Parent root, String windowName) {


        Scene viewC = new Scene(root);

        currentStage = new Stage();

        currentStage.setScene(viewC);

        currentStage.setTitle(windowName);

        currentStage.showAndWait();


    }

    @FXML
    public void studyStart() {

        try {
            viewChanger(FXMLLoader.load(getClass().getResource("studyStart.fxml")),
                    "Workhour counter has started!");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void studySet() {
        if (currentStage != null) {
            currentStage.close();
        }

        try {
            viewChanger(FXMLLoader.load(getClass().getResource("studySettings.fxml")),
                    "Study settings");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void taskSet() {

        try {

            viewChanger(FXMLLoader.load(getClass().getResource("TaskEditCreation.fxml")),
                    "Task settings");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void courseSet() {
        System.out.println(courseHandler.getCourseList());
        try {

            viewChanger(FXMLLoader.load(getClass().getResource("CreateCourse.fxml")),
                    "Create course!");


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
