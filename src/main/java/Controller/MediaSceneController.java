package Controller;
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

    public MediaSceneController(){
    }


    public void logOutClicked(){
        superController.goToLogIn(mediaSceneLogOut);
    }

    public void backClicked() {
        superController.goToStartScene(mediaSceneBackButton);
    }

    public void addToMyListClicked(){
        //Fjerner eller tilføjer den valgte film/serie til listen af favoritfilm, afhængigt af om den er der i forvejen.
        if (!userModel.isInFavorites(mediaModel.getSelectedMedia())){
            //tilføjer filmen/serien til ens liste hvis man klikker på den
            userModel.getSelectedUser().getSelectedProfile().addMedia(mediaModel.getSelectedMedia());
            mediaSceneMessageLabel.setText(" Added to favorites");
            mediaSceneAddToMyListButton.setText("Remove from my list");
        } else {
            //fjerner filmen/serien til ens liste hvis man klikker på den
            userModel.getSelectedUser().getSelectedProfile().removeMedia(mediaModel.getSelectedMedia());
            mediaSceneMessageLabel.setText(" Removed from favorites");
            mediaSceneAddToMyListButton.setText("Add to my list");
        }
    }

    public void playButtonClicked(){
        if (mediaScenePlayButton.getText().equals("Play")){
            mediaSceneMessageLabel.setText("Now playing " + mediaModel.getSelectedMedia().getTitle());
            mediaScenePlayButton.setText("Pause");
        } else {
            mediaScenePlayButton.setText("Play");
            mediaSceneMessageLabel.setText(mediaModel.getSelectedMedia().getTitle() + " paused");
        }
    }

    public void initialize(){
        //Opsætter informationen om den valgte film/serie
        mediaScenePlayButton.setText("Play");
        mediaSceneMessageLabel.setText("");
        mediaSceneTitleLabel.setText("Title: " + mediaModel.getSelectedMedia().getTitle());
        StringBuilder s = new StringBuilder();
        int i = 0;
        for (String string : mediaModel.getSelectedMedia().getGenre()){
            s.append(string);
            i++;
            if (i < mediaModel.getSelectedMedia().getGenre().length){
                s.append(", ");
            }
        }
        mediaSceneGenresLabel.setText("Genres: " + s);
        String rating = "" + mediaModel.getSelectedMedia().getRating();
        mediaSceneRatingLabel.setText("Rating: " + rating);
        //Tjekker om der er valgt en film
        if (mediaModel.selectedMediaIsMovie()) {
            mediaSceneReleaseYearLabel.setText("Release year: " + (mediaModel.getSelectedMovie().getYear()));
            mediaSceneSeasonsButton.setVisible(false);
            mediaSceneEpisodesButton.setVisible(false);
        }
        //Tjekker om der er valgt en serie
        if (mediaModel.selectedMediaIsShow()) {
            mediaSceneReleaseYearLabel.setText("Run time: " + mediaModel.getSelectedShow().getRuntime());
            mediaSceneSeasonsButton.setVisible(true);
            mediaSceneEpisodesButton.setVisible(true);
            mediaSceneSeasonsButton.setText("Seasons");
            mediaSceneEpisodesButton.setText("Episodes");
            //Laver Season og Episode knapperne hvis det er en serie
            for (Object key : mediaModel.getSeasonAndEpisodesMap(mediaModel.getSelectedShow()).keySet()){
                MenuItem newMenuItem = new MenuItem("Season " + key);
                newMenuItem.setOnAction(e -> {
                    mediaSceneEpisodesButton.setText("Episodes");
                    mediaSceneSeasonsButton.setText(newMenuItem.getText());
                    mediaSceneEpisodesButton.getItems().clear();
                    int numberOfEpisodes = (int) mediaModel.getSeasonAndEpisodesMap(mediaModel.getSelectedShow()).get(key);
                    for (int j = 1; j+1 < numberOfEpisodes; j++){
                        MenuItem newEpisodeItem = new MenuItem("Episode " + j);
                        newEpisodeItem.setOnAction(f -> mediaSceneEpisodesButton.setText(newEpisodeItem.getText()));
                        mediaSceneEpisodesButton.getItems().add(newEpisodeItem);
                    }
                });
                mediaSceneSeasonsButton.getItems().add(newMenuItem);
            }
        }

        //Sætter teksten på knappen afhængigt af om filmen allerede er i favorit listen
        if (!userModel.isInFavorites(mediaModel.getSelectedMedia())){
            mediaSceneAddToMyListButton.setText("Add to my list");
        } else {
            mediaSceneAddToMyListButton.setText("Remove from my list");
        }

        Image selectedImage = mediaModel.getSelectedMedia().getCover();
        mediaSceneImageView.setImage(selectedImage);
    }
}
