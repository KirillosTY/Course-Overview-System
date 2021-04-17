
package courseoverviewsystem.cos;

import Controls.Course;
import Controls.CourseHandler;
import Controls.MainController;
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

    private CourseHandler courseHandler;

    @FXML
    private ComboBox<Course> courselist;

    @FXML
    public void initialize(){

        courseHandler = MainController.getCourseHandler();

        text.setText(checkTime() + LocalDate.now());


            courselist.getItems().addAll(courseHandler.getCourseList());


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



    public void addCourse(){

        courseHandler.createCourse();
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

            viewChanger(FXMLLoader.load(getClass().getResource("taskSettings.fxml")),
                    "Task settings");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
