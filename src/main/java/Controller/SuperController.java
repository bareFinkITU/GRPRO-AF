package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SuperController {
    //En overordnet controller der har nogle overordnede metoder der bruges i de andre controllere.

    @FXML
    private GridPane gp;

    private boolean moviesClicked;
    private boolean showsClicked;
    private boolean myProfileList;
    private static SuperController instance;

    private SuperController(){
        moviesClicked = false;
        showsClicked = false;
        myProfileList = false;
    }

    public static SuperController getInstanceOf(){
        if (instance == null){
            instance = new SuperController();
        }
        return instance;
    }

    public void goToLogIn(Button button){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/LogInView.fxml"));
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
        loader.setLocation(this.getClass().getResource("/view/StartSceneView.fxml"));
        try {
            BorderPane bp = loader.load();
            Stage Megaflix = (Stage) button.getScene().getWindow();
            Megaflix.setScene(new Scene(bp));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isMoviesClicked() {
        return moviesClicked;
    }

    public boolean isShowsClicked() {
        return showsClicked;
    }

    public boolean isMyProfileList() {
        return myProfileList;
    }

    public void setMoviesClicked(boolean moviesClicked) {
        this.moviesClicked = moviesClicked;
    }

    public void setShowsClicked(boolean showsClicked) {
        this.showsClicked = showsClicked;
    }

    public void setMyProfileList(boolean myProfileList) {
        this.myProfileList = myProfileList;
    }
}
