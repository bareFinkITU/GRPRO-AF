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
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import model.Content;
import model.Movie;

import java.util.ArrayList;
import java.util.List;


public class startSceneController {

    @FXML
    private FlowPane startSceneFP;


    @FXML
    private TextField startSceneSearchField;

    @FXML
    private Button startSceneLogOutButton;

    @FXML
    private MenuButton startSceneChangeProfile;

    @FXML
    private VBox startSceneGenreVBox;

    @FXML
    private Slider startSceneRatingBar;

    @FXML
    private Label startSceneRatingLabel;

    private SuperController sC = new SuperController();
    private ContentController cC = ContentController.getInstanceOf();
    private ArrayList<Content> allContent = cC.getContent();
    private Users brugere = Users.getInstanceOf();
    private CreateProfileBox cPB = new CreateProfileBox();
    private String selectedGenre;

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


        if (genreSearch) cC.searchByGenre(selectedGenre);
        if (ratingSearch) cC.searchByRating(startSceneRatingBar.getValue());
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

    private void searchByGenre(String s) {
        genreSearch = true;
        selectedGenre = s;
        searchChecker();
        cC.drawContentList(cC.searchByGenre(s),startSceneFP);
    }

    private void ratingBarChanged() {
        ratingSearch = true;
        searchChecker();
        cC.drawContentList(cC.searchByRating(startSceneRatingBar.getValue()),startSceneFP);
        String rating = "" + startSceneRatingBar.getValue();
        rating = rating.substring(0,3);
        startSceneRatingLabel.setText("Search rating: " + rating);
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


    public void addGenres(){
        if (startSceneGenreVBox.getChildren().size() > 3) {
            startSceneGenreVBox.getChildren().remove(3, startSceneGenreVBox.getChildren().size());
        }
        for (String s : cC.getGenres()){
            CheckBox newCheckBox = new CheckBox(s);
            newCheckBox.setStyle("-fx-text-fill:  #ffffff");
            newCheckBox.setPadding(new Insets(2,0,2,5));
            newCheckBox.setPrefWidth(175);
            newCheckBox.setOnAction(e -> searchByGenre(s));
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
        startSceneRatingBar.setOnMouseClicked(e -> ratingBarChanged());
        startSceneRatingLabel.setText("Rating search");
        addGenres();
        setProfiles();
    }


}
