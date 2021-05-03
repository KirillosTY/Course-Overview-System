package cos.ui;


import cos.controls.MainController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * JavaFX UiMainStart
 */
public class UiMainStart extends Application {


    protected static ArrayList<Stage> stageControls;

    protected static ArrayList<Stage> loadNeeded;


    public static void main(String[] args) {
        launch(args);
    }

    @FXML
    public static Stage viewChanger(String resource, String windowName, boolean load) throws Exception {

        try {

            FXMLLoader loader = new FXMLLoader(UiMainStart.class.getResource(resource));

            Parent root = loader.load();

            Scene viewC = new Scene(root);


            Stage created = new Stage();

            created.setScene(viewC);

            created.setTitle(windowName);

            stageControls.add(created);
            if (load) {
                addStagesLater(created);
            }

            return created;

        } catch (Exception e) {

            return null;
        }

    }

    private static void addStagesLater(Stage created) {

        Platform.runLater(() -> {

            loadNeeded.add(created);
        });

    }

    @Override
    public void start(Stage stage) {
        setStageWindows();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("UiMain.fxml"));

            Scene scene = new Scene(root, 270, 590);

            stage.setTitle("Course Overview System");
            stage.setScene(scene);

            ArrayList<Stage> closingStages = new ArrayList<>();
            stage.setUserData(closingStages);
            stageControls.add(stage);
            stage.setOnHidden(save -> {

                MainController.getInformationHandler().saveCourseHandler(
                        MainController.getCourseHandler());
                MainController.getInformationHandler().saveSettings(MainController.getSettings());
                for (Stage s : closingStages) {
                    s.close();
                }
            });


            stage.show();
        } catch (IOException e) {

        }

    }

    public void setStageWindows() {

        loadNeeded = new ArrayList<>();

        stageControls = new ArrayList<>();

    }


}