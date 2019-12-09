package movieScene;

import controller.ContentController;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Content;

import java.io.IOException;

public class MovieSceneController {
    @FXML
    private ImageView movieSceneImageView;

    private ContentController cC = ContentController.getInstanceOf();

    private Content selectedContent;



    public MovieSceneController() throws IOException {
    }

    public void initialize(){
        selectedContent = cC.getSelectedContent();
        Image selectedImage = selectedContent.getCover();
        movieSceneImageView.setImage(selectedImage);
    }
}
