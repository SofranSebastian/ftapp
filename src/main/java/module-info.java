module com.fta {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.fta to javafx.fxml;
    exports com.fta;
}