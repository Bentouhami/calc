module com.javafx.calc.mvc {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.javafx.calc.mvc to javafx.fxml;
    exports com.javafx.calc.mvc;
}
