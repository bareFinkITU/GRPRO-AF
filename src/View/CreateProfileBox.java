package View;

import TODO_CHANGE_MY_NAME.Profile;
import Model.UserModel;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
//TODO check om det kan være private
public class CreateProfileBox {

    private UserModel userModel = UserModel.getInstanceOf();
    private Boolean answer;
    private TextField ageTextField = new TextField();

    public boolean display(){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Create new profile");
        window.setMinWidth(300);
        window.setMinHeight(215);

        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setVgap(8);
        layout.setHgap(10);

        Label usernameLabel = new Label("Username: ");
        GridPane.setConstraints(usernameLabel, 0 ,0);

        Label ageLabel = new Label("Age ");
        GridPane.setConstraints(ageLabel, 0, 1);

        TextField usernameTextField = new TextField();
        usernameTextField.setPromptText("Username");
        GridPane.setConstraints(usernameTextField,1,0);

        ageTextField.setPromptText("Age");

        ageTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                ageTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        ageTextField.setOnAction(e -> {
            //TODO lav en smart metode så kodedublekering undgås
            int age = Integer.parseInt(ageTextField.getText());
            Profile newProfile = new Profile(usernameTextField.getText(),age);
            userModel.getSelectedUser().addProfile(newProfile);
            userModel.getSelectedUser().setSelectedProfile(newProfile);
            window.close();
            answer = true;
        });
        GridPane.setConstraints(ageTextField,1,1);


        Label errorLabel = new Label("All fields must be filled");
        GridPane.setConstraints(errorLabel,1,3);

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> window.close());
        GridPane.setConstraints(cancelButton,0,2);

       Button createButton = new Button("Create profile");
        createButton.setOnAction(e -> {
            try {
                int age = Integer.parseInt(ageTextField.getText());
                Profile newProfile = new Profile(usernameTextField.getText(),age);
                userModel.getSelectedUser().addProfile(newProfile);
                userModel.getSelectedUser().setSelectedProfile(newProfile);
                window.close();
                answer = true;
            } catch (IllegalArgumentException i){
                if (i instanceof NumberFormatException) {
                    errorLabel.setText("All fields must be filled");
                } else {
                    errorLabel.setText(i.getMessage());
                }
            }
        });
        GridPane.setConstraints(createButton,1,2);
        layout.getChildren().addAll(usernameLabel,ageLabel,usernameTextField,ageTextField,cancelButton,createButton,errorLabel);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }

}
