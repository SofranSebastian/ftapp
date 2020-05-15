module com.fta {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    opens com.fta to javafx.fxml;
    exports com.fta;
}