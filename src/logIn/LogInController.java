package logIn;

import UserMVC.Users;
import UserMVC.loginException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;


public class LogInController {

    @FXML
    private Button logInButton;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private BorderPane bp;

    public void logInPressed() {

        Users brugere = new Users();
        try {
            brugere.login(usernameField.getText(),passwordField.getText());
            FXMLLoader loader = new FXMLLoader();
            System.out.println("Path: " + this.getClass().getResource("/"));
            loader.setLocation(this.getClass().getResource("/startScene/scv.fxml"));
            try {
                BorderPane bp = loader.load();
                Stage Megaflix = (Stage) logInButton.getScene().getWindow();
                Megaflix.setScene(new Scene(bp));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (loginException e){
            System.out.println("Dette er ikke et gyldigt log in!");
        }





    }

    private void detHerErEnMetode(String username, String password){
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
    }



}
