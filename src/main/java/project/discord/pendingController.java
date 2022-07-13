package project.discord;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class pendingController implements Initializable {
    static ArrayList<String> users;
    @FXML
    private GridPane grid;

    @FXML
    private Label pendingCount;

    @FXML
    private ScrollPane scroll;

    @FXML
    private TextField search;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pendingCount.setText(""+users.size());

        for ( int i = 0; i < users.size(); i++) {
            try {
                System.out.println(" here");
                FXMLLoader fxmlLoader = new FXMLLoader();

                fxmlLoader.setLocation(getClass().getResource("pendingWindow.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                pendingWindowController pending = fxmlLoader.getController();
                pending.setUsername(users.get(i));
                pending.initial();
                grid.add(anchorPane, 0,i);
                //GridPane.setMargin(anchorPane,new Insets(10));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void setPending(ArrayList<String> users){
        pendingController.users = users;
    }
}