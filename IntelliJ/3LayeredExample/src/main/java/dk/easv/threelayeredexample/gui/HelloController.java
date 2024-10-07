package dk.easv.threelayeredexample.gui;

import dk.easv.threelayeredexample.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private TextField txtInput;

    @FXML
    protected void onHelloButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("TextCalcView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        TextCalcController ctrl = fxmlLoader.getController();
        ctrl.setText(txtInput.getText());
        stage.setTitle("Results");
        stage.setScene(scene);
        stage.show();
        // send the text to the new window
        // make calculation of amount of chars in text
    }
}