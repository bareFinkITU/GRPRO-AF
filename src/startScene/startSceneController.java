package startScene;

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
    private FlowPane fp;

    @FXML
    private GridPane gp;

    @FXML
    private TextField searchField;

    @FXML
    private Button logOutButton;

    private ContentController cC = ContentController.getInstanceOf();

    private ArrayList<Content> allContent = cC.getContent();

    public startSceneController() throws IOException {
    }


    public void searchByTitle() throws IOException {
        cC.resetContentSort();
        ArrayList<Content> searchByTitleContent =  cC.searchByTitle(searchField.getText());
        refreshContentList(searchByTitleContent, fp);
    }

    public void homeClicked() throws IOException {
        cC.resetContentSort();
        refreshContentList(allContent,fp);
    }

    public void moviesClicked() throws IOException {
        cC.resetContentSort();
        ArrayList<Content> searchForMovies = cC.searchForMovies();
        refreshContentList(searchForMovies,fp);
        System.out.println("Now it's only movies!");
    }

    public void showsClicked() throws IOException {
        cC.resetContentSort();
        ArrayList<Content> searchForShows = cC.searchForShows();
        refreshContentList(searchForShows,fp);
        System.out.println("Now it's only shows!");
    }

    public void logOut(){
        FXMLLoader loader = new FXMLLoader();
        System.out.println("Path: " + this.getClass().getResource("/"));
        loader.setLocation(getClass().getResource("/logIn/LogInView.fxml"));
        try {
            gp = loader.load();
            Stage Megaflix = (Stage) logOutButton.getScene().getWindow();
            Megaflix.setScene(new Scene(gp));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void refreshContentList(List<Content> contents,  FlowPane list) {
        list.getChildren().clear();
        for (Content c : contents){
            Button newButton = new Button();
            newButton.setGraphic(new ImageView(c.getCover()));
            newButton.setStyle(" -fx-background-color: transparent");
            newButton.setOnAction(e -> {
                System.out.println(c.getTitle());
            });
            newButton.setOnMouseEntered(e -> {
                newButton.setScaleX(1.1);
                newButton.setScaleY(1.1);
            });
            newButton.setOnMouseExited(e -> {
                newButton.setScaleY(1);
                newButton.setScaleX(1);
            });

            list.getChildren().addAll(newButton);
        }
    }

    public void initialize() throws IOException {
        cC.resetContentSort();
        refreshContentList(allContent,fp);
    }



}
