package dk.easv.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SnackbarController {

    @FXML private Label lblWrning;
    @FXML private  ImageView imgIcon;

    public void setMessageAndIcon(String message, Image img){
        lblWrning.setText(message);
        imgIcon.setImage(img);

    };
}
