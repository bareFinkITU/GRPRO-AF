package register;

import UserMVC.Users;
import UserMVC.validRegistration;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;


public class RegisterController {

    @FXML
    private TextField registerNameField;
    @FXML
    private TextField registerUsernameField;
    @FXML
    private TextField registerPasswordField;
    @FXML
    private TextField registerEmailField;
    @FXML
    private TextField registerAgeField;
    @FXML
    private Button submitButton;
    @FXML
    private GridPane gp;



    private Users brugere;


    public void submitPressed(){
        int age = Integer.parseInt(registerAgeField.getText());
        try {
            brugere.registerUser(registerNameField.getText(),registerUsernameField.getText(),registerPasswordField.getText(),registerEmailField.getText(),age);
            FXMLLoader loader = new FXMLLoader();
            System.out.println("Path: " + this.getClass().getResource("/"));
            loader.setLocation(getClass().getResource("/logIn/LogInView.fxml"));
            try {
                gp = loader.load();
                Stage Megaflix = (Stage) submitButton.getScene().getWindow();
                Megaflix.setScene(new Scene(gp));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (validRegistration e){
            System.out.println(e.getMessage());
        }
    }

    public void initialize(){
        brugere = Users.getInstanceOf();
    }
}
