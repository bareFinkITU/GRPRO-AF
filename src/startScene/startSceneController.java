package startScene;

import controller.ContentController;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import model.Content;
import model.Movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class startSceneController {

    @FXML
    private FlowPane fp;

    private ContentController cC = new ContentController();

    private ArrayList allContent;




    private void refreshContentList(List<Content> contents,  FlowPane list) {
        for (Content c : contents){
            Button newButton = new Button();
            newButton.setGraphic(new ImageView(c.getCover()));
            newButton.setStyle(" -fx-background-color: transparent");
            newButton.setOnAction(e -> {
              /*  BorderPane newBorderPane = new BorderPane();
                Button newButton2 = new Button("Tryk her for at gå tilbage");
                newButton2.setStyle(" -fx-background-color: transparent");
                //newButton.setGraphic(new ImageView(c.getCover()));
                newButton2.setOnAction(f -> window.setScene(scene));
                newBorderPane.setTop(newButton2);
                newBorderPane.setCenter(new ImageView(c.getCover()));
                newBorderPane.setStyle("-fx-background-color: BLACK;");

                Scene newScene = new Scene(newBorderPane,1270,720);

                window.setScene(newScene);*/
                System.out.println("Knapperne virker");
            });

            list.getChildren().addAll(newButton);
            System.out.println(c.getTitle());
        }
    }

    public void initialize() throws IOException {

        allContent = cC.getContent();
        refreshContentList(allContent, fp);
    }


}
