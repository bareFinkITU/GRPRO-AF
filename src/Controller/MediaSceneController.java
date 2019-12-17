package Controller;
import TODO_CHANGE_MY_NAME.Media;
import TODO_CHANGE_MY_NAME.Movie;
import TODO_CHANGE_MY_NAME.Show;
import Model.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MediaSceneController {
    @FXML private ImageView mediaSceneImageView;
    @FXML private Label     mediaSceneTitleLabel;
    @FXML private Label     mediaSceneGenresLabel;
    @FXML private Label     mediaSceneReleaseYearLabel;
    @FXML private Button    mediaSceneLogOut;
    @FXML private Button    mediaSceneBackButton;
    @FXML private Button    mediaSceneAddToMyListButton;
    @FXML private Label     mediaSceneMessageLabel;
    @FXML private Label     mediaSceneRatingLabel;
    @FXML private MenuButton mediaSceneSeasonsButton;
    @FXML private MenuButton mediaSceneEpisodesButton;
    @FXML private Button mediaScenePlayButton;

    private SuperController superController = SuperController.getInstanceOf();
    private MediaModel mediaModel = MediaModel.getInstanceOf();
    private UserModel userModel = UserModel.getInstanceOf();
    private Media selectedMedia;

    public MediaSceneController(){
    }


    public void logOutClicked(){
        superController.goToLogIn(mediaSceneLogOut);
    }

    public void backClicked() {
        superController.goToStartScene(mediaSceneBackButton);
    }

    //TODO flyt til UserModel
    public boolean isInFavorites(Media media){
        for (Media m : userModel.getSelectedUser().getSelectedProfile().getFavorites()){
            if (m == media){
                return true;
            }
        }
        return false;
    }

    public void addToMyListClicked(){
        if (!isInFavorites(selectedMedia)){
            //tilføjer filmen/serien til ens liste hvis man klikker på den
            userModel.getSelectedUser().getSelectedProfile().addMedia(selectedMedia);
            mediaSceneMessageLabel.setText(" Added to favorites");
            mediaSceneAddToMyListButton.setText("Remove from my list");
        } else {
            //fjerner filmen/serien til ens liste hvis man klikker på den
            userModel.getSelectedUser().getSelectedProfile().removeMedia(selectedMedia);
            mediaSceneMessageLabel.setText(" Removed from favorites");
            mediaSceneAddToMyListButton.setText("Add to my list");
        }
    }

    public void playButtonClicked(){
        if (mediaScenePlayButton.getText().equals("Play")){
            mediaSceneMessageLabel.setText("Now playing " + selectedMedia.getTitle());
            mediaScenePlayButton.setText("Pause");
        } else {
            mediaScenePlayButton.setText("Play");
            mediaSceneMessageLabel.setText(selectedMedia.getTitle() + " paused");
        }
    }

    public void initialize(){
        selectedMedia = mediaModel.getSelectedMedia();
        mediaScenePlayButton.setText("Play");
        mediaSceneMessageLabel.setText("");
        mediaSceneTitleLabel.setText("Title: " + selectedMedia.getTitle());
        StringBuilder s = new StringBuilder();
        int i = 0;
        for (String string : selectedMedia.getGenre()){
            s.append(string);
            i++;
            if (i < selectedMedia.getGenre().length){
                s.append(", ");
            }
        }
        mediaSceneGenresLabel.setText("Genres: " + s);
        String rating = "" + selectedMedia.getRating();
        mediaSceneRatingLabel.setText("Rating: " + rating);
        if (selectedMedia instanceof Movie) {
            Movie a = (Movie) selectedMedia;
            mediaSceneReleaseYearLabel.setText("Release year: " + (a.getYear()));
            mediaSceneSeasonsButton.setVisible(false);
            mediaSceneEpisodesButton.setVisible(false);
        }
        if (selectedMedia instanceof Show) {
            Show a = (Show) selectedMedia;
            mediaSceneReleaseYearLabel.setText("Run time: " + a.getRuntime());
            mediaSceneSeasonsButton.setVisible(true);
            mediaSceneEpisodesButton.setVisible(true);
            mediaSceneSeasonsButton.setText("Seasons");
            mediaSceneEpisodesButton.setText("Episodes");
            for (Object key : mediaModel.getSeasonAndEpisodesMap(a).keySet()){
                MenuItem newMenuItem = new MenuItem("Season " + key);
                newMenuItem.setOnAction(e -> {
                    mediaSceneEpisodesButton.setText("Episodes");
                    mediaSceneSeasonsButton.setText(newMenuItem.getText());
                    mediaSceneEpisodesButton.getItems().clear();
                    int numberOfEpisodes = (int) mediaModel.getSeasonAndEpisodesMap(a).get(key);
                    for (int j = 1; j+1 < numberOfEpisodes; j++){
                        MenuItem newEpisodeItem = new MenuItem("Episode " + j);
                        newEpisodeItem.setOnAction(f -> mediaSceneEpisodesButton.setText(newEpisodeItem.getText()));
                        mediaSceneEpisodesButton.getItems().add(newEpisodeItem);
                    }
                });
                mediaSceneSeasonsButton.getItems().add(newMenuItem);
            }
        }

        if (!isInFavorites(selectedMedia)){
            mediaSceneAddToMyListButton.setText("Add to my list");
        } else {
            mediaSceneAddToMyListButton.setText("Remove from my list");
        }

        Image selectedImage = selectedMedia.getCover();
        mediaSceneImageView.setImage(selectedImage);
    }
}
