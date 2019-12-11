package logIn;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    private GridPane gp;

    private Stage Megaflix;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Megaflix = primaryStage;
        Megaflix.setTitle("MEGAFLIX");
        Megaflix.setMinHeight(565);
        Megaflix.setMinWidth(1065);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("LogInView.fxml"));
        gp = loader.load();
        Megaflix.setScene(new Scene(gp));



        primaryStage.show();
    }
}
