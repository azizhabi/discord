package project.discord;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import org.json.JSONObject;

public class blockWindowController {

    @FXML
    private Circle circle;

    @FXML
    private Button unblock;

    @FXML
    private Label username;

    @FXML
    void onUnblockButtonClick(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("methode","UnBlock");
        jsonObject.put("Username",controler.getUsername());
        jsonObject.put("FriendName",this.username);
        conection.clientSockets.sendMassage(jsonObject);

    }

    public void setUsername(String username) {
        this.username.setText(username);
    }
}
