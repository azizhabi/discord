package project.discord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class signUpController implements Initializable {
    @FXML
    private Label labelEmail;

    @FXML
    private Label labelPassword;

    @FXML
    private Label labelUsername;

    @FXML
    private ComboBox<?> day;

    @FXML
    private TextField email;

    @FXML
    private ComboBox<?> month;

    @FXML
    private TextField password;

    @FXML
    private Hyperlink login;

    @FXML
    private Button signUp;

    @FXML
    private TextField username;

    @FXML
    private ComboBox<?> year;

    @FXML
    void onLoginLinkClick(ActionEvent event) throws IOException {
        login.getScene().getWindow().hide();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onSignupButtonCLick(ActionEvent event) {
        JSONObject jsonObject = new JSONObject();
        JSONObject json1 = new JSONObject();
        json1.put("methode","CheckUsername");
        json1.put("Username",username.getText());
        conection.clientSockets.sendMassage(json1);
        JSONObject jsonObject1 = conection.clientSockets.getMassage();
        if (jsonObject1.getString("Answer").equals("False")) {
            username.setText("");
            labelUsername.setVisible(true);
        }else {
            if (username.getText().isEmpty()) {
                labelUsername.setVisible(true);
            }
            if (password.getText().isEmpty()) {
                labelPassword.setVisible(true);
            }
            if (email.getText().isEmpty()) {
                labelEmail.setVisible(true);
            } else {
                jsonObject.put("Username", username.getText());
                jsonObject.put("password", password.getText());
                jsonObject.put("Email", email.getText());
                jsonObject.put("State","Online");
                try {
                    conection.mainsystem.SignIn(jsonObject);
                    if (conection.mainsystem.clientsockets.getMassage().getString("Answer").equals("True")) {
                        email.getScene().getWindow().hide();
                        controler.setUsername(username.getText());
                        //  mainController.setUsername(username.getText());
                        Stage stage = new Stage();
                        //FXMLLoader fxmlLoader = FXMLLoader.load(getClass().getResource("testScroll.fxml"));
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());
                        stage.setScene(scene);
                        stage.show();

                    }
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelEmail.setVisible(false);
        labelPassword.setVisible(false);
        labelUsername.setVisible(false);
    }
}
