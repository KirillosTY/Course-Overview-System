package cos.ui;


import cos.controls.MainController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.media.AudioClip;
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
    public static Stage viewChanger(String resource, String windowName, boolean load) {

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

        } catch (IOException e) {
            popupText(windowName, "Please restart the application. This is most likely an user error, has to be." +
                    "please say it is a user error");
            return null;
        }

    }

    private static void addStagesLater(Stage created) {

        Platform.runLater(() -> {

            loadNeeded.add(created);
        });

    }

    @FXML
    public static void popupText(String windowName, String errorMSG) {

        try {

            Alert error = new Alert(Alert.AlertType.WARNING);
            error.setTitle("Error" + windowName);
            error.setHeaderText(errorMSG);
            error.setWidth(200);
            error.showAndWait();
        } catch (Exception e) {

        }
    }

    @Override
    public void start(Stage stage) {
        setStageWindows();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("UiMain.fxml"));

            Scene scene = new Scene(root, 270, 625);

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

                for(Stage s :stageControls){
                    s.close();
                }

            });


            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            popupText(" Loading files and main Window ", "" +
                    "loaded coursehandler/settings.bin does not match the version of this program. Check files, remove config if necessary(Will also wipe out data).");
        }

    }

    public void setStageWindows() {

        loadNeeded = new ArrayList<>();

        stageControls = new ArrayList<>();

    }

    public static void playSound(String url){
        try {
          AudioClip  swapSound = new AudioClip(url);
          swapSound.play();
        } catch (Exception e){
            e.printStackTrace();
        }

    }


}