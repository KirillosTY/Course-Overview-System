
package courseoverviewsystem.cos;



import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    public void initialize(){


        text.setText(checkTime() + LocalDate.now());

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
    public void viewChanger(Parent root, String windowName) throws Exception{


        Scene viewC = new Scene(root);

        Stage startStage = new Stage();

        startStage.setScene(viewC);

        startStage.setTitle(windowName);

        startStage.showAndWait();
        currentStage= startStage;

    }

    @FXML
    public void studyStart() throws Exception{
        if(currentStage!= null){
            currentStage.close();
        }

        viewChanger( FXMLLoader.load(getClass().getResource("studyStart.fxml")),
                "Workhour counter has started!");


    }

    @FXML
    public void studySet() throws Exception{

        viewChanger(FXMLLoader.load(getClass().getResource("studySettings.fxml")),
                "Study settings");
    }

    @FXML
    public void taskSet() throws Exception{

        viewChanger(FXMLLoader.load(getClass().getResource("taskSettings.fxml")),
                "Task settings");


    }
}
