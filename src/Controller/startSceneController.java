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
    @FXML   private Slider     startSceneYearSearchBar;
    @FXML   private Label      startSceneyearSearchlabel;

    private MediaModel mediaModel = MediaModel.getInstanceOf();
    private ArrayList<Media> allMedia = mediaModel.getMedia();
    private UserModel        userModel = UserModel.getInstanceOf();
    private String           selectedGenre;
    private CreateProfileBox createProfileBox = new CreateProfileBox();

    private boolean moviesClicked = false;
    private boolean showsClicked  = false;
    private boolean genreSearch   = false;
    private boolean titleSearch   = false;
    private boolean ratingSearch  = false;
    private boolean yearSearch    = false;
    private boolean underAged     = false;
    private boolean myProfileList = false;

    public startSceneController() {
    }

    public void homeClicked() {
        moviesClicked   = false;
        showsClicked    = false;
        genreSearch     = false;
        titleSearch     = false;
        ratingSearch    = false;
        yearSearch      = false;
        myProfileList   = false;

        initialize();
    }

    public void searchChecker() {
        mediaModel.resetMediaSort();
        if (moviesClicked)  mediaModel.searchForMovies();
        if (showsClicked)   mediaModel.searchForShows();
        if (titleSearch)    mediaModel.searchByTitle((startSceneSearchField.getText()));
        if (genreSearch)    mediaModel.searchByGenre(selectedGenre);
        if (ratingSearch)   mediaModel.searchByRating(startSceneRatingBar.getValue());
        if (yearSearch)     mediaModel.searchByYear((int) Math.round(startSceneYearSearchBar.getValue()),2020);
        if (underAged)      mediaModel.searchByGenre("Family");
        if (myProfileList)  mediaModel.searchInMyList(userModel.getSelectedUser().getSelectedProfile());
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
        myProfileList = true;
        searchChecker();
        mediaModel.drawMediaList(mediaModel.searchInMyList(userModel.getSelectedUser().getSelectedProfile()), startSceneFP);
    }

    public void logOutClicked() {
        new SuperController().goToLogIn(startSceneLogOutButton);
    }


    public void setProfiles() {
        startSceneChangeProfile.getItems().clear();
        MenuItem addNewProfile = new MenuItem("Add new profile");
        addNewProfile.setOnAction(e -> addProfileClicked());
        MenuItem removeProfile = new MenuItem("Remove a profile");
        removeProfile.setOnAction(e -> removeProfileClicked());
        startSceneChangeProfile.getItems().addAll(addNewProfile, removeProfile);

        for (Profiles p : userModel.getSelectedUser().getProfiles()) {
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
        createProfileBox.setCreateButtonText("Remove Profile");
        createProfileBox.setAgeTextField(true);
        if (createProfileBox.display()){
            setProfiles();
            initialize();
        }
    }

    public void addProfileClicked() {
        createProfileBox.setCreateButtonText("Create profile");
        createProfileBox.setAgeTextField(false);
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

        startSceneSearchField.clear();
        if (mediaModel.getSelectedMedia() == null) {
            mediaModel.resetMediaSort();

            if (userModel.getSelectedUser().getSelectedProfile().isUnderAged()){
                mediaModel.drawMediaList(mediaModel.searchByGenre("Family"),startSceneFP);
                underAged = true;
            } else {
                mediaModel.drawMediaList(allMedia, startSceneFP);
                underAged = false;
            }
        } else {
            mediaModel.drawMediaList(mediaModel.getMediaSort(),startSceneFP);
            mediaModel.setSelectedMedia(null);
        }

        startSceneRatingBar.setValue(0);
        startSceneYearSearchBar.setValue(1950);



        startSceneRatingLabel.setText("Search by rating");
        startSceneGenreMenu.setText("Genres");
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
