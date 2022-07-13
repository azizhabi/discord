package project.discord;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.ArrayList;

public class blockController {
    private ArrayList<String> blockFriends;
    @FXML
    private GridPane grid;

    @FXML
    private Label blockCounts;

    @FXML
    private ScrollPane scroll;

    @FXML
    private TextField search;

    public void initial(){
        for (int i = 0; i < blockFriends.size(); i++) {

            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("blockWindow.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                blockWindowController block = fxmlLoader.getController();
                block.setUsername(blockFriends.get(i));
                grid.add(anchorPane, 0,i);
                //GridPane.setMargin(anchorPane,new Insets(10));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setBlockFriends(ArrayList<String> blockFriends) {
        this.blockFriends = blockFriends;
    }
}
