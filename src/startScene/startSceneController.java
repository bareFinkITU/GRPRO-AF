package startScene;

import UserMVC.Profiles;
import UserMVC.Users;
import controller.ContentController;
import controller.SuperController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Content;
import model.Movie;

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

    @FXML
    private VBox startSceneGenreVBox;

    private SuperController sC = new SuperController();

    private ContentController cC = ContentController.getInstanceOf();

    private ArrayList<Content> allContent = cC.getContent();

    private Users brugere = Users.getInstanceOf();

    private CreateProfileBox cPB = new CreateProfileBox();

    public startSceneController() {
    }


    public void searchByTitle() {
        cC.resetContentSort();
        ArrayList<Content> searchByTitleContent =  cC.searchByTitle(startSceneSearchField.getText());
        cC.drawContentList(searchByTitleContent, startSceneFP);
        System.out.println(startSceneSearchField.getText());
    }

    public void homeClicked() {
        cC.resetContentSort();
        cC.drawContentList(allContent,startSceneFP);
    }

    public void moviesClicked() {
        cC.resetContentSort();
        ArrayList<Content> searchForMovies = cC.searchForMovies();
        cC.drawContentList(searchForMovies,startSceneFP);
        System.out.println("Now it's only movies!");
    }

    public void showsClicked()  {
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
        startSceneChangeProfile.getItems().clear();
        MenuItem addNewProfile = new MenuItem("Add new profile");
        addNewProfile.setOnAction(e -> addProfileClicked());
        startSceneChangeProfile.getItems().addAll(addNewProfile);
        for (Profiles p : brugere.getSelectedUser().getProfiles()) {
            MenuItem newItem = new MenuItem(p.getName());
            newItem.setOnAction(e -> {
                brugere.getSelectedUser().setSelectedProfile(p);
                initialize();
            });
            startSceneChangeProfile.getItems().addAll(newItem);
        }
    }

    public void addGenres(){
        //startSceneGenreVBox.getChildren().remove(2, cC.getGenres().size()-1);
        for (String s : cC.getGenres()){
            CheckBox newCheckBox = new CheckBox(s);
            newCheckBox.setPadding(new Insets(2,0,2,5));
            newCheckBox.setPrefWidth(175);
            startSceneGenreVBox.getChildren().addAll(newCheckBox);
        }
    }

    public void addProfileClicked() {
        if (cPB.display() == true){
            setProfiles();
            initialize();
        }
    }

    public void initialize() {
        startSceneChangeProfile.setText(brugere.getSelectedUser().getSelectedProfile().getName());
        cC.resetContentSort();
        cC.drawContentList(allContent,startSceneFP);
        addGenres();
        setProfiles();
    }




}
