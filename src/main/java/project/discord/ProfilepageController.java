package project.discord;



import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ProfilepageController {

    @FXML
    private Label email;

    @FXML
    private Label phonenumber;

    @FXML
    private Label username;

    public void setEmail(String email) {
        this.email.setText(email);
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber.setText(phonenumber);
    }

    public void setUsername(String username) {
        this.username.setText(username);
    }
}

