package com.javafx.calc.mvc.Model;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Calculate {

    private Label output;
    private Button[] btn_numPad;
    private Button[] btn_funtionsButton;

    public Calculate() {

    }

    public Label getOutput() {
        return output;
    }

    public void setOutput(Label output) {
        this.output = output;
    }

    public Button[] getBtn_numPad() {
        return btn_numPad;
    }

    public void setBtn_numPad(Button[] btn_numPad) {
        this.btn_numPad = btn_numPad;
    }

    public Button[] getBtn_funtionsButton() {
        return btn_funtionsButton;
    }

    public void setBtn_funtionsButton(Button[] btn_funtionsButton) {
        this.btn_funtionsButton = btn_funtionsButton;
    }

}// end classe Calculate
