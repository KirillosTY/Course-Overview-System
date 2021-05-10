package cos.ui;

import cos.controls.MainController;
import cos.controls.Settings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    private TextField workTH, workTM, breakTH, breakTM;

    @FXML
    private TextField cycle;

    @FXML
    private CheckBox showCT, showWT, showCycles;


    @FXML
    public void initialize() {
        settings = MainController.getSettings();
        loadSettings();
        textFNumSetupHour(workTH);
        textFNumSetupMin(workTM);
        textFNumSetupHour(breakTH);
        textFNumSetupMin(breakTM);
        textNumSetCycle();
    }


    @FXML
    public void textNumSetCycle(){
        cycle.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s,
                                String input) {
                if (!input.isEmpty()) {
                    if (!input.matches("\\d*")) {
                        cycle.setText(input.replaceAll("[^\\d]", ""));
                    }
                    if (input.length() > 1) {
                        cycle.setText(input.replaceAll("[^\\d]", "").substring(0, 1));
                    }
                }
            }
        });
    }


    @FXML
    public void textFNumSetupHour(TextField setH) {
        setH.textProperty().addListener((observableValue, s, input) -> {
            if (!input.isEmpty()) {
                if (!input.matches("\\d*")) {
                    setH.setText(input.replaceAll("[^\\d]", ""));
                }
                if (input.length() > 2) {
                    setH.setText(input.replaceAll("[^\\d]", "").substring(0, 2));
                }
            }
        });
    }

    @FXML
    public void textFNumSetupMin(TextField setM){

        setM.textProperty().addListener((observableValue, s, input) -> {
            if (!input.isEmpty()) {
                if (!input.matches("\\d*")) {
                    setM.setText(input.replaceAll("[^\\d]", ""));
                }
                if(input.length() > 2){
                    setM.setText(input.replaceAll("[^\\d]", "").substring(0,2));
                }
            }
        });

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

    public void isAcceptableSet(){

        if(workTH.getText().isEmpty()){
            workTH.setText("00");

        }
        if(workTM.getText().isEmpty()){
            workTM.setText("45");
        }
        if(breakTH.getText().isEmpty()){
            breakTH.setText("00");
        }
        if(breakTM.getText().isEmpty()){
            breakTM.setText("15");
        }
        if(cycle.getText().isEmpty()){
            cycle.setText("7");
        }

    }

    public boolean checkWithinParameters(){

        boolean check = true;
        if(Integer.parseInt(workTH.getText()) > 23){
            workTH.setText("23");
            check = false;
        }

        if(Integer.parseInt(breakTH.getText()) > 23){
            breakTH.setText("23");
            check = false;
        }


        if(Integer.parseInt(workTM.getText()) > 59){
            workTM.setText("59");
            check = false;
        }

        if(Integer.parseInt(breakTM.getText()) > 59){
            breakTM.setText("59");
            check = false;
        }
        return check;
    }


    public void saveSetButton() {
        isAcceptableSet();
        checkWithinParameters();
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
