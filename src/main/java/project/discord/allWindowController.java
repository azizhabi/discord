package project.discord;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class allWindowController {

    @FXML
    private Circle circle;

    @FXML
    private AnchorPane mainpane;

    @FXML
    private Button message;

    @FXML
    private Button more;

    @FXML
    private Label username;

    @FXML
    void onMessageButtonClick(ActionEvent event) throws IOException {
        grouppageController.setUsername(this.username.getText());



        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("grouppage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onMoreButtonClick(ActionEvent event) {

    }

    public void setUsername(String username){
        this.username.setText(username);
    }

}
