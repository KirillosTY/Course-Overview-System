package cos.ui;

import cos.controls.MainController;
import cos.controls.Settings;
import cos.controls.Task;
import cos.controls.WorkHourCounter;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.Duration;
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

        if (!settings.isStudyShowT()) {
            overallVBox.getChildren().remove(CT);
        }
        if (!settings.isStudyShowWT()) {
            overallVBox.getChildren().remove(WTOverall);
        }
        if (!settings.isStudyShowCycles()) {
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
        count.setCurrentCount(settings.getStudyWorkH()*3600L+settings.getStudyWorkM()*60+5);
        count.setCycle(settings.getStudyCycle());

        Task task = MainController.getCourseHandler().getCurrentTask();
        currentTask.setText("Current task: " + task.getName()+ " - "+task.getDescription()+ " - "
                + Duration.between(LocalDateTime.now(), task.getWorkHoursSpent().getEndDate()).toHours()
                +" Hours left");
        msg.setText("\n\n\n" + MainController.getCourseHandler().getCurrentTask().getNotes());
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                onCloseCounterStop();
            }
        });
    }

    @FXML
    public void onCloseCounterStop() {
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
                .setCurrentCount(courseOverall + countOverall.getCurrentCount() - startHours);
        MainController.getInformationHandler().saveCourseHandler(MainController.getCourseHandler());
        stage.close();

    }

    public void cancel() {
        Stage s = (Stage) cancel.getScene().getWindow();
        countOverall.setCurrentCount(startHours);
        counter.stop();
        s.close();

    }

    public void dayDone(WorkHourCounter tempCount) {
        WTLeft.setText("The day is over, well done! Your notes were saved automatically.");
        saveNotes();
        counter.stop();

    }

    public void saveNotes() {
        if(msg.getText().length() > 2){
        MainController.getCourseHandler().getCurrentTask().saveNotesWithStamp(msg.getText());
        MainController.getCourseHandler().getCurrent().saveNotesWithStamp(msg.getText(),
                MainController.getCourseHandler().getCurrentTask().toString());
        }
    }


    public void startAnimation(WorkHourCounter tempCount) {
        final LocalDateTime startTime = LocalDateTime.now().plusSeconds(tempCount.getCurrentCount());

        counter = new AnimationTimer() {

            Long startCounter = 0L;

            @Override
            public void handle(long currentTime) {

                if (currentTime - startCounter >= 1_000_000_000) {
                    startCounter = currentTime;
                    updatetingStatus(tempCount);
                    clockUpdate(tempCount);
                    tempCount.setCurrentCount(tempCount.getCurrentCount() - 1);

                }


                if (LocalDateTime.now().isAfter(startTime)) {

                    swapModes(tempCount);

                    if (tempCount.getCycle() >= 0) {
                        startAnimation(tempCount);
                        counter.start();

                        stop();
                    } else {
                        dayDone(tempCount);

                    }
                }
            }

        };

    }

    public void clockUpdate(WorkHourCounter tempCount) {


        if (working) {

            WTLeft.setText(tempCount.timeToString()+" left for break");


        } else {

            WTLeft.setText(tempCount.timeToString()+" left for work");
        }
    }

    public void swapModes(WorkHourCounter tempCount) {


        if (working) {

            tempCount.setCycle(tempCount.getCycle() - 1);

            count.setCurrentCount(settings.getStudyBreakH()*3600L
                    +settings.getStudyBreakM()*60+5);

            working = false;

        } else {

            saveNotes();

            tempCount.setCurrentCount(settings.getStudyWorkH()*3600L
                    +settings.getStudyWorkM()*60+5);

            working = true;
        }


    }

    public void updatetingStatus(WorkHourCounter tempCount) {

        CT.setText("Current time: " + LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm")));
        cycles.setText("Cycles left: " + tempCount.getCycle());

        if (working) {
            countOverall.setCurrentCount(countOverall.getCurrentCount()+1);
            WTOverall.setText("You have worked for " + countOverall.getDays()+ " days " + countOverall.getHours()+" hours "
                    +  countOverall.getMinutes() + " minutes.");
        }

    }


}







