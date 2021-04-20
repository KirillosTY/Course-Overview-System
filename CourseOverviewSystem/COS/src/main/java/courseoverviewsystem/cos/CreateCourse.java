package courseoverviewsystem.cos;

import Controls.CourseHandler;
import Controls.MainController;
import Controls.WorkHourCounter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
    private Button save;

    private final CourseHandler ch;

    public CreateCourse() {
        ch = MainController.getCourseHandler();


    }

    @FXML
    public void initialize() {

        dateStart.setValue(LocalDate.now());
        startH.setText("23");
        startM.setText("59");

        dateEnd.setValue(LocalDate.now().plusDays(56));
        endH.setText("23");
        endM.setText("59");

        value.setText("5");


    }

    @FXML
    public void saveCourse() {

        WorkHourCounter courseWHC = new WorkHourCounter();

        LocalTime timeFormat = LocalTime.of(Integer.parseInt(startH.getText()),
                Integer.parseInt(startM.getText()));

        LocalDateTime dateTimeFormat = LocalDateTime.of(dateStart.getValue(), timeFormat);

        courseWHC.setStartDate(dateTimeFormat);

        timeFormat = LocalTime.of(Integer.parseInt(endH.getText()),
                Integer.parseInt(endM.getText()));

        dateTimeFormat = LocalDateTime.of(dateEnd.getValue(), timeFormat);

        int val = Integer.parseInt(value.getText());
        int prio = Integer.parseInt(priority.getText());

        ch.createCourse(false, courseWHC,
                name.getText(), description.getText(), notes.getText(), prio, val);

        MainController.getInformationHandler().saveCourseHandler(ch);
        Stage stage = (Stage) save.getScene().getWindow();

        stage.close();


    }

}
