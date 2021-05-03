package cos.ui;

import cos.controls.Course;
import cos.controls.MainController;
import cos.controls.WorkHourCounter;
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

public class CreateCourse {


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
    private TextField value;

    @FXML
    private Button remove;

    @FXML
    private Button save;


    private Course course;


    @FXML
    public void initialize() {

        name.setPromptText("Name");
        description.setPromptText("Description");
        notes.setPromptText("Add notes");

        dateStart.setValue(LocalDate.now());
        startH.setText("00");
        startM.setText("00");

        dateEnd.setValue(LocalDate.now().plusDays(56));
        endH.setText("00");
        endM.setText("00");
        notes.wrapTextProperty().setValue(true);

        value.setText("5");
        courseLoad();

    }

    @FXML
    public void editDefault() {

        dateStart.setValue(course.getWorkHoursSpent().getStartDate().toLocalDate());
        startH.setText(course.getWorkHoursSpent().getStartDate().toLocalTime().toString().substring(0, 2));
        startM.setText(course.getWorkHoursSpent().getStartDate().toLocalTime().toString().substring(3, 5));

        dateEnd.setValue(course.getWorkHoursSpent().getEndDate().toLocalDate());
        endH.setText(course.getWorkHoursSpent().getEndDate().toLocalTime().toString().substring(0, 2));
        endM.setText(course.getWorkHoursSpent().getEndDate().toLocalTime().toString().substring(3, 5));

        notes.setText(course.getNotes());
        name.setText(course.getName());
        description.setText(course.getDescription());
        priority.setText(course.getPriority() + "");
        value.setText(course.getValue() + "");
        remove.setVisible(true);

    }

    @FXML
    public void courseLoad() {

        Runnable update = new Runnable() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Stage stage = (Stage) dateEnd.getScene().getWindow();
                        course = (Course) stage.getUserData();
                        if (course != null) {
                            editDefault();
                        }
                    }
                });
            }
        };
        new Thread(update).start();


    }

    public boolean isAcceptable() {

        if (name.getText().equals("")) {
            //Error handler
            name.setPromptText("You must add a name");
            name.setBackground(new Background(new BackgroundFill(Color.CRIMSON, new CornerRadii(10), Insets.EMPTY)));
            return false;
        }
        if (description.getText().equals("")) {
            //Error handler
            description.setPromptText("You must add a description");
            description.setBackground(new Background(new BackgroundFill(Color.CRIMSON, new CornerRadii(10), Insets.EMPTY)));
            return false;
        }
        return true;
    }

    @FXML
    public void editcourse() {

        if (!isAcceptable()) {
            return;
        }

        course.setName(name.getText());
        course.setDescription(description.getText());
        course.setNotes(notes.getText());
        course.setValue(Integer.parseInt(value.getText()));
        WorkHourCounter dates = setupWHC();

        course.setWorkHoursSpent(dates);
        close();
    }

    public WorkHourCounter setupWHC() {


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
    public void saveCourse() {

        if (course != null) {
            editcourse();
            return;
        }

        WorkHourCounter courseWHC = setupWHC();

        int val = Integer.parseInt(value.getText());
        int prio = Integer.parseInt(priority.getText());

        if (!isAcceptable()) {
            return;
        }

        MainController.getCourseHandler().createCourse(false, courseWHC,
                name.getText(), description.getText(), notes.getText(), prio, val);


        Stage stage = (Stage) save.getScene().getWindow();

        stage.close();


    }

    @FXML
    public void remove() {
        MainController.getCourseHandler().setCurrent(null);
        MainController.getCourseHandler().markCourseAsDone(course, true);
        close();

    }

    @FXML
    public void close() {

        Stage stage = (Stage) save.getScene().getWindow();
        stage.close();
    }

}
