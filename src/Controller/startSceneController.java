package Controller;

import TODO_CHANGE_MY_NAME.Profiles;
import Model.MediaModel;
import Model.UserModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import TODO_CHANGE_MY_NAME.Media;
import View.CreateProfileBox;

import java.util.ArrayList;
import java.util.List;

public class startSceneController {
    @FXML   private FlowPane   startSceneFP;
    @FXML   private TextField  startSceneSearchField;
    @FXML   private Button     startSceneLogOutButton;
    @FXML   private MenuButton startSceneChangeProfile;
    @FXML   private MenuButton startSceneGenreMenu;
    @FXML   private Slider     startSceneRatingBar;
    @FXML   private Label      startSceneRatingLabel;

    private MediaModel mediaModel = MediaModel.getInstanceOf();
    private ArrayList<Media> allMedia = mediaModel.getMedia();
    private UserModel        userModel = UserModel.getInstanceOf();
    private String           selectedGenre;

    private boolean moviesClicked = false;
    private boolean showsClicked  = false;
    private boolean genreSearch   = false;
    private boolean titleSearch   = false;
    private boolean ratingSearch  = false;

    public startSceneController() {
    }

    public void homeClicked() {
        moviesClicked   = false;
        showsClicked    = false;
        genreSearch     = false;
        titleSearch     = false;
        ratingSearch    = false;

        startSceneSearchField.clear();
        mediaModel.resetMediaSort();
        mediaModel.drawMediaList(allMedia, startSceneFP);
        startSceneGenreMenu.setText("Genres");
        startSceneRatingBar.setValue(0);
    }

    public void searchChecker() {
        mediaModel.resetMediaSort();
        if (moviesClicked) mediaModel.searchForMovies();
        if (showsClicked) mediaModel.searchForShows();
        if (titleSearch) mediaModel.searchByTitle((startSceneSearchField.getText()));
        if (genreSearch) mediaModel.searchByGenre(selectedGenre);
        if (ratingSearch) mediaModel.searchByRating(startSceneRatingBar.getValue());
    }

    public void searchByTitle() {
        titleSearch = true;
        searchChecker();
        //TODO unchecked assignment arraylist < ? >
        mediaModel.drawMediaList(mediaModel.searchByTitle(startSceneSearchField.getText()), startSceneFP);
        System.out.println(startSceneSearchField.getText());
    }

    public void moviesClicked() {
        moviesClicked = true;
        showsClicked = false;
        searchChecker();
        mediaModel.drawMediaList(mediaModel.searchForMovies(), startSceneFP);
    }

    public void showsClicked() {
        showsClicked = true;
        moviesClicked = false;
        searchChecker();
        mediaModel.drawMediaList(mediaModel.searchForShows(), startSceneFP);
    }

    private void searchByGenre(String s) {
        genreSearch = true;
        selectedGenre = s;
        searchChecker();
        mediaModel.drawMediaList(mediaModel.searchByGenre(s),startSceneFP);
        startSceneGenreMenu.setText(s);
    }

    private void ratingBarChanged() {
        ratingSearch = true;
        searchChecker();
        mediaModel.drawMediaList(mediaModel.searchByRating(startSceneRatingBar.getValue()),startSceneFP);
        //TODO virker den?
        String rating = ("" + startSceneRatingBar.getValue()).substring(0,3);
        startSceneRatingLabel.setText("Search by rating: " + rating);
    }

    public void myProfileClicked() {
        List<Media> favorites = userModel.getSelectedUser().getSelectedProfile().getFavorites();
        mediaModel.drawMediaList(favorites, startSceneFP);
    }

    public void logOutClicked() {
        new SuperController().goToLogIn(startSceneLogOutButton);
    }


    public void setProfiles() {
        startSceneChangeProfile.getItems().clear();
        MenuItem addNewProfile = new MenuItem("Add new profile");
        addNewProfile.setOnAction(e -> addProfileClicked());
        startSceneChangeProfile.getItems().addAll(addNewProfile);
        for (Profiles p : userModel.getSelectedUser().getProfiles()) {
            MenuItem newItem = new MenuItem(p.getName());
            newItem.setOnAction(e -> {
                userModel.getSelectedUser().setSelectedProfile(p);
                initialize();
            });
            startSceneChangeProfile.getItems().addAll(newItem);
        }
    }


    public void addGenres(){
        startSceneGenreMenu.getItems().clear();
        for (String s : mediaModel.getGenres()){
            MenuItem newMenuItem = new MenuItem(s);
            newMenuItem.setOnAction(e -> searchByGenre(s));
            startSceneGenreMenu.getItems().addAll(newMenuItem);
        }
    }



    public void addProfileClicked() {
        if (new CreateProfileBox().display()) {
            setProfiles();
            initialize();
        }
    }

    public void initialize() {
        startSceneChangeProfile.setText(userModel.getSelectedUser().getSelectedProfile().getName());
        mediaModel.resetMediaSort();
        mediaModel.drawMediaList(allMedia, startSceneFP);
        startSceneRatingLabel.setText("Search by rating");
        startSceneGenreMenu.setText("Genres");
        addGenres();
        setProfiles();
        startSceneRatingBar.valueProperty().addListener((observable, oldValue, newValue) -> {
            ratingBarChanged();
        });
    }


}
