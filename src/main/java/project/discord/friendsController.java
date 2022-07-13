package project.discord;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import org.json.JSONObject;
import projectServer.Accounts;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class friendsController implements Initializable {
    @FXML
    private AnchorPane mainPane;

    @FXML
    private Label addFriend;

    @FXML
    private Label all;

    @FXML
    private Label blocked;

    @FXML
    private Label online;

    @FXML
    private Label pending;


    @FXML
    void onAddFriendButtonClick(MouseEvent event) {
        setStyleToDefault();
        addFriend.setStyle("-fx-background-color: #36393F");
        addFriend.setStyle("-fx-border-color: transparent");
        addFriend.setStyle("-fx-text-fill:  #3BA55D");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("addFriend.fxml"));
        try {
            AnchorPane anchorPane = fxmlLoader.load();
            addFriendController add = fxmlLoader.getController();
            mainPane.getChildren().setAll(anchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onAllClick(MouseEvent event) {
        setStyleToDefault();
        all.setStyle("-fx-background-color: #454950");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("methode","FriendList");
        jsonObject.put("Username",controler.getUsername());
        conection.clientSockets.sendMassage(jsonObject);
        JSONObject jsonObject1 = conection.clientSockets.getMassage();
        ArrayList<String> allFriends = new ArrayList<>();
        for (Object str : jsonObject1.getJSONArray("Username")) {
            allFriends.add((String) str);
        }
        System.out.println(" the all size is "+ allFriends.size());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("all.fxml"));
        try {
            AnchorPane anchorPane = fxmlLoader.load();
            allController all = fxmlLoader.getController();
            all.setAllUsers(allFriends);
            all.initial();
            mainPane.getChildren().setAll(anchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void  onBlockedClick(MouseEvent event) {
        setStyleToDefault();
        blocked.setStyle("-fx-background-color: #454950");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("methode","BlockList");
        jsonObject.put("Username",controler.getUsername());
        conection.clientSockets.sendMassage(jsonObject);
        JSONObject jsonObject1 = conection.clientSockets.getMassage();
        ArrayList<String> blockFriends = new ArrayList<>();
        for (Object str : jsonObject1.getJSONArray("Username")) {
            blockFriends.add((String) str);
        }
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("block.fxml"));
        try {
            AnchorPane anchorPane = fxmlLoader.load();
            blockController block = fxmlLoader.getController();
            block.setBlockFriends(blockFriends);
            block.initial();
            mainPane.getChildren().setAll(anchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    @FXML
    void onOnlineClick(MouseEvent event) {
        setStyleToDefault();
        online.setStyle("-fx-background-color: #454950");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("methode","OnlineList");
        jsonObject.put("Username",controler.getUsername());
        conection.clientSockets.sendMassage(jsonObject);
        JSONObject jsonObject2 = conection.clientSockets.getMassage();
        ArrayList<String> onlineFriends = new ArrayList<>();
        for (Object str : jsonObject2.getJSONArray("Username")) {
            onlineFriends.add((String) str);
        }
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("online.fxml"));
        try {
            AnchorPane anchorPane = fxmlLoader.load();
            onlineController online = fxmlLoader.getController();
            online.setOnlineUsers(onlineFriends);
            online.initial();
            mainPane.getChildren().setAll(anchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void onPendingClick(MouseEvent event) {
        setStyleToDefault();
        pending.setStyle("-fx-background-color: #454950");
        ArrayList<String> users = new ArrayList<>();
        JSONObject json = new JSONObject();
        json.put("methode","ShowRequest");
        json.put("Username",controler.getUsername());
        conection.clientSockets.sendMassage(json);
        JSONObject jsonObject = conection.clientSockets.getMassage();
        ArrayList<String> request = new ArrayList<>();
        for (Object str : jsonObject.getJSONArray("Username")) {
            request.add((String) str);
        }
        System.out.println("the size in friends: "+request.size());
        pendingController.setPending(request);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("pending.fxml"));
        try {
            AnchorPane anchorPane = fxmlLoader.load();
            pendingController pending = fxmlLoader.getController();
            mainPane.getChildren().setAll(anchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        switchToOnline();
    }

    public void switchToOnline(){
        setStyleToDefault();
        online.setStyle("-fx-background-color: #454950");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("methode","OnlineList");
        jsonObject.put("Username",controler.getUsername());
        conection.clientSockets.sendMassage(jsonObject);
        JSONObject jsonObject2 = conection.clientSockets.getMassage();
        ArrayList<String> onlineFriends = new ArrayList<>();
        for (Object str : jsonObject2.getJSONArray("Username")) {
            onlineFriends.add((String) str);
        }
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("online.fxml"));
        try {
            AnchorPane anchorPane = fxmlLoader.load();
            onlineController online = fxmlLoader.getController();
            System.out.println("alisehfa rajimi");
            online.setOnlineUsers(onlineFriends);
            online.initial();
            mainPane.getChildren().setAll(anchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void setStyleToDefault(){
        online.setStyle("-fx-background-color: #36393F");
        blocked.setStyle("-fx-background-color:  #36393F");
        pending.setStyle("-fx-background-color:  #36393F");
        all.setStyle("-fx-background-color:  #36393F");
        addFriend.setStyle("-fx-background-color: #3BA55D");
        addFriend.setTextFill(Color.WHITE);

    }
}
