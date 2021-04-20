package courseoverviewsystem.cos;


import Controls.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX UiMainStart
 */
public class UiMainStart extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("UiMain.fxml"));

        Scene scene = new Scene(root, 770, 470);

        stage.setTitle("Course Overview System");
        stage.setScene(scene);

        stage.setOnCloseRequest(e -> {
            MainController.getInformationHandler().saveCourseHandler(MainController.getCourseHandler());
        });

        stage.show();

    }


}