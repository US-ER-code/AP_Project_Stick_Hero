module com.example.ap_project_stick_hero {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ap_project_stick_hero to javafx.fxml;
    exports com.example.ap_project_stick_hero;
}