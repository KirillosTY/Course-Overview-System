
package courseoverviewsystem.cos;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class UiMain {

    @FXML
    private Label text;


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
    public void studyStart() throws Exception{


        courseoverviewsystem.cos.StudyStart start = new courseoverviewsystem.cos.StudyStart();
        start.studyStart();

    }

    @FXML
    private void switchToSecondary() throws IOException {
       
    }

    @FXML
    public void taskSet() throws Exception{

        Parent task = FXMLLoader.load(getClass().getResource("taskSettings.fxml"));

        Scene taskS = new Scene(task);

        Stage taskW = new Stage();

        taskW.setScene(taskS);

        taskW.show();


    }
}
