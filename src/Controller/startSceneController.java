package Controller;

import TODO_CHANGE_MY_NAME.Profile;
import Model.MediaModel;
import Model.UserModel;
import View.RemoveProfileBox;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import TODO_CHANGE_MY_NAME.Media;
import View.CreateProfileBox;

import java.util.ArrayList;

public class startSceneController {
    @FXML   private FlowPane   startSceneFP;
    @FXML   private TextField  startSceneSearchField;
    @FXML   private Button     startSceneLogOutButton;
    @FXML   private MenuButton startSceneChangeProfile;
    @FXML   private MenuButton startSceneGenreMenu;
    @FXML   private Slider     startSceneRatingBar;
    @FXML   private Label      startSceneRatingLabel;
    @FXML   private Slider     startSceneYearSearchBar;
    @FXML   private Label      startSceneyearSearchlabel;
    @FXML   private Button     startSceneMyProfileButton;
    @FXML   private Button     startSceneShowsButton;
    @FXML   private Button     startSceneMovieButton;
    @FXML   private Button     startSceneHomeButton;

    private MediaModel mediaModel = MediaModel.getInstanceOf();
    private ArrayList<Media> allMedia = mediaModel.getMedia();
    private UserModel        userModel = UserModel.getInstanceOf();
    private String           selectedGenre;
    private CreateProfileBox createProfileBox = new CreateProfileBox();
    private RemoveProfileBox removeProfileBox = new RemoveProfileBox();
    private SuperController superController = SuperController.getInstanceOf();

    private boolean genreSearch   = false;
    private boolean titleSearch   = false;
    private boolean ratingSearch  = false;
    private boolean yearSearch    = false;
    private boolean underAged     = false;

    public startSceneController() {
    }

    public void homeClicked() {
        startSceneHomeButton.setStyle("-fx-background-color: TRANSPERANT");
        superController.setMoviesClicked(false);
        superController.setShowsClicked(false);
        superController.setMyProfileList(false);
        genreSearch     = false;
        titleSearch     = false;
        ratingSearch    = false;
        yearSearch      = false;


        initialize();
    }

    public void searchChecker() {
        mediaModel.resetMediaSort();
        if (superController.isMoviesClicked())  mediaModel.searchForMovies();
        if (superController.isShowsClicked())   mediaModel.searchForShows();
        if (titleSearch)    mediaModel.searchByTitle((startSceneSearchField.getText()));
        if (genreSearch)    mediaModel.searchByGenre(selectedGenre);
        if (ratingSearch)   mediaModel.searchByRating(startSceneRatingBar.getValue());
        if (yearSearch)     mediaModel.searchByYear((int) Math.round(startSceneYearSearchBar.getValue()),2020);
        if (underAged)      mediaModel.searchByGenre("Family");
        if (superController.isMyProfileList())  mediaModel.searchInMyList(userModel.getSelectedUser().getSelectedProfile());
    }

    public void searchByTitle() {
        titleSearch = true;
        searchChecker();
        //TODO unchecked assignment arraylist
        mediaModel.drawMediaList(mediaModel.searchByTitle(startSceneSearchField.getText()), startSceneFP);
    }

    public void moviesClicked() {
        startSceneHomeButton.setStyle("-fx-background-color: TRANSPERANT");
        startSceneMovieButton.setStyle("-fx-background-color: D9D9D9");
        startSceneShowsButton.setStyle("-fx-background-color: TRANSPERANT");
        superController.setMoviesClicked(true);
        superController.setShowsClicked(false);
        searchChecker();
        mediaModel.drawMediaList(mediaModel.searchForMovies(), startSceneFP);
    }

    public void showsClicked() {
        startSceneHomeButton.setStyle("-fx-background-color: TRANSPERANT");
        startSceneShowsButton.setStyle("-fx-background-color: D9D9D9");
        startSceneMovieButton.setStyle("-fx-background-color: TRANSPERANT");
        superController.setMoviesClicked(false);
        superController.setShowsClicked(true);
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
        String rating = ("" + startSceneRatingBar.getValue()).substring(0,3);
        startSceneRatingLabel.setText("Search by rating: " + rating);
    }

