package cos.ui;

import cos.controls.Task;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class NoteCheckup {



    @FXML
    private Label name;

    @FXML
    private TextArea notes;

    @FXML
    private Button done;

    @FXML
    private Task task;

    @FXML
    public void initialize(){
    notesLoad();
    }


    @FXML
    public void notesLoad() {

        Runnable update = new Runnable() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Stage stage = (Stage) notes.getScene().getWindow();
                        task = (Task) stage.getUserData();
                        if (task != null) {
                            name.setText(task.toString()+"notes");
                            notes.setText(task.buildNotes());
                        }
                    }
                });
            }
        };
        new Thread(update).start();

    }

    @FXML
    public void close(){

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
            Stage close = (Stage) notes.getScene().getWindow();
            close.close();
            }
        });
    }
}
