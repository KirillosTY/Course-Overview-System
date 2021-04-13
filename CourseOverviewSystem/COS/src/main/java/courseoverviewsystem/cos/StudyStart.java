package courseoverviewsystem.cos;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class StudyStart {

    @FXML
    private Label text;

    @FXML
    private Button done;

    @FXML
    private Stage startStage = new Stage();

    @FXML
    public void initialize() {

        new AnimationTimer() {


            String startTime = LocalDateTime.now().plusSeconds(90).format(
                    DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM));

            Long countdown = 0L;
            Long startCounter = 0L;

            @Override
            public void handle(long currentTime) {

                if(currentTime - startCounter >= 1_000_000_000 ){
                    countdown++;
                    startCounter = currentTime;
                }

                text.setText(counterToString(countdown)+"");


                if(LocalDateTime.now().format(
                        DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)).equals(startTime)){


                }

                if(LocalDateTime.now().format(
                        DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)).equals(startTime)){
                    stop();

                }



            }
        }.start();


    }


    public void breakAnimation(){
        Stage s = (Stage) done.getScene().getWindow();
        s.close();
    }


    public long counterToString(Long countD){

        String count = "You have studied for "+countD%60+" seconds";
        long seconds=countD%60;
        countD-=countD%60;
        countD/=60;

        count+=" "+countD%60+" minutes";
        long minutes=countD%60*100;
        countD-=countD%60;
        countD/=60;

        long hours=countD%60*10000;
        count+=" "+countD%60+" hours!";


        return seconds+minutes+hours;
    }


    public void studyStart() throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("studyStart.fxml"));

        Scene studyS = new Scene(root);

        startStage.setScene(studyS);
        startStage.titleProperty().setValue("Workhour counter has started!");

        startStage.showAndWait();


    }
}







