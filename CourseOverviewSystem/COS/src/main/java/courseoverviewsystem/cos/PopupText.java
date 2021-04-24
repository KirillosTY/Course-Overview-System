package courseoverviewsystem.cos;

import Controls.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class PopupText {

    @FXML
    private TextArea msg;

    @FXML
    private Label multiuse;

    @FXML
    public void initialize(){

        multiuse.setText(MainController.getPopupText()[0]);

        msg.setText(MainController.getPopupText()[1]);

    }

    public PopupText(){

    }


}
