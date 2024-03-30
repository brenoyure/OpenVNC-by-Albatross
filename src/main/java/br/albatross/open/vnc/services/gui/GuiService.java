package br.albatross.open.vnc.services.gui;

import static br.albatross.open.vnc.configurations.AvailableProperties.DEV_GITHUB_PAGE_LINK;
import static br.albatross.open.vnc.configurations.AvailableProperties.IS_LINUX_OS;
import static br.albatross.open.vnc.configurations.AvailableProperties.IS_WINDOWS_OS;
import static java.awt.Desktop.getDesktop;
import static java.lang.Runtime.getRuntime;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import br.albatross.open.vnc.App;
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

        handleGitHubClickEvent(clickEvent);
        refocusTextField(textFieldToBeRefocused);

    }

    public void handleGitHubClickEvent(ActionEvent actionEvent) throws IOException, URISyntaxException {

        if (IS_LINUX_OS) {

            String[] browseCmdArray = { "browse", DEV_GITHUB_PAGE_LINK };
            getRuntime().exec( browseCmdArray );

        } else {

            getDesktop().browse(new URI(DEV_GITHUB_PAGE_LINK));

        }

    }

    public void goToConfigurationScreen() throws IOException {

        String configurationsController = (IS_WINDOWS_OS) ? "windows-configurations" : "configurations";
        App.setRoot(configurationsController);

    }

}
