package logIn;

import UserMVC.Users;
import UserMVC.loginException;
import controller.SuperController;
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
    @FXML
    private GridPane registerGP;
    @FXML
    private Button registerButton;

    private SuperController sC = new SuperController();

    private Users brugere;

    public void logInPressed() {

        try {
           // List<User> opdatedList = brugere.getUsers();
            if(brugere.login(usernameField.getText(),passwordField.getText()) != null){
                sC.goToStartScene(logInButton);
            }

        } catch (loginException e){
            System.out.println(e.getMessage());
        }
    }

    public void registerPressed(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/register/RegisterView.fxml"));
        try {
            GridPane registerGP = loader.load();
            Stage Megaflix = (Stage) registerButton.getScene().getWindow();
            Megaflix.setScene(new Scene(registerGP));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initialize(){
       brugere =  Users.getInstanceOf();
    }
}
