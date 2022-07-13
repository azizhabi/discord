package project.discord;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.IOException;

import static project.discord.conection.mainsystem;

public class loginController {
    Stage stage;
    @FXML
    private Button login;

    @FXML
    private PasswordField password;

    @FXML
    private Hyperlink register;

    @FXML
    private TextField userName;

    @FXML
    void onLoginButtonClick(ActionEvent event) throws IOException, InterruptedException {

        String username = this.userName.getText();
        String password = this.password.getText();
        mainsystem.SignUp(username,password);
        JSONObject json = mainsystem.clientsockets.getMassage();
        System.out.println(json.getString("Answer").equals("True"));
        if (json.getString("Answer").equals("True")) {
            register.getScene().getWindow().hide();
            controler.setUsername(username);

            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();

        }
    }

    @FXML
    void onRegisterLinkClick(ActionEvent event) throws IOException {
        register.getScene().getWindow().hide();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("signUp.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

}
