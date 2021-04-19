
package courseoverviewsystem.cos;

import Controls.Course;
import Controls.CourseHandler;
import Controls.MainController;
import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalTime;

public class UiMain {

    @FXML
    private Label text;

    @FXML
    private Stage currentStage;

    @FXML
    private ComboBox<Course> courselist;

    private CourseHandler courseHandler;

    @FXML
    private ObservableList<Course> observableList;

    @FXML
    public void initialize(){

        courseHandler = MainController.getCourseHandler();

        text.setText(checkTime() + LocalDate.now());

        observableList = FXCollections.observableList(courseHandler.getCourseList());
        courselist.setItems(observableList);
        courselist.getSelectionModel().select(courseHandler.getCurrent());

        System.out.println(observableList);
    }

    private String checkTime(){

        String timeofDay ="Good morning, today is ";

        if(LocalTime.now().isAfter(LocalTime.of(12,00))) {
            timeofDay = "Good day, today is ";
        } else if(LocalTime.now().isAfter(LocalTime.of(16,00))){
            timeofDay = "Good afternoon, today is ";
        } else if(LocalTime.now().isAfter(LocalTime.of(12,00))){
            timeofDay = "Evening, today is ";
        }

        return timeofDay;

    }

    @FXML
    public void updateLists(){

        new AnimationTimer(){
            long startCounter;

            @Override
            public void handle(long l) {
                if (l - startCounter >= 10_000_000_000L) {

                   observableList.setAll(MainController.getCourseHandler().getCourseList());
                    courselist.setItems(observableList);
                    System.out.println(observableList + ""+courseHandler.getCourseList());
                    startCounter=l;
                }

            }
        }.start();

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
    public void studyStart(){

        try {
        viewChanger( FXMLLoader.load(getClass().getResource("studyStart.fxml")),
                "Workhour counter has started!");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void studySet() {
        if(currentStage != null){
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
    public void courseSet(){

        try {

            viewChanger(FXMLLoader.load(getClass().getResource("CreateCourse.fxml")),
                    "Create course!");

        } catch (Exception e){
            e.printStackTrace();
        }



    }
}
