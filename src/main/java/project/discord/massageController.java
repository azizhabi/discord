package project.discord;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

public class massageController {

    @FXML
    private Circle circle;

    @FXML
    private Label massage;

    @FXML
    private Label username;

    public void setUsername(String Username){
        username.setText(Username);
    }
    public void setMessage(String message){
        massage.setText(message);
    }

}
