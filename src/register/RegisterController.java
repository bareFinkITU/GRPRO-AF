package register;

import UserMVC.Users;
import UserMVC.validRegistration;
import controller.SuperController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    @FXML
    private Button registerGoBackButton;
    private SuperController sC = new SuperController();



    private Users brugere;


    public void submitPressed(){
        int age = Integer.parseInt(registerAgeField.getText());
        try {
            brugere.registerUser(registerNameField.getText(),registerUsernameField.getText(),registerPasswordField.getText(),registerEmailField.getText(),age);
            sC.goToLogIn(submitButton);
        } catch (validRegistration e){
            System.out.println(e.getMessage());
        }
    }

    public void goBackPressed(){
        sC.goToLogIn(registerGoBackButton);
    }


    public void initialize(){
        brugere = Users.getInstanceOf();
        registerAgeField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    registerAgeField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
}
