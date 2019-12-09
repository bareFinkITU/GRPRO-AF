package movieScene;

import controller.ContentController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Content;
import model.Movie;
import model.Show;

import java.io.IOException;

public class MovieSceneController {
    @FXML
    private ImageView movieSceneImageView;

    @FXML
    private Label contentSceneTitleLabel;

    @FXML
    private Label contentSceneGenresLabel;

    @FXML
    private Label contentSceneReleaseYearLabel;

    private ContentController cC = ContentController.getInstanceOf();

    private Content selectedContent;



    public MovieSceneController() throws IOException {
    }

    public void initialize(){
        selectedContent = cC.getSelectedContent();
        contentSceneTitleLabel.setText("Title: " + selectedContent.getTitle());
        String s = new String();
        int i = 0;
        for (String string : selectedContent.getGenre()){
            s += string;
            i++;
            if (i < selectedContent.getGenre().length){
                s += ", ";
            }
        }
        contentSceneGenresLabel.setText("Genres: " + s);
        if (selectedContent instanceof Movie) {
            Movie a = (Movie) selectedContent;
            contentSceneReleaseYearLabel.setText("Release year: " + ((Movie) selectedContent).getYear());
        }
        if (selectedContent instanceof Show) {
            Show a = (Show) selectedContent;
            contentSceneReleaseYearLabel.setText("Run time: " + ((Show) selectedContent).getRuntime());
        }

        Image selectedImage = selectedContent.getCover();
        movieSceneImageView.setImage(selectedImage);
    }
}
