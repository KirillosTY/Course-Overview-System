package cos.ui;

import cos.controls.Course;
import cos.controls.CourseHandler;
import cos.controls.MainController;
import cos.controls.Task;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

import java.time.LocalDate;
import java.time.LocalTime;

class ListCells extends ListCell<Task> {

    @Override
    protected void updateItem(Task item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
        } else {
            setText(item.toString());
            setTextFill(Paint.valueOf("#8D3016"));
            setTooltip(new Tooltip(item.getName() + " - " + item.getDescription()));
        }

    }


}

public class UiMain {

    private static CourseHandler courseHandler;
    @FXML
    protected ComboBox<Course> courselist;
    @FXML
    private Label text;
    @FXML
    private Stage mainStage,currentStage;
    @FXML
    private ListView<Task> tasklist;
    @FXML
    private Button startTime;
    @FXML
    private Button editCourse,editTask,addC, addT;;
    @FXML
    private CheckBox doneCourse, doneTask;

    @FXML
    private FlowPane mainBorder;

    private double mouseLocationX;

    private double mouseLocationY;

    @FXML
    public void initialize() {


        courseHandler = MainController.getCourseHandler();
        courselist.setItems(FXCollections.observableList(courseHandler.getCourseList()));
        checkTime();
        defaultStart();
        actionSetupsCourse();
        actionSetupTasks();
        updateLists();
        listUISets();
        setStageWindows();
        tooltipSetup();
    }


    private void checkTime() {

        String timeofDay = "Good morning, today is ";

        if (LocalTime.now().isAfter(LocalTime.of(12, 0))) {
            timeofDay = "Good day, today is ";
        } else if (LocalTime.now().isAfter(LocalTime.of(16, 0))) {
            timeofDay = "Good afternoon, today is ";
        } else if (LocalTime.now().isAfter(LocalTime.of(12, 0))) {
            timeofDay = "Evening, today is ";
        }

        text.setText(timeofDay + LocalDate.now());

    }

    public void defaultStart() {
        courselist.setVisibleRowCount(5);

        courselist.setViewOrder(-2);

        tasklist.setMaxWidth(200);


        courselist.setPlaceholder(new Label("Add a course to start!"));
    }

    public void actionSetupsCourse() {

        listCActionSetup();
        doneCActionSetup();

    }

