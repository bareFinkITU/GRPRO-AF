package Controller;
//TODO fix fxml navne
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
    @FXML private Label     contentSceneTitleLabel;
    @FXML private Label     contentSceneGenresLabel;
    @FXML private Label     contentSceneReleaseYearLabel;
    @FXML private Button    movieSceneLogOut;
    @FXML private Button    movieSceneBackButton;
    @FXML private Button    contentSceneAddToMyListButton;
    @FXML private Label     mediaSceneMessageLabel;
    @FXML private Label     contentSceneRatingLabel;
    @FXML private Label     contentSceneSeasonsLabel;

    private SuperController superController = new SuperController();
    private MediaModel mediaModel = MediaModel.getInstanceOf();
    private UserModel userModel = UserModel.getInstanceOf();
    private Media selectedMedia;

    public MediaSceneController(){
    }


    public void logOutClicked(){
        superController.goToLogIn(movieSceneLogOut);
    }

    public void backClicked() {
        superController.goToStartScene(movieSceneBackButton);
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
            contentSceneAddToMyListButton.setText("Remove from my list");
        } else {
            //fjerner filmen/serien til ens liste hvis man klikker på den
            userModel.getSelectedUser().getSelectedProfile().removeMedia(selectedMedia);
            mediaSceneMessageLabel.setText(" Removed from favorites");
            contentSceneAddToMyListButton.setText("Add to my list");
        }
    }

    public void initialize(){
        selectedMedia = mediaModel.getSelectedMedia();
        mediaSceneMessageLabel.setText("");
        contentSceneTitleLabel.setText("Title: " + selectedMedia.getTitle());
        //TODO check om den virker :)
        StringBuilder s = new StringBuilder();
        int i = 0;
        for (String string : selectedMedia.getGenre()){
            s.append(string);
            i++;
            if (i < selectedMedia.getGenre().length){
                s.append(", ");
            }
        }
        contentSceneGenresLabel.setText("Genres: " + s);
        String rating = "" + selectedMedia.getRating();
        contentSceneRatingLabel.setText("Rating: " + rating);
        if (selectedMedia instanceof Movie) {
            Movie a = (Movie) selectedMedia;
            contentSceneReleaseYearLabel.setText("Release year: " + ((Movie) selectedMedia).getYear());
            contentSceneSeasonsLabel.setText("");
        }
        if (selectedMedia instanceof Show) {
            Show a = (Show) selectedMedia;
            contentSceneReleaseYearLabel.setText("Run time: " + a.getRuntime());
            contentSceneSeasonsLabel.setText("Seasons " + a.getSeasons());
        }

        if (!isInFavorites(selectedMedia)){
            contentSceneAddToMyListButton.setText("Add to my list");
        } else {
            contentSceneAddToMyListButton.setText("Remove from my list");
        }

        Image selectedImage = selectedMedia.getCover();
        mediaSceneImageView.setImage(selectedImage);
    }
}
