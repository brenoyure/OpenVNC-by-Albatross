package br.albatross.open.vnc.services;

import static br.albatross.open.vnc.configurations.AvailableProperties.DEV_GITHUB_PAGE_LINK;
import static br.albatross.open.vnc.configurations.AvailableProperties.IS_LINUX_OS;
import static java.awt.Desktop.getDesktop;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.event.ActionEvent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

public final class GuiService {

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

    public void handleGitHubClickEvent(ActionEvent clickEvent, TextField textFieldToBeRefocused) throws IOException, URISyntaxException {

        if (IS_LINUX_OS) {

            String[] browseCmdArray = { "browse", DEV_GITHUB_PAGE_LINK };
            Runtime.getRuntime().exec( browseCmdArray );

        } else {

            getDesktop().browse(new URI(DEV_GITHUB_PAGE_LINK));

        }

        refocusTextField(textFieldToBeRefocused);

    }

}
