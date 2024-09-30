package dk.easv.threelayeredexample.gui;

import dk.easv.threelayeredexample.bll.Calculator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TextCalcController {
    @FXML
    private Label lblCompleteText;

    @FXML
    private Label lblCharsCount;

    private Calculator calc = new Calculator();

    private String txt = "";

    public void setText(String txt){
        this.txt = txt;
        lblCompleteText.setText(txt);
        lblCharsCount.setText(""+calc.countChars(txt));
    }

}
