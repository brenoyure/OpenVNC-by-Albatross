package br.albatross.open.vnc.controllers;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import br.albatross.open.vnc.App;
import br.albatross.open.vnc.configurations.ApplicationProperties;
import br.albatross.open.vnc.configurations.ApplicationPropertiesFileBasedConfiguration;
import br.albatross.open.vnc.services.configurations.WindowsSpecificConfiguration;
import br.albatross.open.vnc.services.configurations.WindowsVncConfigurationService;
import br.albatross.open.vnc.services.credentials.ApplicationPropertiesFileBasedCredentialsService;
import br.albatross.open.vnc.services.credentials.CredentialsService;
import br.albatross.open.vnc.services.gui.GuiService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;

public class WindowsConfigurationsController implements Initializable {

    @FXML
    private TextField usuarioTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Label passwordLabel;

    @FXML
    private Label selectUltraVNCInstallDirLabel;

    @FXML
    private TextField selectUltraVNCInstallDirTextField;

    @FXML
    private Button autoDetectUltraVNCInstallDirButton;    

    @FXML
    private Button saveButton;

    @FXML
    private Button backToMainButton;

    @FXML
    private Hyperlink githubLink;

    private GuiService guiService;    

    private WindowsSpecificConfiguration configuration;

    public WindowsConfigurationsController() {

    	ApplicationProperties applicationProperties = new ApplicationPropertiesFileBasedConfiguration();
    	CredentialsService credentialsService       = new ApplicationPropertiesFileBasedCredentialsService(applicationProperties);
    	this.configuration                          = new WindowsVncConfigurationService(applicationProperties, credentialsService);
        this.guiService                             = new GuiService();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        configuration.getVncDirectory().ifPresent(selectUltraVNCInstallDirTextField::setText);
        configuration.getUser().ifPresent(usuarioTextField::setText);

    }

    @FXML
    private void saveSettings(ActionEvent event) throws IOException {

        if (!(usuarioTextField.getText() == null || usuarioTextField.getText().isBlank())) {
            configuration.saveUser(usuarioTextField.getText());
        }

        if (!(passwordTextField.getText() == null || passwordTextField.getText().isBlank())) {
            configuration.savePassword(passwordTextField.getText());
        }

        if (!(selectUltraVNCInstallDirTextField.getText() == null || selectUltraVNCInstallDirTextField.getText().isBlank())) {
            configuration.setVncDirectory(selectUltraVNCInstallDirTextField.getText());
        }

        JOptionPane.showMessageDialog(null, "Configurações Salvas com Sucesso", null, INFORMATION_MESSAGE);
        backToMainButton(event);

    }

    @FXML
    private void selectUltraVNCInstallDir(MouseEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(null);

        if (selectedDirectory == null) {
            return;
        }

        saveButton.setDisable(false);
        selectUltraVNCInstallDirTextField.setText(selectedDirectory.getAbsolutePath());

    }

    @FXML
    private void backToMainButton(ActionEvent event) throws IOException {
        App.setRoot("main");

    }

    @FXML
    private void onKeyTyped(KeyEvent event) {

        if (usuarioTextField.getText().isBlank() || passwordTextField.getText().isBlank()) {
            saveButton.setDisable(true);
            return;
        }

        saveButton.setDisable(false);

    }

    @FXML
    private void autoDetectUltraVNCInstallDir(ActionEvent event) {

        configuration.autoDetectVncDirectory().ifPresentOrElse(directory -> {
            selectUltraVNCInstallDirTextField.setText(directory.getAbsolutePath());
            saveButton.setDisable(false);
        }, () -> {
            JOptionPane.showMessageDialog(null, "Clique no campo de texto, a esquerda, e procure pela pasta em que está localizado, o .exe do UltraVNC® Viewer.", "Pasta do UltraVNC® Viewer não encontrada", JOptionPane.WARNING_MESSAGE);
        });

    }

    @FXML
    private void githubLinkClicked(ActionEvent event) throws IOException, URISyntaxException {
        guiService.handleGitHubClickEvent(event, usuarioTextField);
        githubLink.setVisited(false);
    }

}
