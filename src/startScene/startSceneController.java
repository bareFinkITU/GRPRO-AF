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

    private boolean moviesClicked = false;
    private boolean showsClicked = false;
    private boolean genreSearch = false;
    private boolean titleSearch = false;
    private boolean ratingSearch = false;

    public startSceneController() {
    }

    public void homeClicked() {
        moviesClicked = false;
        showsClicked = false;
        genreSearch = false;
        titleSearch = false;
        ratingSearch = false;

        startSceneSearchField.clear();
        cC.resetContentSort();
        cC.drawContentList(allContent, startSceneFP);
    }

    public void searchChecker() {
        cC.resetContentSort();
        if (moviesClicked) cC.searchForMovies();
        if (showsClicked) cC.searchForShows();
        if (titleSearch) cC.searchByTitle((startSceneSearchField.getText()));

        /*
        if (genreSearch) cC.searchByGenre(*//*INDSÆT USER INPUT HER*//*);
        if (ratingSearch) cC.searchByRating(*//*INDSÆT USER INPUT HER*//*);
        */
    }

    public void searchByTitle() {
        titleSearch = true;
        searchChecker();
        cC.drawContentList(cC.searchByTitle(startSceneSearchField.getText()), startSceneFP);
        System.out.println(startSceneSearchField.getText());
    }

    public void moviesClicked() {
        moviesClicked = true;
        showsClicked = false;
        searchChecker();
        cC.drawContentList(cC.searchForMovies(), startSceneFP);
        System.out.println("movies now true");
    }

    public void showsClicked() {
        showsClicked = true;
        moviesClicked = false;
        searchChecker();
        cC.drawContentList(cC.searchForShows(), startSceneFP);
        System.out.println("shows now true");
    }


    public void myProfileClicked() {

        List<Content> favorites = brugere.getSelectedUser().getSelectedProfile().getFavorites();
        cC.drawContentList(favorites, startSceneFP);

    }

    public void logOutClicked() {
        sC.goToLogIn(startSceneLogOutButton);
    }


    public void setProfiles() {
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

    public void addGenres() {
        //startSceneGenreVBox.getChildren().remove(2, cC.getGenres().size()-1);
        for (String s : cC.getGenres()) {
            CheckBox newCheckBox = new CheckBox(s);
            newCheckBox.setPadding(new Insets(2, 0, 2, 5));
            newCheckBox.setPrefWidth(175);
            startSceneGenreVBox.getChildren().addAll(newCheckBox);
        }
    }

    public void addProfileClicked() {
        if (cPB.display() == true) {
            setProfiles();
            initialize();
        }
    }

    public void initialize() {
        startSceneChangeProfile.setText(brugere.getSelectedUser().getSelectedProfile().getName());
        cC.resetContentSort();
        cC.drawContentList(allContent, startSceneFP);
        addGenres();
        setProfiles();
    }


}
