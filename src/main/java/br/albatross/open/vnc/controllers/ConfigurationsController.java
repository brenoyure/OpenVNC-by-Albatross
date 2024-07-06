package br.albatross.open.vnc.controllers;

import static br.albatross.open.vnc.configurations.AvailableProperties.IS_WINDOWS_OS;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import br.albatross.open.vnc.App;
import br.albatross.open.vnc.configurations.ApplicationProperties;
import br.albatross.open.vnc.configurations.ApplicationPropertiesFileBasedConfiguration;
import br.albatross.open.vnc.services.configurations.Configuration;
import br.albatross.open.vnc.services.configurations.VncConfigurationService;
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

public class ConfigurationsController implements Initializable {

    @FXML
    private TextField usuarioTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Label savePasswordNoAvailableLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private Button saveButton;

    @FXML
    private Button backToMainButton;

    @FXML
    private Button limparCredenciaisSalvasButton;

    private boolean podeLimparAsCredenciaisSalvas = false;

    @FXML
    private Hyperlink githubLink;

    private final GuiService guiService;

    private final Configuration configuration;

    public ConfigurationsController() {

    	ApplicationProperties applicationProperties = new ApplicationPropertiesFileBasedConfiguration();
    	CredentialsService credentialsService       = new ApplicationPropertiesFileBasedCredentialsService(applicationProperties);
    	this.configuration                          = new VncConfigurationService(credentialsService, applicationProperties);
    	this.guiService                             = new GuiService();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        passwordTextField.setDisable(true);
        savePasswordNoAvailableLabel.setVisible(true);

        configuration.getUser().ifPresent(savedUsername -> {
            usuarioTextField.setText(savedUsername);
            limparCredenciaisSalvasButton.setDisable(false);
        });

    }

    @FXML
    private void saveSettings(ActionEvent event) throws IOException {

        if (podeLimparAsCredenciaisSalvas) {
            configuration.clearCredentials();
        }

        if (!(usuarioTextField.getText() == null || usuarioTextField.getText().isBlank())) {
            configuration.saveUser(usuarioTextField.getText());
        }

        if (!(passwordTextField.getText() == null || passwordTextField.getText().isBlank())) {
            configuration.savePassword(passwordTextField.getText());
        }

        showMessageDialog(null, "Configurações Salvas com Sucesso", null, INFORMATION_MESSAGE);
        backToMainButton(event);

    }

    @FXML
    private void backToMainButton(ActionEvent event) throws IOException {
        App.setRoot("main");

    }

    @FXML
    private void onKeyTyped(KeyEvent event) {

        if (usuarioTextField.getText().isBlank() || (passwordTextField.getText().isBlank() && IS_WINDOWS_OS)) {
            saveButton.setDisable(true);
            return;
        }

        saveButton.setDisable(false);

    }

    @FXML
    private void githubLinkClicked(ActionEvent event) throws IOException, URISyntaxException {
        guiService.handleGitHubClickEvent(event, usuarioTextField);
        githubLink.setVisited(false);
    }

    @FXML
    private void limparCredenciaisSalvas(ActionEvent event) {
        podeLimparAsCredenciaisSalvas = true;
        usuarioTextField.clear();
//      passwordTextField.clear(); <-- Campo de Senha ainda não implementado no Linux
        saveButton.setDisable(false);
        usuarioTextField.requestFocus();
    }

}
