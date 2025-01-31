package dk.easv.demodynamic;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class HelloController {
    @FXML
    private FlowPane mainPane;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() throws IOException {
        System.out.println("test");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/easv/demodynamic/KittyMugshot.fxml"));
        VBox kitty1 = new VBox();
        ImageView iv1 = new ImageView();

        kitty1.getChildren().add(iv1);
        Label l1 = new Label("Name: Minnie");
        kitty1.getChildren().add(l1);

        mainPane.getChildren().add(kitty1);

        VBox kitty = loader.load();
        for(Node n: kitty.getChildren())
        {
            if(n instanceof ImageView)
            {
                ImageView iv = (ImageView) n;
                System.out.println("IMAGE");
                System.out.println(n);
            }
            else if(n instanceof Label){
                Label l = (Label) n;
                l.setText("Name: Max");
                System.out.println("LABEL");
                System.out.println(n);
            }

        }
        mainPane.getChildren().add(kitty);
    }
}