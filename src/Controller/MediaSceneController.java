package Controller;
import TODO_CHANGE_MY_NAME.Media;
import TODO_CHANGE_MY_NAME.Movie;
import TODO_CHANGE_MY_NAME.Show;
import Model.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    @FXML private Label     mediaSceneSeasonsLabel;

    private SuperController superController = new SuperController();
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

    public void initialize(){
        selectedMedia = mediaModel.getSelectedMedia();
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
            mediaSceneReleaseYearLabel.setText("Release year: " + ((Movie) selectedMedia).getYear());
            mediaSceneSeasonsLabel.setText("");
        }
        if (selectedMedia instanceof Show) {
            Show a = (Show) selectedMedia;
            mediaSceneReleaseYearLabel.setText("Run time: " + a.getRuntime());
            mediaSceneSeasonsLabel.setText("Seasons " + a.getSeasons());
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
