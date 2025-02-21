package dk.easv.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class HelloController {
    @FXML
    private  VBox mainBox;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() throws IOException {
        welcomeText.setText("Welcome to JavaFX Application!");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("snackbar.fxml"));
        Parent root = loader.load();
        SnackbarController ctrl = loader.getController();
        ctrl.setMessageAndIcon("Hello", null);

        mainBox.getChildren().add(root);


    }
}