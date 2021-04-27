package courseoverviewsystem.cos;

import controls.CourseHandler;
import controls.MainController;
import controls.Task;
import controls.WorkHourCounter;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class TaskEditCreation {


    private  CourseHandler ch;
    @FXML
    private TextField name;
    @FXML
    private TextField description;
    @FXML
    private TextArea notes;
    @FXML
    private TextField startH;
    @FXML
    private TextField startM;
    @FXML
    private DatePicker dateStart;
    @FXML
    private TextField endH;
    @FXML
    private TextField endM;
    @FXML
    private DatePicker dateEnd;
    @FXML
    private TextField priority;
    @FXML
    private Button save;

    private Task task;

    public TaskEditCreation() {

        ch = MainController.getCourseHandler();

    }

    @FXML
    public void initialize() {


        name.setPromptText("Name");
        description.setPromptText("Description");
        notes.setPromptText("Add notes");
        dateStart.setValue(LocalDate.now());
        startH.setText("00");
        startM.setText("00");
        notes.wrapTextProperty().setValue(true);

        dateEnd.setValue(LocalDate.now().plusDays(7));
        endH.setText("23");
        endM.setText("59");
        taskLoad();
    }

    @FXML
    public void editDefault(){

        dateStart.setValue(task.getWorkHoursSpent().getStartDate().toLocalDate());
        startH.setText(task.getWorkHoursSpent().getStartDate().toLocalTime().toString().substring(0,2));
        startM.setText(task.getWorkHoursSpent().getStartDate().toLocalTime().toString().substring(3,5));

        dateEnd.setValue(task.getWorkHoursSpent().getEndDate().toLocalDate());
        endH.setText(task.getWorkHoursSpent().getEndDate().toLocalTime().toString().substring(0,2));
        endM.setText(task.getWorkHoursSpent().getEndDate().toLocalTime().toString().substring(3,5));

        notes.setText(task.getNotes());
        name.setText(task.getName());
        description.setText(task.getDescription());
        priority.setText(task.getPriority()+"");

    }

    @FXML
    public void taskLoad(){

        Runnable update = new Runnable() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Stage stage = (Stage) dateEnd.getScene().getWindow();
                        task  = (Task) stage.getUserData();

                        if(task != null){
                            editDefault();
                        }
                    }
                });
            }
        };
        new Thread(update).start();


    }

    public boolean isAcceptable(){

        if(name.getText().equals("")) {
            //Error handler
            name.setPromptText("You must add a name");
            name.setBackground(new Background(new BackgroundFill(Color.CRIMSON, new CornerRadii(10), Insets.EMPTY)));
            return false;
        }
        if(description.getText().equals("")){
            //Error handler
            description.setPromptText("You must add a description");
            description.setBackground(new Background(new BackgroundFill(Color.CRIMSON, new CornerRadii(10), Insets.EMPTY)));
            return false;
        }
        return true;
    }

    @FXML
    public void editTask(){
       if(!isAcceptable()){
           return;
       }

        task.setName(name.getText());
        task.setDescription(description.getText());
        task.setNotes(notes.getText());

        WorkHourCounter dates = setupWHC();

        task.setWorkHoursSpent(dates);
        close();
    }


    public WorkHourCounter setupWHC(){


        WorkHourCounter taskWHC = new WorkHourCounter();

        LocalTime timeFormat = LocalTime.of(Integer.parseInt(startH.getText()),
                Integer.parseInt(startM.getText()));

        LocalDateTime dateTimeFormat = LocalDateTime.of(dateStart.getValue(), timeFormat);

        dateStart.setEditable(false);

        taskWHC.setStartDate(dateTimeFormat);

        timeFormat = LocalTime.of(Integer.parseInt(endH.getText()),
                Integer.parseInt(endM.getText()));

        dateTimeFormat = LocalDateTime.of(dateEnd.getValue(), timeFormat);
        dateEnd.setEditable(false);
        taskWHC.setEndDate(dateTimeFormat);
        return taskWHC;
    }

    @FXML
    public void saveTask() {

        if(task != null){
            System.out.println(task);
            editTask();
            return;
        }

       if(!isAcceptable()){
           return;
       }


        WorkHourCounter taskWHC = setupWHC();

        int prio = Integer.parseInt(priority.getText());

        String time = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(
                FormatStyle.LONG,FormatStyle.MEDIUM));

        ch.getCurrent().addTask(false, taskWHC,
                name.getText(), description.getText(),time+":\n\n"+notes.getText(), prio);

        close();


    }

    @FXML
    public void close(){

        Stage stage = (Stage) save.getScene().getWindow();
        stage.close();
    }

}
