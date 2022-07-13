module project.discord {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens project.discord to javafx.fxml;
    exports project.discord;
}