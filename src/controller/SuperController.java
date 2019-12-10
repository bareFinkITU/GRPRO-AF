package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SuperController {

    public SuperController(){}

    @FXML
    private GridPane gp;

    @FXML
    private BorderPane bp;

    public void goToLogIn(Button button){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/logIn/LogInView.fxml"));
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
        loader.setLocation(this.getClass().getResource("/startScene/StartSceneView.fxml"));
        try {
            BorderPane bp = loader.load();
            Stage Megaflix = (Stage) button.getScene().getWindow();
            Megaflix.setScene(new Scene(bp));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
