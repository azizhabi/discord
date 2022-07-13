package project.discord;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;
import projectServer.Accounts;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class mainController implements Initializable {
    private static String user;
    private String email;
    private String username;
    private String phone;
    @FXML
    private  Label labelUsername;

    @FXML
    private Label add;

    @FXML
    private GridPane grid;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private ImageView profileBtn;

    @FXML
    private ScrollPane scroll;
//////
    @FXML
    private Label profile;
    /////
    @FXML
    private ImageView setting;

    @FXML
    void onAddClick(MouseEvent event) {

    }

    @FXML
    void onConversationClick(MouseEvent event) {

    }

    @FXML
    void onFriendsClick(MouseEvent event) {

    }

    @FXML
    void onProfileClick(MouseEvent event) throws IOException {
        System.out.println("hi2133");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("profilePage.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        ProfilepageController p = fxmlLoader.getController();
        System.out.println("hi2134");

        /////////////
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("methode","ShowProfile");
        jsonObject.put("Username",controler.getUsername());
        conection.clientSockets.sendMassage(jsonObject);
        System.out.println("hi again");
        JSONObject jsonObject1 = conection.clientSockets.getMassage();
        System.out.println("hi again and again");
        p.setPhonenumber(jsonObject1.getString("PhoneNumber"));
        p.setUsername(jsonObject1.getString("Username"));
        p.setEmail(jsonObject1.getString("EmailAddress"));
        /////////////
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onSettingButtonClick(MouseEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user = controler.getUsername();
        labelUsername.setText(user);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("friends.fxml"));
        try {
            System.out.println("how are you");
            AnchorPane anchorPane = fxmlLoader.load();
            friendsController sign = fxmlLoader.getController();
            mainPane.getChildren().setAll(anchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



}
