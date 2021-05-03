package cos.ui;

import cos.controls.Course;
import cos.controls.CourseHandler;
import cos.controls.MainController;
import cos.controls.Task;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Callback;

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
    private Stage currentStage;
    @FXML
    private Stage mainStage;
    @FXML
    private ListView<Task> tasklist;
    @FXML
    private Button startTime;
    @FXML
    private Button editTask;
    @FXML
    private CheckBox doneTask;
    @FXML
    private Button editCourse;
    @FXML
    private CheckBox doneCourse;
    @FXML
    private FlowPane mainBorder;
    @FXML
    private final boolean UPCcheck = false;

    @FXML
    public void initialize() {


        courseHandler = MainController.getCourseHandler();

        text.setText(checkTime() + LocalDate.now());

        courselist.setItems(FXCollections.observableList(courseHandler.getCourseList()));
        defaultStart();
        actionSetupsCourse();
        actionSetupTasks();
        updateLists();
        listUISets();
        setStageWindows();
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

    private String checkTime() {

        String timeofDay = "Good morning, today is ";

        if (LocalTime.now().isAfter(LocalTime.of(12, 0))) {
            timeofDay = "Good day, today is ";
        } else if (LocalTime.now().isAfter(LocalTime.of(16, 0))) {
            timeofDay = "Good afternoon, today is ";
        } else if (LocalTime.now().isAfter(LocalTime.of(12, 0))) {
            timeofDay = "Evening, today is ";
        }

        return timeofDay;

    }

    public void actionSetupsCourse() {

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

        tasklist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Task>() {
            @Override
            public void changed(ObservableValue<? extends Task> observableValue, Task task, Task t1) {
                if (tasklist.getSelectionModel().getSelectedItem() != null) {

                    courseHandler.setCurrentTask(tasklist.getSelectionModel().getSelectedItem());

                } else {
                    courseHandler.setCurrentTask(null);

                }
            }
        });

        doneTask.selectedProperty().addListener((observableValue, aBoolean, task) -> {
            if (courseHandler.getCurrentTask() != null) {
                taskIsDone();
                doneTask.setSelected(false);
            } else {
                doneTask.setSelected(false);
            }

        });

    }

    public void setStageWindows() {


        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                mainStage = (Stage) text.getScene().getWindow();
            }
        });

    }

    public void defaultStart() {
        courselist.setVisibleRowCount(5);

        courselist.setViewOrder(-2);

        tasklist.setMaxWidth(200);


        courselist.setPlaceholder(new Label("Add a course to start!"));
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
                e.printStackTrace();
            }
        } else {
            errorHandler();
        }

    }

    @FXML
    public void studySet() {
        closeAllExceptMain();

        try {
            currentStage = UiMainStart.viewChanger("studySettings.fxml",
                    "Study settings", false);

            currentStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
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
                e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }


    @FXML
    public void PopupText() {
        closeAllExceptMain();

        try {
            currentStage = UiMainStart.viewChanger("PopupText.fxml",
                    MainController.getPopupText()[0], false);

            currentStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @FXML
    public void errorHandler() {
        closeAllExceptMain();

        System.out.println("Et ole valinnut tehtävää");

    }
}