    private void YearSearchBarChanged(){
        yearSearch = true;
        searchChecker();
        mediaModel.drawMediaList(mediaModel.searchByYear((int) Math.round(startSceneYearSearchBar.getValue()),2020),startSceneFP);
        String year = ("" + startSceneYearSearchBar.getValue()).substring(0,4);
        startSceneyearSearchlabel.setText("Search by year: " + year);
    }

    public void myProfileClicked() {
        homeClicked();
        startSceneHomeButton.setStyle("-fx-background-color: TRANSPERANT");
        startSceneMyProfileButton.setStyle("-fx-background-color: D9D9D9");
        superController.setMyProfileList(true);
        searchChecker();
        mediaModel.drawMediaList(mediaModel.searchInMyList(userModel.getSelectedUser().getSelectedProfile()), startSceneFP);
    }

    public void logOutClicked() {
        superController.goToLogIn(startSceneLogOutButton);
    }


    public void setProfiles() {
        startSceneChangeProfile.getItems().clear();
        MenuItem addNewProfile = new MenuItem("Add new profile");
        addNewProfile.setOnAction(e -> addProfileClicked());
        MenuItem removeProfile = new MenuItem("Remove a profile");
        removeProfile.setOnAction(e -> removeProfileClicked());
        startSceneChangeProfile.getItems().addAll(addNewProfile, removeProfile);
        //TODO flyt til UserModel
        for (Profile p : userModel.getSelectedUser().getProfiles()) {
            MenuItem newItem = new MenuItem();
            if (p.isUnderAged()){
                newItem.setText(p.getName() + " (child)");
            } else {
                newItem.setText(p.getName());
            }
            newItem.setOnAction(e -> {
                if (p != userModel.getSelectedUser().getSelectedProfile()) {
                    userModel.getSelectedUser().setSelectedProfile(p);
                    initialize();
                }
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

    public void removeProfileClicked(){
        if (removeProfileBox.display()){
            setProfiles();
            initialize();
        }
    }

    public void addProfileClicked() {
        if (createProfileBox.display()) {
            setProfiles();
            initialize();
        }
    }

    public void initialize() {
        if (userModel.getSelectedUser().getSelectedProfile().isUnderAged()){
            startSceneChangeProfile.setText(userModel.getSelectedUser().getSelectedProfile().getName() + " (child)");
        } else {
            startSceneChangeProfile.setText(userModel.getSelectedUser().getSelectedProfile().getName());
        }

        if (mediaModel.getSelectedMedia() == null) {
            mediaModel.resetMediaSort();
            startSceneSearchField.clear();

            startSceneRatingBar.setValue(0);
            startSceneYearSearchBar.setValue(1950);

            startSceneRatingLabel.setText("Search by rating");
            startSceneyearSearchlabel.setText("Search by year");

            startSceneHomeButton.setStyle("-fx-background-color: D9D9D9");
            startSceneMovieButton.setStyle("-fx-background-color: TRANSPERANT");
            startSceneShowsButton.setStyle("-fx-background-color: TRANSPERANT");
            startSceneMyProfileButton.setStyle("-fx-background-color: TRANSPERANT");

            startSceneGenreMenu.setText("Genres");
            if (userModel.getSelectedUser().getSelectedProfile().isUnderAged()){
                mediaModel.drawMediaList(mediaModel.searchByGenre("Family"),startSceneFP);
                underAged = true;
            } else {
                mediaModel.drawMediaList(allMedia, startSceneFP);
                underAged = false;
            }
        } else {
            if (superController.isMyProfileList()){
                myProfileClicked();
                mediaModel.drawMediaList(mediaModel.getMediaSort(),startSceneFP);
            } else{
                mediaModel.drawMediaList(mediaModel.getMediaSort(),startSceneFP);
            }
            if (superController.isMoviesClicked()){
                startSceneMovieButton.setStyle("-fx-background-color: D9D9D9");
            }
            if (superController.isShowsClicked()){
                startSceneShowsButton.setStyle("-fx-background-color: D9D9D9");
            }
            if (superController.isMyProfileList()){
                startSceneMyProfileButton.setStyle("-fx-background-color: D9D9D9");
            }
            mediaModel.setSelectedMedia(null);
        }

        addGenres();
        setProfiles();

        startSceneRatingBar.valueProperty().addListener((observable, oldValue, newValue) -> {
            ratingBarChanged();
        });

        startSceneYearSearchBar.valueProperty().addListener((observable, oldValue, newValue) -> {
            YearSearchBarChanged();
        });
    }
}
