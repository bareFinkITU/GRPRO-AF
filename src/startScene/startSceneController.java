package startScene;

import UserMVC.Profiles;
import UserMVC.Users;
import controller.ContentController;
import controller.SuperController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Content;
import model.Movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class startSceneController {

    @FXML
    private FlowPane startSceneFP;

    @FXML
    private GridPane gp;

    @FXML
    private BorderPane myProfileBP;

    @FXML
    private TextField startSceneSearchField;

    @FXML
    private Button startSceneLogOutButton;

    @FXML
    private Button startSceneMyProfileButton;

    @FXML
    private Label startSceneSelectedUserLabel;

    @FXML
    private MenuButton startSceneChangeProfile;

    private SuperController sC = new SuperController();

    private ContentController cC = ContentController.getInstanceOf();

    private ArrayList<Content> allContent = cC.getContent();

    private Users brugere = Users.getInstanceOf();

    public startSceneController() throws IOException {
    }


    public void searchByTitle() throws IOException {
        cC.resetContentSort();
        ArrayList<Content> searchByTitleContent =  cC.searchByTitle(startSceneSearchField.getText());
        cC.drawContentList(searchByTitleContent, startSceneFP);
        System.out.println(startSceneSearchField.getText());
    }

    public void homeClicked() throws IOException {
        cC.resetContentSort();
        cC.drawContentList(allContent,startSceneFP);
    }

    public void moviesClicked() throws IOException {
        cC.resetContentSort();
        ArrayList<Content> searchForMovies = cC.searchForMovies();
        cC.drawContentList(searchForMovies,startSceneFP);
        System.out.println("Now it's only movies!");
    }

    public void showsClicked() throws IOException {
        cC.resetContentSort();
        ArrayList<Content> searchForShows = cC.searchForShows();
        cC.drawContentList(searchForShows,startSceneFP);
        System.out.println("Now it's only shows!");
    }



    public void myProfileClicked(){

        List<Content> favorites = brugere.getSelectedUser().getSelectedProfile().getFavorites();
        cC.drawContentList(favorites,startSceneFP);

    }

    public void logOutClicked(){
        sC.goToLogIn(startSceneLogOutButton);
    }


    public void setProfiles(){
        for (Profiles p : brugere.getSelectedUser().getProfiles()) {
            MenuItem newItem = new MenuItem(p.getName());
           // startSceneChangeProfile.
        }
    }

    public void addGenres(){

    }

    public void changeProfileClicked(){
        for(Profiles p : brugere.getSelectedUser().getProfiles()){
            System.out.println(p.getName());
        }
    }

    public void initialize() throws IOException {
        startSceneSelectedUserLabel.setText("Selected profile: " + brugere.getSelectedUser().getSelectedProfile().getName());
        cC.resetContentSort();
        cC.drawContentList(allContent,startSceneFP);
    }




}
