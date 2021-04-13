package courseoverviewsystem.cos;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX UiMainStart
 */
public class UiMainStart extends Application {

    private static Scene scene;

   @Override
    public void start(Stage stage) throws Exception {

       Parent root = FXMLLoader.load(getClass().getResource("UiMain.fxml"));
    
        Scene scene = new Scene(root, 770, 470);
    
        stage.setTitle("Course Overview System");
        stage.setScene(scene);
        stage.show();
    }

}