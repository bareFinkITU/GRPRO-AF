package startScene;

import UserMVC.Users;
import controller.ContentController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Content;
import model.Movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class startSceneController {

    @FXML
    private FlowPane startSceneFP;

    @FXML
    private GridPane gp;

    @FXML
    private BorderPane myProfileBP;

    @FXML
    private TextField startSceneSearchField;

    @FXML
    private Button startSceneLogOutButton;

    @FXML
    private Button startSceneMyProfileButton;

    private ContentController cC = ContentController.getInstanceOf();

    private ArrayList<Content> allContent = cC.getContent();

    private Users brugere = Users.getInstanceOf();

    public startSceneController() throws IOException {
    }


    public void searchByTitle() throws IOException {
        cC.resetContentSort();
        ArrayList<Content> searchByTitleContent =  cC.searchByTitle(startSceneSearchField.getText());
        cC.drawContentList(searchByTitleContent, startSceneFP);
        System.out.println(startSceneSearchField.getText());
    }

    public void homeClicked() throws IOException {
        cC.resetContentSort();
        cC.drawContentList(allContent,startSceneFP);
    }

    public void moviesClicked() throws IOException {
        cC.resetContentSort();
        ArrayList<Content> searchForMovies = cC.searchForMovies();
        cC.drawContentList(searchForMovies,startSceneFP);
        System.out.println("Now it's only movies!");
    }

    public void showsClicked() throws IOException {
        cC.resetContentSort();
        ArrayList<Content> searchForShows = cC.searchForShows();
        cC.drawContentList(searchForShows,startSceneFP);
        System.out.println("Now it's only shows!");
    }

    public void logOutClicked(){
        FXMLLoader loader = new FXMLLoader();
        System.out.println("Path: " + this.getClass().getResource("/"));
        loader.setLocation(getClass().getResource("/logIn/LogInView.fxml"));
        try {
            gp = loader.load();
            Stage Megaflix = (Stage) startSceneLogOutButton.getScene().getWindow();
            Megaflix.setScene(new Scene(gp));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void myProfileClicked(){
        FXMLLoader loader = new FXMLLoader();
        System.out.println("Path: " + this.getClass().getResource("/"));
        loader.setLocation(getClass().getResource("/myProfile/MyProfileView.fxml"));
        try {
            myProfileBP = loader.load();
            Stage Megaflix = (Stage) startSceneMyProfileButton.getScene().getWindow();
            Megaflix.setScene(new Scene(myProfileBP));
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*System.out.println("The selected user is: ");
        System.out.println(brugere.getSelectedUser().getName());
        System.out.println();
        List<Content> favorites = brugere.getSelectedUser().getFavorites();

        //Udskriver de film der er i favorit listen
        for (Content c : favorites) {
            System.out.println(c.getTitle());
        }*/
    }


    public void initialize() throws IOException {
        cC.resetContentSort();
        cC.drawContentList(allContent,startSceneFP);
    }



}
