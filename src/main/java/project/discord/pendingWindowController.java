package project.discord;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import org.json.JSONObject;

import java.net.URL;
import java.util.ResourceBundle;

public class pendingWindowController {

    @FXML
    private Circle circle;

    @FXML
    private Button confirm;

    @FXML
    private Button delete;

    @FXML
    private  Label username;
    private String requestedUser;

    @FXML
    void onConfirmButtonClick(ActionEvent event) {
        JSONObject json = new JSONObject();
        json.put("methode", "AnswerRequest");
        json.put("Username",controler.getUsername());
        json.put("FriendName",this.username.getText());
        conection.clientSockets.sendMassage(json);

    }

    @FXML
    void onDeleteButtonClick(ActionEvent event) {

        JSONObject json = new JSONObject();
        json.put("methode", "DeleteRequest");
        json.put("Username",controler.getUsername());
        json.put("DeleteName",this.username.getText());
        conection.clientSockets.sendMassage(json);

    }

    public void setUsername(String requestedUser){
        this.requestedUser = requestedUser;
    }


    public void initial(){
        username.setText(requestedUser);
    }


}
