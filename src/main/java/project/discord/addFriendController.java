package project.discord;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.json.JSONObject;

public class addFriendController {

    @FXML
    private Button request;

    @FXML
    private TextField username;

    @FXML
    /**
     * add a friend
     */
    void onRequestButtonClick(ActionEvent event) {
        String friend = username.getText();
        JSONObject json = new JSONObject();
        json.put("methode","FriendRequest");
        json.put("Username",controler.getUsername());
        json.put("FriendName",this.username.getText());
        conection.clientSockets.sendMassage(json);
        System.out.println("OK");
        JSONObject json1 = conection.clientSockets.getMassage();
        if (json1.getString("Answer").equals("True")) {
            System.out.println("YES");
        }

    }

}