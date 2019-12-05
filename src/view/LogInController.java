package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class LogInController {

    @FXML
    private Button logInButton;
    @FXML
    private TextField usernameField;

    public void logInPressed() {
        detHerErEnMetode(usernameField.getText(), "*********");
    }

    private void detHerErEnMetode(String username, String password){
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
    }
}
