package myProfile;

import UserMVC.Users;
import controller.ContentController;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import model.Content;

import java.io.IOException;
import java.util.List;

public class MyProfileController {

    @FXML
    private FlowPane myProfileFP;

    private ContentController cC = ContentController.getInstanceOf();
    private Users brugere = Users.getInstanceOf();

    public MyProfileController() throws IOException {
    }

    public void initialize(){
        List<Content> favorites = brugere.getSelectedUser().getFavorites();
        cC.drawContentList(favorites,myProfileFP);
    }
}
