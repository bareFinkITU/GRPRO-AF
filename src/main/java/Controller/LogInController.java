package Controller;

import Model.MediaModel;
import Model.UserModel;
import Exceptions.IllegalLoginException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;


public class LogInController {

    @FXML   private Button logInButton;
    @FXML   private TextField usernameField;
    @FXML   private PasswordField passwordField;
    @FXML   private Button registerButton;
    @FXML   private Label logInMessageLabel;

    private SuperController  superController = SuperController.getInstanceOf();
    private MediaModel       mediaModel = MediaModel.getInstanceOf();
    private UserModel        userModel =  UserModel.getInstanceOf();


    public LogInController() {
    }

    public void logInPressed() {

        try {
            if(userModel.login(usernameField.getText(),passwordField.getText()) != null){
                superController.goToStartScene(logInButton);
            }

        } catch (IllegalLoginException e){
            logInMessageLabel.setText(e.getMessage());
        } catch (NullPointerException f) {
            logInMessageLabel.setText(f.getMessage());
        }
    }

    public void registerPressed(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/view/RegisterView.fxml"));
        try {
            GridPane registerGP = loader.load();
            Stage Megaflix = (Stage) registerButton.getScene().getWindow();
            Megaflix.setScene(new Scene(registerGP));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initialize(){
       logInMessageLabel.setText("");
       mediaModel.setSelectedMedia(null);
    }
}
