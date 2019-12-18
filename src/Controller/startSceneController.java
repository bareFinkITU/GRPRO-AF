package Controller;

import TODO_CHANGE_MY_NAME.Profile;
import Model.MediaModel;
import Model.UserModel;
import View.RemoveProfileBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import TODO_CHANGE_MY_NAME.Media;
import View.CreateProfileBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class startSceneController {
    @FXML   private FlowPane   startSceneFP;
    @FXML   private BorderPane mediaSceneBP;
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
        //TODO unchecked assignment arraylist < ? >
        drawMediaList(mediaModel.searchByTitle(startSceneSearchField.getText()), startSceneFP);
    }

    public void moviesClicked() {
        startSceneHomeButton.setStyle("-fx-background-color: TRANSPERANT");
        startSceneMovieButton.setStyle("-fx-background-color: D9D9D9");
        startSceneShowsButton.setStyle("-fx-background-color: TRANSPERANT");
        superController.setMoviesClicked(true);
        superController.setShowsClicked(false);
        searchChecker();
        drawMediaList(mediaModel.searchForMovies(), startSceneFP);
    }

    public void showsClicked() {
        startSceneHomeButton.setStyle("-fx-background-color: TRANSPERANT");
        startSceneShowsButton.setStyle("-fx-background-color: D9D9D9");
        startSceneMovieButton.setStyle("-fx-background-color: TRANSPERANT");
        superController.setMoviesClicked(false);
        superController.setShowsClicked(true);
        searchChecker();
        drawMediaList(mediaModel.searchForShows(), startSceneFP);
    }

    private void searchByGenre(String s) {
        genreSearch = true;
        selectedGenre = s;
        searchChecker();
        drawMediaList(mediaModel.searchByGenre(s),startSceneFP);
        startSceneGenreMenu.setText(s);
    }

    private void ratingBarChanged() {
        ratingSearch = true;
        searchChecker();
        drawMediaList(mediaModel.searchByRating(startSceneRatingBar.getValue()),startSceneFP);
        String rating = ("" + startSceneRatingBar.getValue()).substring(0,3);
        startSceneRatingLabel.setText("Search by rating: " + rating);
    }

    private void YearSearchBarChanged(){
        yearSearch = true;
        searchChecker();
        drawMediaList(mediaModel.searchByYear((int) Math.round(startSceneYearSearchBar.getValue()),2020),startSceneFP);
        String year = ("" + startSceneYearSearchBar.getValue()).substring(0,4);
        startSceneyearSearchlabel.setText("Search by year: " + year);
    }

    public void myProfileClicked() {
        homeClicked();
        startSceneHomeButton.setStyle("-fx-background-color: TRANSPERANT");
        startSceneMyProfileButton.setStyle("-fx-background-color: D9D9D9");
        superController.setMyProfileList(true);
        searchChecker();
        drawMediaList(mediaModel.searchInMyList(userModel.getSelectedUser().getSelectedProfile()), startSceneFP);
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

    public void drawMediaList(List<Media> medias, FlowPane flowPane) {
        flowPane.getChildren().clear();
        if (medias.size() == 0) {
            Label errorLabel = new Label("No media matches the search criteria, try again.");
            errorLabel.setTextFill(Color.web("WHITE"));
            errorLabel.setFont(new Font(16));
            errorLabel.setPadding(new Insets(0,5,5,5));
            flowPane.getChildren().add(errorLabel);
        } else {
            for (Media c : medias) {
                Button newButton = new Button();
                newButton.setGraphic(new ImageView(c.getCover()));
                newButton.setStyle(" -fx-background-color: transparent");
                newButton.setOnAction(e -> {
                    mediaModel.setSelectedMedia(c);

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/View/MediaSceneView.fxml"));
                    try {
                        mediaSceneBP = loader.load();
                        Stage Megaflix = (Stage) newButton.getScene().getWindow();
                        Megaflix.setScene(new Scene(mediaSceneBP));
                    } catch (IOException t) {
                        t.printStackTrace();
                    }

                });
                newButton.setOnMouseEntered(e -> {
                    newButton.setScaleX(1.1);
                    newButton.setScaleY(1.1);
                });
                newButton.setOnMouseExited(e -> {
                    newButton.setScaleY(1);
                    newButton.setScaleX(1);
                });

                flowPane.getChildren().addAll(newButton);
            }
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
                drawMediaList(mediaModel.searchByGenre("Family"),startSceneFP);
                underAged = true;
            } else {
                drawMediaList(mediaModel.getMedia(), startSceneFP);
                underAged = false;
            }
        } else {
            if (superController.isMyProfileList()){
                myProfileClicked();
                drawMediaList(mediaModel.getMediaSort(),startSceneFP);
            } else{
                drawMediaList(mediaModel.getMediaSort(),startSceneFP);
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
