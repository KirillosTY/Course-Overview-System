package courseoverviewsystem.cos;

import Controls.MainController;
import Controls.Settings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StudySettings {

    Settings settings;

    @FXML
    private Button save;

    @FXML
    private TextField workTM;

    @FXML
    private TextField workTH;

    @FXML
    private TextField breakTM;

    @FXML
    private TextField breakTH;

    @FXML
    private TextField cycle;

    @FXML
    private CheckBox showCT;

    @FXML
    private CheckBox showWT;

    @FXML
    private CheckBox showBT;

    @FXML
    private TextArea msg;


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

        msg.setText(settings.getStudyMsg());

        showCT.selectedProperty().setValue(settings.isStudyShowT());


        showWT.selectedProperty().setValue(settings.isStudyShowWT());


        showBT.selectedProperty().setValue(settings.isStudyShowBT());


    }

    public void saveSetButton() {

        settings.setStudyWorkH(Integer.parseInt(workTH.getText()));

        settings.setStudyWorkM(Integer.parseInt(workTM.getText()));

        settings.setStudyBreakH(Integer.parseInt(breakTH.getText()));

        settings.setStudyBreakM(Integer.parseInt(breakTM.getText()));

        settings.setStudyCycle(Integer.parseInt(cycle.getText()));

        settings.setStudyShowT(showCT.isSelected());

        settings.setStudyShowBT(showBT.isSelected());

        settings.setStudyShowWT(showWT.isSelected());

        settings.setStudyMsg(msg.getText());

        MainController.getInformationHandler().saveSettings(settings);

        Stage studySetStage = (Stage) save.getScene().getWindow();
        studySetStage.close();

    }

}
