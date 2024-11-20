package br.albatross.open.vnc.services.gui;

import static br.albatross.open.vnc.configurations.AvailableProperties.APP_ICON_RESOURCE_PATH;
import static br.albatross.open.vnc.configurations.AvailableProperties.APP_MAIN_WINDOW_TITLE;
import static br.albatross.open.vnc.configurations.AvailableProperties.DEV_GITHUB_PAGE_LINK;
import static br.albatross.open.vnc.configurations.AvailableProperties.IS_LINUX_OS;
import static br.albatross.open.vnc.configurations.AvailableProperties.IS_WINDOWS_OS;
import static java.awt.Desktop.getDesktop;
import static java.lang.Runtime.getRuntime;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import br.albatross.open.vnc.App;
import io.quarkiverse.fx.views.FxViewRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
//import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

@ApplicationScoped
public class GuiService {

    @Inject
    Instance<FXMLLoader> fxmlLoader;    

    @Inject
    FxViewRepository fxViewRepository;

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

    public void goToConfigurationScreen(ActionEvent actionEvent) throws IOException {
        String configurationsController = (IS_WINDOWS_OS) ? "windows-configurations" : "configurations";
        changeScreen(actionEvent, configurationsController);
    }

    public void changeScreen(
            ActionEvent actionEvent,
            String controllerFxmlName) {
        changeScreen(actionEvent, controllerFxmlName, APP_MAIN_WINDOW_TITLE);
    }

    public void changeScreen(ActionEvent actionEvent,
                             String controllerFxmlName,
                             String windowTitle) {

        openNewWindow(actionEvent, controllerFxmlName, windowTitle);
//        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }

    public void openNewWindow(ActionEvent actionEvent,
                             String controllerFxmlName,
                             String windowTitle) {

        createNewStage(
                controllerFxmlName, 
                windowTitle)
        .show();

    }

    public Stage createNewStage(String controllerFxmlName, String windowTitle) {
        try {
            FXMLLoader fxmlLoader = this.fxmlLoader.get();
            fxmlLoader.setLocation(App.class.getResource(controllerFxmlName + ".fxml"));
            Stage stage = fxViewRepository.getPrimaryStage();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle(windowTitle);
            stage.getIcons().add(new Image(APP_ICON_RESOURCE_PATH));

            return stage;

        } catch (IOException e) { throw new RuntimeException(e); }

    }

}
