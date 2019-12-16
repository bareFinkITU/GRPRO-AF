package Controller;

import Model.MediaModel;
import TODO_CHANGE_MY_NAME.Media;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class SuperController {
    @FXML
    private GridPane gp;

    private MediaModel mediaModel = MediaModel.getInstanceOf();

    public SuperController(){}

    public void goToLogIn(Button button){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/LogInView.fxml"));
        try {
            gp = loader.load();
            Stage Megaflix = (Stage) button.getScene().getWindow();
            Megaflix.setScene(new Scene(gp));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToStartScene(Button button) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/View/StartSceneView.fxml"));
        try {
            BorderPane bp = loader.load();
            Stage Megaflix = (Stage) button.getScene().getWindow();
            Megaflix.setScene(new Scene(bp));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
