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

public class onlineController {
    private int count = 0;
    private ArrayList<String> onlineUsers;
    @FXML
    private GridPane grid;

    @FXML
    private Label onlineCounts;

    @FXML
    private ScrollPane scroll;

    @FXML
    private TextField search;

    public void initial() {
        System.out.println("online");
        for (int i = 0; i < onlineUsers.size(); i++) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("allWindow.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                allWindowController alw = fxmlLoader.getController();
                alw.setUsername(onlineUsers.get(i));
                grid.add(anchorPane, 0,i);
            //    GridPane.setMargin(anchorPane,new Insets(10));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setOnlineUsers(ArrayList<String> onlineUsers) {
        this.onlineUsers = onlineUsers;
        count = onlineUsers.size();
        onlineCounts.setText(""+count);
    }

    public  ArrayList<String> getOnlineUsers() {
        return onlineUsers;
    }


}
