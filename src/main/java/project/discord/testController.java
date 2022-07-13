package project.discord;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class testController implements Initializable {
    ArrayList<User> users = new ArrayList<>();

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        User u1 = new User("ali", "shefa");
        User u2 = new User("ali", "jan");
        User u3 = new User("eid", "mohammad");
        User u4 = new User("barat", "ali");
        User u5 = new User("azizullah", "habibi");
        users.add(u1);
        users.add(u2);
        users.add(u3);
        users.add(u4);
        users.add(u5);
        int row = 0;
        int col = 0;
        for (int i = 0; i < users.size(); i++) {

            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("user.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                userController user = fxmlLoader.getController();
                user.setLname(users.get(i).getlName());
                user.setName(users.get(i).getName());
                user.setPhoto();
                grid.add(anchorPane, row,col++);
                GridPane.setMargin(anchorPane,new Insets(10));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
