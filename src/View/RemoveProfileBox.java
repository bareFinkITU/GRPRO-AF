package View;

import Model.UserModel;
import SubModel.Profile;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RemoveProfileBox {

    private UserModel userModel = UserModel.getInstanceOf();
    private Profile profile = null;


    public boolean display(){
        //Opretter det vindue hvori man kan fjerne en profil

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Remove profile");
        window.setMinWidth(300);
        window.setMinHeight(215);
        window.setMaxHeight(215);


        ScrollPane layout = new ScrollPane();
        VBox vBox = new VBox();
        vBox.setSpacing(5);
        final ToggleGroup group = new ToggleGroup();

        for (Profile p : userModel.getSelectedUser().getProfiles()){
            RadioButton newRadioButton = new RadioButton(p.getName());
            newRadioButton.setOnAction(e -> profile = p);
            newRadioButton.setToggleGroup(group);
            vBox.getChildren().add(newRadioButton);
        }

        HBox buttonMenu = new HBox();
        buttonMenu.setSpacing(10);

        Label messageLabel = new Label("");

        Button removeButton = new Button("Remove");
        removeButton.setOnAction(e -> {
            //forsøger at fjerne den valgte profil
            //Hvis der kun er en profil, viser den en fejlbesked i stedet
            if (userModel.getSelectedUser().getProfiles().size() > 1) {
                userModel.getSelectedUser().removeProfile(profile);
                window.close();
                userModel.getSelectedUser().setSelectedProfile(userModel.getSelectedUser().getProfiles().get(0));
            } else {
                messageLabel.setText("You can't have less than one profile!");
            }
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> window.close());

        buttonMenu.getChildren().addAll(cancelButton,removeButton);

        vBox.getChildren().addAll(buttonMenu, messageLabel);
        layout.setContent(vBox);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return true;
    }

}
