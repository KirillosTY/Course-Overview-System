module cos.ui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;

    opens cos.ui to javafx.fxml;
    exports cos.ui;

}
