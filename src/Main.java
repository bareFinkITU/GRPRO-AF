import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Sætter programmet igang, og går til login skærmen

        primaryStage.setTitle("MEGAFLIX");
        primaryStage.setMinHeight(610);
        primaryStage.setMinWidth(1020);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/LogInView.fxml"));
        GridPane gp = loader.load();
        primaryStage.setScene(new Scene(gp));
        primaryStage.show();
    }
}
