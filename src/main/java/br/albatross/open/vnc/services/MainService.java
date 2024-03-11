package br.albatross.open.vnc.services;

import javafx.event.ActionEvent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

public class MainService {

    public void handleHostRadioButtonClick(TextField textField, ActionEvent event, String inputText) {
        textField.setText(inputText);
        refocusTextField(textField);
    }

    public void refocusTextFieldAfterHyperLinkClickEvent(Hyperlink hyperLink, TextField textField) {
        hyperLink.setVisited(false);
        refocusTextField(textField);
    }

    private void refocusTextField(TextField textField) {
        textField.requestFocus();
        textField.forward();
    }

}