    @FXML
    public void listCActionSetup() {
        courselist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Course>() {
            @Override
            public void changed(ObservableValue<? extends Course> observableValue, Course course, Course t1) {

                if (t1 != null) {
                    courseHandler.setCurrent(courselist.getSelectionModel().getSelectedItem());
                    updateTasks();

                } else {
                    courseHandler.setCurrent(null);
                    courseHandler.setCurrentTask(null);

                }

            }
        });

    }

    @FXML
    public void doneCActionSetup() {
        doneCourse.selectedProperty().addListener((observableValue, aBoolean, t1) -> {
            Course tempCourse = courseHandler.getCurrent();
            courseHandler.setCurrent(null);
            courseHandler.setCurrentTask(null);
            courseHandler.markCourseAsDone(tempCourse, false);
            tasklist.setItems(null);
            updateLists();
            doneCourse.setSelected(false);

        });

    }


    public void actionSetupTasks() {

        listTActionSetup();
        doneTActionSetup();

    }

    @FXML
    public void listTActionSetup() {
        tasklist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Task>() {
            @Override
            public void changed(ObservableValue<? extends Task> observableValue, Task task, Task t1) {
                if (tasklist.getSelectionModel().getSelectedItem() != null) {

                    courseHandler.setCurrentTask(tasklist.getSelectionModel().getSelectedItem());

                    startTime.setText("Start studying: " + courseHandler.getCurrentTask().getName());

                } else {
                    courseHandler.setCurrentTask(null);
                    startTime.setText("Start studying");

                }
            }
        });
    }

    @FXML
    public void doneTActionSetup() {
        doneTask.selectedProperty().addListener((observableValue, aBoolean, task) -> {
            if (courseHandler.getCurrentTask() != null) {
                taskIsDone();
                doneTask.setSelected(false);
            } else {
                doneTask.setSelected(false);
            }

        });
    }

    @FXML
    public void updateLists() {

        courselist.setItems(FXCollections.observableList(MainController.getCourseHandler().getCourseList()));

        if (courseHandler.getCurrent() != null && courseHandler.getCourseList().contains(courseHandler.getCurrent())) {
            courselist.getSelectionModel().select(courseHandler.getCurrent());
            updateTasks();

        } else {
            courselist.setPlaceholder(new Label("Add a course to start!"));
            tasklist.setPlaceholder(new Label("Add tasks!"));
        }

    }


    @FXML
    public void updateTasks() {

        if (courseHandler.getCurrent() != null) {
            tasklist.setItems(FXCollections.observableList(courseHandler.getCurrent().getTaskList()));
            tasklist.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

            if (courseHandler.getCurrentTask() == null) {
                tasklist.setPlaceholder(new Label("Add tasks!"));
            } else if (courseHandler.getCurrent().getTaskList().contains(courseHandler.getCurrentTask())) {
                tasklist.getSelectionModel().select(courseHandler.getCurrentTask());

            } else {
                tasklist.setPlaceholder(new Label("Add tasks!"));

            }
        } else {
            tasklist.setPlaceholder(new Label("Add tasks!"));
        }

    }

    @FXML
    public void listUISets() {

        tasklist.setCellFactory(new Callback<ListView<Task>, ListCell<Task>>() {
            @Override
            public ListCell<Task> call(ListView<Task> taskListView) {

                ListCells styled = new ListCells();
                styled.setTextFill(Paint.valueOf("#8D3016"));
                styled.setStyle("-fx-background-color: transparent (#8D3016); ");

                return styled;
            }
        });


    }

    public void setStageWindows() {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                mainStage = (Stage) text.getScene().getWindow();
                setupStageLoc();

            }
        });

    }

    @FXML
    public void setupStageLoc() {
        mainStage.getScene().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                mouseLocationX = mainStage.getX() - mouseEvent.getScreenX();
                mouseLocationY = mainStage.getY() - mouseEvent.getScreenY();
            }
        });

        mainStage.getScene().setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                mainStage.setX(mouseLocationX + mouseEvent.getScreenX());
                mainStage.setY(mouseLocationY + mouseEvent.getScreenY());
            }
        });

    }

    @FXML
    private void tooltipSetup() {
        courselist.setTooltip(new Tooltip("Select a course!"));
        addC.setTooltip(new Tooltip("Add a course!"));

        addT.setTooltip(new Tooltip("Add a task!"));
        startTime.setTooltip(new Tooltip("Remember to choose the correct task!"));

        setupTooltipLocation(courselist);
        setupTooltipLocation(addC);
        setupTooltipLocation(addT);
        setupTooltipLocation(startTime);
    }

    public void setupTooltipLocation(Control e) {
        e.setOnMousePressed(location -> {
            mouseLocationX = location.getScreenX();

            mouseLocationY = location.getScreenY();
        });
        tooltipActionSetup(e.getTooltip());
    }

    public void tooltipActionSetup(Tooltip tip) {

        tip.setShowDelay(Duration.seconds(3));
        tip.setShowDuration(Duration.seconds(2));
        tip.setHideOnEscape(true);
        tip.setAutoHide(true);
    }


    @FXML
    public void mainIsDone() {
        if (currentStage != null) {
            for (Stage s : UiMainStart.stageControls) {
                s.close();
            }
        }

        mainStage = (Stage) text.getScene().getWindow();
        mainStage.close();

    }

    @FXML
    public void courseIsDone() {

        if (courseHandler.getCurrent() != null) {
            courseHandler.markCourseAsDone(courseHandler.getCurrent(), false);
            courseHandler.setCurrent(null);
            courseHandler.setCurrentTask(null);

        }
        updateLists();

    }

    @FXML
    public void taskIsDone() {
        if (courseHandler.getCurrentTask() == null) {

            return;
        }

        courseHandler.getCurrent().markTaskDone(courseHandler.getCurrentTask());
        courseHandler.setCurrentTask(null);
        updateLists();
        updateTasks();

    }


    public void closeAllExceptMain() {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                UiMainStart.stageControls.forEach(s -> {
                    if (s != mainStage && s != currentStage) {
                        s.close();
                    }
                });

            }
        });
    }

    public void disableMain(boolean usable) {

        mainBorder.setDisable(usable);

    }


    @FXML
    public void studyStart() {
        closeAllExceptMain();

        if (courseHandler.getCurrentTask() != null) {

            try {
                currentStage = UiMainStart.viewChanger("studyStart.fxml",
                        "Workhour counter has started!", false);

                currentStage.showAndWait();

            } catch (Exception e) {
                UiMainStart.popupText("Study start", "Something went wrong opening this window, please restart the application");
            }
        } else {
            errorHandler();
        }

    }

    @FXML
    public void studySet() {
        closeAllExceptMain();

        try {
            currentStage = UiMainStart.viewChanger("studysettings.fxml",
                    "Study settings", false);

            currentStage.showAndWait();
        } catch (Exception e) {
            UiMainStart.popupText("Study settings", "Something went wrong opening this  window, please restart the application");
        }
    }

    @FXML
    public void taskCreate() {
        closeAllExceptMain();

        if (courseHandler.getCurrent() != null) {

            try {
                currentStage = UiMainStart.viewChanger("TaskEditCreation.fxml",
                        "Task Creation", false);

                currentStage.setOnHidden((e) -> {
                    updateLists();
                    updateTasks();

                });
                currentStage.showAndWait();
            } catch (Exception e) {
                UiMainStart.popupText("Task creation", "Something went wrong opening this  window, please restart the application");
            }
        } else {
            errorHandler();
        }
    }


    @FXML
    public void courseCreate() {
        closeAllExceptMain();

        try {
            currentStage = UiMainStart.viewChanger("CreateCourse.fxml",
                    "Course creation!", false);
            currentStage.setOnHidden((e) -> {

                updateLists();

                MainController.getInformationHandler().saveCourseHandler(MainController.getCourseHandler());

            });

            currentStage.showAndWait();
        } catch (Exception e) {
            UiMainStart.popupText("Course creation", "Something went wrong opening this  window, please restart the application");
        }

    }


    @FXML
    public void pastUpcomingCurrentEdit() {

        closeAllExceptMain();
        disableMain(true);
        try {

            currentStage = UiMainStart.viewChanger("EditCourseAll.fxml",
                    "Manage all courses through here!", false);

            currentStage.setOnHidden(e -> {

                courseHandler.setCurrent(null);
                courseHandler.setCurrentTask(null);
                updateLists();
                updateTasks();
                disableMain(false);

            });
            currentStage.showAndWait();

        } catch (Exception e) {
            UiMainStart.popupText("Manage all courses", "Something went wrong opening this  window, please restart the application");
        }
    }


    @FXML
    public void errorHandler() {
        Tooltip tipError = new Tooltip();
        tooltipActionSetup(tipError);
        if (courseHandler.getCourseList().isEmpty()) {
            tipError.setText("Add a course first!");
            tipError.show(addC, mouseLocationX, mouseLocationY);

        } else if (courseHandler.getCurrent() == null) {
            tipError.setText("Select a course first!");
            tipError.show(courselist, mouseLocationX, mouseLocationY);

        } else if (courseHandler.getCurrent().getTaskList().isEmpty()) {
            tipError.setText("Add a task first!");
            tipError.show(addT, mouseLocationX, mouseLocationY);


        } else if (courseHandler.getCurrentTask() == null) {
            tipError.setText("Select a task first!");
            tipError.show(tasklist, mouseLocationX, mouseLocationY);

        }
    }
}
