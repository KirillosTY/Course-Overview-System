package courseoverviewsystem.cos;

import Controls.MainController;
import Controls.Settings;
import Controls.WorkHourCounter;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class StudyStart {

    @FXML
    private Label WTLeft;

    @FXML
    private Label CT;

    @FXML
    private Label WTOverall;

    @FXML
    private Label BTOverall;

    @FXML
    private TextArea msg;

    @FXML
    private Label cycles;

    boolean working = true;



    @FXML
    private Button done;

    Settings settings;

    private WorkHourCounter countOverall;



    @FXML
    public void initialize() {
        settings = MainController.getInformationHandler().loadSettings();

        
        countOverall = new WorkHourCounter();

        WorkHourCounter count = new WorkHourCounter();
        count.setMinutes(settings.getStudyWorkM());
        count.setHours(settings.getStudyWorkH());
        count.setCycle(settings.getStudyCycle());


        startAnimation(count);


    }


    public void breakAnimation(){
        Stage s = (Stage) done.getScene().getWindow();


        s.close();

    }


    public void startAnimation(WorkHourCounter tempCount){

        new AnimationTimer() {

            LocalDateTime startTime = LocalDateTime.now().plusSeconds(tempCount.getFullSeconds());

            Long countdown = 0L;
            Long startCounter = 0L;

            @Override
            public void handle(long currentTime) {

                if (currentTime - startCounter >= 1_000_000_000) {
                    countdown++;
                    startCounter = currentTime;
                    updatetingStatus(tempCount,countdown);
                    clockUpdate(tempCount);
                    tempCount.setSeconds(tempCount.getSeconds() - 1);
                }


                if (LocalDateTime.now().isAfter(startTime)) {

                    swapModes(tempCount);

                    if (tempCount.getCycle() > 0) startAnimation(tempCount);

                    stop();

                }
            }


        }.start();

    }

    public void clockUpdate(WorkHourCounter tempCount){


        if(working){

            WTLeft.setText("You have " + tempCount.getSeconds()
                    + " seconds " + tempCount.getMinutes() + " minutes "
                    + tempCount.getHours() + " hours left of work");


        } else {

            WTLeft.setText("You have " + tempCount.getSeconds()
                    + " seconds " + tempCount.getMinutes() + " minutes "
                    + tempCount.getHours() + " hours left of break. Go get coffee!");
        }
    }

    public void swapModes(WorkHourCounter tempCount){


        if(working){

            tempCount.setCycle(tempCount.getCycle() - 1);

            tempCount.setMinutes(settings.getStudyBreakM());

            tempCount.setHours(settings.getStudyBreakH());

            working= false;

        } else {


            tempCount.setMinutes(settings.getStudyWorkM());

            tempCount.setHours(settings.getStudyWorkH());

            working = true;
        }


    }

    public void updatetingStatus(WorkHourCounter tempCount, Long countdown){

        CT.setText("Current time: "+LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm")));
        cycles.setText("Cycles left: "+tempCount.getCycle());

        if(working){
            countOverall.counter(countdown);
            WTOverall.setText("Overall worktime: "+countOverall.timeToString());
        }

    }




}







