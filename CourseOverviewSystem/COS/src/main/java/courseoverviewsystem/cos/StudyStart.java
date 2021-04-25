package courseoverviewsystem.cos;

import Controls.MainController;
import Controls.Settings;
import Controls.WorkHourCounter;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class StudyStart {

    boolean working = true;
    private Settings settings;
    @FXML
    private Label WTLeft;
    @FXML
    private Label CT;
    @FXML
    private Label WTOverall;
    @FXML
    private Label currentTask;
    @FXML
    private TextArea msg;

    @FXML
    private Button cancel;

    @FXML
    private VBox overallVBox;

    @FXML
    private Label cycles;

    @FXML
    private Button done;

    private WorkHourCounter countOverall;

    private WorkHourCounter count;
    @FXML
    private AnimationTimer counter;

    private long startHours;

    @FXML
    private Stage stage;


    @FXML
    public void initialize() {
        settings = MainController.getInformationHandler().loadSettings();

        if(!settings.isStudyShowT()){
            overallVBox.getChildren().remove(CT);
        }
        if(!settings.isStudyShowWT()){
            overallVBox.getChildren().remove(WTOverall);
        }
        if(!settings.isStudyShowCycles()){
            overallVBox.getChildren().remove(cycles);
        }


        countOverall = MainController.getCourseHandler().getCurrentTask().getWorkHoursSpent();
        startHours = countOverall.getCurrentCount();
        this.count = new WorkHourCounter();
        defaultStart();
        startAnimation(count);

        counter.start();
    }

    public void defaultStart() {
        count.setMinutes(settings.getStudyWorkM());
        count.setHours(settings.getStudyWorkH());
        count.setCycle(settings.getStudyCycle());
        currentTask.setText("Current task: " + MainController.getCourseHandler().getCurrentTask());
        msg.setText("\n\n\n" + MainController.getCourseHandler().getCurrentTask().getNotes());
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                onCloseCounterStop();
            }
        });
    }

    @FXML
    public void onCloseCounterStop(){
        stage = (Stage) overallVBox.getScene().getWindow();
        stage.setOnHidden(counterStop -> {
            counter.stop();
        });

    }

    @FXML
    public void breakAnimation() {

        saveNotes();
        long courseOverall = MainController.getCourseHandler().getCurrent().getWorkHoursSpent().getCurrentCount();
        MainController.getCourseHandler().getCurrent().getWorkHoursSpent()
                .setCurrentCount(courseOverall + countOverall.getCurrentCount()-startHours);
        MainController.getInformationHandler().saveCourseHandler(MainController.getCourseHandler());
        stage.close();

    }

    public void cancel() {
        Stage s = (Stage) cancel.getScene().getWindow();
        countOverall.setCurrentCount(startHours);
        counter.stop();
        s.close();

    }

    public void saveNotes() {

        MainController.getCourseHandler().getCurrentTask().saveNotesWithStamp(msg.getText());
        MainController.getCourseHandler().getCurrent().saveNotesWithStamp(msg.getText(),
                MainController.getCourseHandler().getCurrentTask().toString());
    }


    public void startAnimation(WorkHourCounter tempCount) {

        counter = new AnimationTimer() {

            final LocalDateTime startTime = LocalDateTime.now().plusSeconds(tempCount.getFullSeconds());

            Long countdown = 0L;
            Long startCounter = 0L;

            @Override
            public void handle(long currentTime) {

                if (currentTime - startCounter >= 1_000_000_000) {
                    countdown++;
                    countOverall.setCurrentCount(countOverall.getCurrentCount() + 1);
                    startCounter = currentTime;
                    updatetingStatus(tempCount, countdown);
                    clockUpdate(tempCount);
                    tempCount.setSeconds(tempCount.getSeconds() - 1);
                }


                if (LocalDateTime.now().isAfter(startTime)) {

                    swapModes(tempCount);

                    if (tempCount.getCycle() > 0) startAnimation(tempCount);

                    stop();

                }
            }


        };



    }

    public void clockUpdate(WorkHourCounter tempCount) {


        if (working) {

            WTLeft.setText("You have " + tempCount.getSeconds()
                    + " seconds " + tempCount.getMinutes() + " minutes "
                    + tempCount.getHours() + " hours left of work");


        } else {

            WTLeft.setText("You have " + tempCount.getSeconds()
                    + " seconds " + tempCount.getMinutes() + " minutes "
                    + tempCount.getHours() + " hours left of break. Go get coffee!");
        }
    }

    public void swapModes(WorkHourCounter tempCount) {


        if (working) {

            tempCount.setCycle(tempCount.getCycle() - 1);

            tempCount.setMinutes(settings.getStudyBreakM());

            tempCount.setHours(settings.getStudyBreakH());

            working = false;

        } else {

            saveNotes();

            tempCount.setMinutes(settings.getStudyWorkM());

            tempCount.setHours(settings.getStudyWorkH());

            working = true;
        }


    }

    public void updatetingStatus(WorkHourCounter tempCount, Long countdown) {

        CT.setText("Current time: " + LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm")));
        cycles.setText("Cycles left: " + tempCount.getCycle());

        if (working) {
            countOverall.counter(countOverall.getCurrentCount());
            WTOverall.setText("Overall worktime: " + countOverall.timeToString());
        }

    }


}







