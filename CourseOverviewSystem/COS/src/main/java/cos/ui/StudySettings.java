package cos.ui;

import cos.controls.MainController;
import cos.controls.Settings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StudySettings {

    Settings settings;

    @FXML
    private Button save;

    @FXML
    private TextField workTH, workTM,  breakTM, breakTH;

    @FXML
    private TextField cycle;

    @FXML
    private CheckBox showCT, showWT, showCycles;


    @FXML
    public void initialize() {

        settings = MainController.getSettings();

        loadSettings();


    }

    public void loadSettings() {


        workTH.setText(settings.getStudyWorkH() + "");

        workTM.setText(settings.getStudyWorkM() + "");

        breakTH.setText(settings.getStudyBreakH() + "");

        breakTM.setText(settings.getStudyBreakM() + "");

        cycle.setText(settings.getStudyCycle() + "");


        showCT.selectedProperty().setValue(settings.isStudyShowT());


        showWT.selectedProperty().setValue(settings.isStudyShowWT());


        showCycles.selectedProperty().setValue(settings.isStudyShowCycles());


    }

    public void saveSetButton() {

        settings.setStudyWorkH(Integer.parseInt(workTH.getText()));

        settings.setStudyWorkM(Integer.parseInt(workTM.getText()));

        settings.setStudyBreakH(Integer.parseInt(breakTH.getText()));

        settings.setStudyBreakM(Integer.parseInt(breakTM.getText()));

        settings.setStudyCycle(Integer.parseInt(cycle.getText()));

        settings.setStudyShowT(showCT.isSelected());

        settings.setStudyShowCycles(showCycles.isSelected());

        settings.setStudyShowWT(showWT.isSelected());


        MainController.getInformationHandler().saveSettings(settings);

        Stage studySetStage = (Stage) save.getScene().getWindow();
        studySetStage.close();

    }

}
