module courseoverviewsystem.cos {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;

    opens courseoverviewsystem.cos to javafx.fxml;
    exports courseoverviewsystem.cos;
}
