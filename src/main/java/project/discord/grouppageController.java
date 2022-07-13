package project.discord;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.json.JSONObject;
import org.json.JSONPropertyName;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class grouppageController implements Initializable {
    private static String username;
    @FXML
    private VBox massages;

    @FXML
    private Label groupname;

    @FXML
    private TextField massage;

    @FXML
    private Button refresh;

    @FXML
    private Button sendmassage;

    @FXML
    void onRefreshbuttonclick(ActionEvent event) throws IOException {
        JSONObject jsonObject = new JSONObject();
        System.out.println("I am in refresh");
        jsonObject.put("methode", "getmassage");
        jsonObject.put("Username", controler.getUsername());
        jsonObject.put("FriendName", grouppageController.getUsername());
        conection.clientSockets.sendMassage(jsonObject);
        JSONObject jsonObject1 = conection.clientSockets.getMassage();
        System.out.println("i am true");
        ArrayList<String> allFriends = new ArrayList<>();
        ArrayList<String> strings = new ArrayList<>();
        for (Object str : jsonObject1.getJSONArray("Username")) {
            strings.add((String) str);
        }
            for (Object str : jsonObject1.getJSONArray("massage")) {
                allFriends.add((String) str);
                System.out.println("I aded");
            }
            for (int i = 0; i<strings.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("massage.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                System.out.println("I shown");
                massageController mes = fxmlLoader.getController();
                mes.setMessage(allFriends.get(i));
                mes.setUsername(strings.get(i));
                massages.getChildren().add(anchorPane);
            }
        }


    @FXML
    void onSendbuttonClick(ActionEvent event) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("methode", "SendMassage");
        jsonObject.put("Username", this.getUsername());
        jsonObject.put("FriendName", controler.getUsername());
        jsonObject.put("massage", this.massage.getText());
        conection.clientSockets.sendMassage(jsonObject);
        JSONObject jsonObject1 = conection.clientSockets.getMassage();
        if (jsonObject1.getString("Answer").equals("True")) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("massage.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            massageController mes = fxmlLoader.getController();
            mes.setMessage(this.massage.getText());
            mes.setUsername(controler.getUsername());
            massages.getChildren().add(anchorPane);

        }
    }
public static void setUsername(String user) {
        grouppageController.username = user;
        System.out.println("I seted");
}
public static String getUsername() {
    System.out.println("you got me");
        return grouppageController.username;
}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        JSONObject jsonObject = new JSONObject();
        System.out.println("I am in refresh");
        jsonObject.put("methode", "getmassage");
        jsonObject.put("Username", controler.getUsername());
        jsonObject.put("FriendName", grouppageController.getUsername());
        conection.clientSockets.sendMassage(jsonObject);
        JSONObject jsonObject1 = conection.clientSockets.getMassage();
        System.out.println("i am true");
        ArrayList<String> allFriends = new ArrayList<>();
        ArrayList<String> strings = new ArrayList<>();
        for (Object str : jsonObject1.getJSONArray("Username")) {
            strings.add((String) str);
        }
        for (Object str : jsonObject1.getJSONArray("massage")) {
            allFriends.add((String) str);
            System.out.println("I aded");
        }
        for (int i = 0; i<strings.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("massage.fxml"));
            AnchorPane anchorPane = null;
            try {
                anchorPane = fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("I shown");
            massageController mes = fxmlLoader.getController();
            mes.setMessage(allFriends.get(i));
            mes.setUsername(strings.get(i));
            massages.getChildren().add(anchorPane);
        }

    }
}
