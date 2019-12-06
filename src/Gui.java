import controller.ContentController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Content;


import javax.naming.ContextNotEmptyException;
import java.io.FileInputStream;
import java.io.FileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Gui extends Application{

    Stage window;

    public static void main(String[] args) throws IOException {
        //Starter interfacet
        launch(args);
    }

    @Override
    //Her skrives alt der skal være i interfacet
    public void start(Stage primaryStage) throws Exception {

        //Det første er en test.
        ContentController test = new ContentController();

        test.initializeContent();
/*      test.customSort(8.5);
        test.search("k");*/
        System.out.println();
        System.out.println();


        window = primaryStage;
        window.setTitle("FAST SPEED MEGAFLIX 9000");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        //Name label
        Label nameLabel = new Label("Username");
        GridPane.setConstraints(nameLabel, 0,0);

        //Name input
        TextField nameInput = new TextField();
        nameInput.setPromptText("Username");
        GridPane.setConstraints(nameInput, 1, 0);

        //Password label
        Label passLabel = new Label("Password");
        GridPane.setConstraints(passLabel, 0,1);

        //Password input
        TextField passInput = new TextField();
        passInput.setPromptText("Password");
        GridPane.setConstraints(passInput, 1,1);

        Button loginButton = new Button("Log In");
        GridPane.setConstraints(loginButton, 1,2);

        grid.getChildren().addAll(nameLabel,nameInput,passLabel,passInput,loginButton);


        StackPane sP1 = new StackPane();
        sP1.setStyle("-fx-background-color: DC0505;");
        Image logo = new Image(new FileInputStream("out/img/logo.jpg"));
        sP1.getChildren().addAll((new ImageView(logo)), grid);

        Scene logIn = new Scene(sP1, 1270, 720);

        ScrollPane scroll = new ScrollPane();

        FlowPane flow = new FlowPane();
        flow.setPadding(new Insets(5, 0, 5, 0));

        flow.setVgap(20);
        flow.setHgap(0);

        flow.setPrefWrapLength(170); // preferred width allows for two columns
        flow.setStyle("-fx-background-color: BLACK;");

        scroll.setContent(flow);
        scroll.fitToHeightProperty().set(true);
        scroll.fitToWidthProperty().set(true);

        BorderPane bP2 = new BorderPane();
        bP2.setCenter(scroll);



        Button logUd = new Button("Log ud");
        logUd.setOnAction(e -> window.setScene(logIn));


        TextField searchInput = new TextField();
        nameInput.setPromptText("Search");

        Button search = new Button();



        Image searchImage = new Image(new FileInputStream("out/img/search.png"));
        ImageView newImageView = new ImageView(searchImage);
        newImageView.setFitHeight(20);
        newImageView.setFitWidth(20);
        search.setGraphic(newImageView);
        search.setStyle(" -fx-background-color: transparent");



        HBox topMenu = new HBox();
        topMenu.setStyle("-fx-background-color: BLACK;");
        topMenu.getChildren().addAll(logUd, searchInput, search);
        bP2.setTop(topMenu);

        Scene startScene = new Scene(bP2,1270, 720);
        loginButton.setOnAction(e -> window.setScene(startScene));
        search.setOnAction(e -> {
            ArrayList<Content> searchArrayList = test.searchByTitle(searchInput.getText());
            refreshContentList(searchArrayList, startScene, flow);
        });


        refreshContentList(test.getContent(), startScene, flow);

        window.setScene(logIn);
        window.show();
    }

    private void refreshContentList(List<Content> contents, Scene scene, FlowPane list) {
        list.getChildren().clear();
        for (Content c : contents){
            Button newButton = new Button();
            newButton.setGraphic(new ImageView(c.getCover()));
            newButton.setStyle(" -fx-background-color: transparent");
            newButton.setOnAction(e -> {
                BorderPane newBorderPane = new BorderPane();
                Button newButton2 = new Button("Tryk her for at gå tilbage");
                newButton2.setStyle(" -fx-background-color: transparent");
                //newButton.setGraphic(new ImageView(c.getCover()));
                newButton2.setOnAction(f -> window.setScene(scene));
                newBorderPane.setTop(newButton2);
                newBorderPane.setCenter(new ImageView(c.getCover()));
                newBorderPane.setStyle("-fx-background-color: BLACK;");

                Scene newScene = new Scene(newBorderPane,1270,720);

                window.setScene(newScene);
            });

            list.getChildren().addAll(newButton);
        }
    }
}