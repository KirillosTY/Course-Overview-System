package courseoverviewsystem.cos;

import Controls.WorkHourCounter;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
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


    private WorkHourCounter count;



    @FXML
    public void initialize() {

        count = new WorkHourCounter();

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

                count.counter(countdown);

                text.setText("You have studied for "+count.getSeconds()
                + " seconds");


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






}







