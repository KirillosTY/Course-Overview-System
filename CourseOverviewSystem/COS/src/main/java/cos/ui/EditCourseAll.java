package cos.ui;

import cos.controls.Course;
import cos.controls.CourseHandler;
import cos.controls.MainController;
import cos.controls.Task;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

public class EditCourseAll {

    @FXML
    private ComboBox<String> courseListSelector;

    @FXML
    private ComboBox<Course> courseSelector;

    @FXML
    private ListView<Task> taskSelector;

    @FXML
    private Button editC;

    @FXML
    private Button addC;

    @FXML
    private Button editT;

    @FXML
    private Button addT;

    @FXML
    private TextArea courseNotes;

    @FXML
    private TextArea taskNotes;

    @FXML
    private String upcoming;

    @FXML
    private String past;

    @FXML
    private String current;

    @FXML
    private HashMap<String, Stage> stagesNeeded;

    private CourseHandler cHandler;

    private ArrayList<Course> currentCourseList;

    private Course currentCourse;

    private Task currentTask;


    @FXML
    public void initialize(){


        cHandler = MainController.getCourseHandler();

        upcoming = "Upcoming courses";
        past = "Past courses";
        current = "Current courses";

        setupControls();

        courlistSeleUpdate();


    }

    public void loadStages(){

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Stage stage = (Stage) courseListSelector.getScene().getWindow();
                stagesNeeded = (HashMap<String,Stage>) stage.getUserData();
            }
        });

    }
    @FXML
    public void courlistSeleUpdate(){

        courseListSelector.getItems().addAll(current,past,upcoming);

        currentCourseList = cHandler.getCourseList();

        courseListSelector.getSelectionModel().selectFirst();

        courListUpdate();

    }

    @FXML
    public void courListUpdate(){

        if(!courseSelector.getItems().isEmpty()){

            if (courseSelector.getSelectionModel().getSelectedItem() == null) {

                courseSelector.getSelectionModel().selectFirst();

            }

            cHandler.setCurrent(courseSelector.getSelectionModel().getSelectedItem());
            taskSelector.setItems(FXCollections.observableList(cHandler.getCurrent().getTaskList()));
            msgSets();

        } else {
                taskNotes.clear();
                courseNotes.clear();
                taskSelector.setItems(FXCollections.observableList(new ArrayList<>()));
        }

    }


    @FXML
    public void taskListUpdater(){

            if(!taskSelector.getItems().isEmpty()){


                if(taskSelector.getSelectionModel().getSelectedItem() == null){

                    taskSelector.getSelectionModel().selectFirst();
                    cHandler.setCurrentTask(taskSelector.getSelectionModel().getSelectedItem());
                }

                msgSetsTask();
            } else {
                taskNotes.clear();
            }

    }

    @FXML
    public void setupControls(){

       courseListSelector.setOnAction(updateList->{

          String chosenCourseList = courseListSelector.getSelectionModel().getSelectedItem();

          if(chosenCourseList.equals(upcoming)){
              currentCourseList = cHandler.getUpcomingCourse();

          }
          if(chosenCourseList.equals(past)){
              currentCourseList = cHandler.getPastCourse();

          }
          if(chosenCourseList.equals(current)){
              currentCourseList = cHandler.getCourseList();
          }
          courseSelector.setItems(FXCollections.observableList(currentCourseList));
          courListUpdate();

       });


       courseSelector.getSelectionModel().selectedItemProperty().addListener((observableValue, aBoolean, course)-> {

           if(courseSelector.getSelectionModel().getSelectedItem()!= null) {
               cHandler.setCurrent(course);
               msgSets();
               taskListUpdater();
           } else {
               currentCourse = null;
           }


       });

        taskSelector.getSelectionModel().selectedItemProperty().addListener((observableValue, aBoolean, course)-> {
            if(taskSelector.getSelectionModel().getSelectedItem() != null) {
                msgSetsTask();
                currentTask = taskSelector.getSelectionModel().getSelectedItem();
            } else {
                currentTask = null;
            }
        });


    }

    @FXML
    public void msgSets(){

        courseNotes.setText(courseSelector.getSelectionModel().getSelectedItem().getNotes());

    }

    @FXML
    public void msgSetsTask(){

        taskNotes.setText(taskSelector.getSelectionModel().getSelectedItem().getNotes());
    }

    @FXML
    public void editTask(){
        createEditTask(false);
    }

    @FXML
    public void editCourse(){
        createEditCourse(false);
    }

    @FXML
    public void createTask(){
        createEditTask(true);
    }

    @FXML
    public void createCourse(){
        createEditCourse(true);

    }

    @FXML
    public void createEditCourse(boolean create) {
        closeAllExceptECA();

        if(currentCourseList == null){
            return;
        }
        try {
            Stage editStage = UiMainStart.viewChanger("CreateCourse.fxml", "Edit course", true);

            if(editStage != null) {

                if(!create) {
                    editStage.setUserData(currentCourse);
                }

                editStage.showAndWait();
            }
        }catch (Exception ECAec){
            System.out.println("ECAec");
        }
    }



    @FXML
    public void createEditTask(boolean create){
        closeAllExceptECA();
        if(currentCourse == null){
            return;
        }
        try {
            Stage editStage = UiMainStart.viewChanger("TaskEditCreation.fxml", "Edit Task", true);

            if(editStage != null) {
                if(!create) {
                    editStage.setUserData(currentTask);
                }
                editStage.showAndWait();
            }
        }catch (Exception ECAec){
            System.out.println("ECAec");
        }

    }

    public void closeAllExceptECA(){


        Platform.runLater(()->{

            for(Stage s: UiMainStart.loadNeeded){
                s.close();
            }
        });

    }




}
