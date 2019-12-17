package Controller;

import Model.UserModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


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
    private Button registerGoBackButton;
    @FXML
    private Label registerErrorMessage;

    private SuperController superController = SuperController.getInstanceOf();
    private UserModel userModel;

    public void initialize() {
        userModel = UserModel.getInstanceOf();
        registerErrorMessage.setText("All fields must be filled");
        registerAgeField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                registerAgeField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    public void submitPressed() {
        try {
            int age = Integer.parseInt(registerAgeField.getText());
            userModel.registerUser(registerNameField.getText(), registerUsernameField.getText(), registerPasswordField.getText(), registerEmailField.getText(), age);
            superController.goToLogIn(submitButton);
        } catch (IllegalArgumentException f) {
            if (f instanceof NumberFormatException) {
                registerErrorMessage.setText("All fields must be filled");
            } else {
                registerErrorMessage.setText(f.getMessage());
            }
        } catch (RuntimeException g) {
            registerErrorMessage.setText(g.getMessage());
        }
    }

    public void goBackPressed() {
        superController.goToLogIn(registerGoBackButton);
    }


}
