package logIn;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LogInView.fxml"));
        primaryStage.setTitle("MEGAFLIX");
        primaryStage.setScene(new Scene(root, 1270, 720));


        /*Parent startScene = FXMLLoader.load(getClass().getResource("startSceneView.fxml"));
        Scene startScene2 = new Scene(startScene, 1270, 720);
        primaryStage.setScene(startScene2);
*/
        primaryStage.show();
    }
}
