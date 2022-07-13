package project.discord;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class userController {

    @FXML
    private Label lname;

    @FXML
    private Label name;

    @FXML
    private Circle photo;

    public void setLname(String lname) {
        this.lname.setText(lname);
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public void setPhoto() throws FileNotFoundException {
        Image image = new Image(new FileInputStream("src/pics/discordOnline.png"));
        photo.setFill(new ImagePattern(image));
    }
}