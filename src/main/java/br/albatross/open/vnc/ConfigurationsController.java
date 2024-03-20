package br.albatross.open.vnc;

import static br.albatross.open.vnc.configurations.AvailableProperties.IS_LINUX_OS;
import static br.albatross.open.vnc.configurations.AvailableProperties.IS_WINDOWS_OS;
import java.io.IOException;

import javax.swing.JOptionPane;

import br.albatross.open.vnc.services.configurations.Configuration;
import br.albatross.open.vnc.services.configurations.VncConfigurationService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    private Button saveButton;

    @FXML
    private Button backToMainButton;

    private Configuration configuration;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if (configuration == null) {
            configuration = new VncConfigurationService();
        }

        if (IS_LINUX_OS) {
            passwordTextField.setDisable(true);
            savePasswordNoAvailableLabel.setVisible(true);
        }

        String username = configuration.getUser();

        if (username == null || username.isBlank()) {
            return;
        }

        usuarioTextField.setText(username);

    }

    @FXML
    private void saveSettings(ActionEvent event) throws IOException {

        if (!(usuarioTextField.getText() == null || usuarioTextField.getText().isBlank())) {
            configuration.saveUser(usuarioTextField.getText());
        }

        if (!(passwordTextField.getText() == null || passwordTextField.getText().isBlank())) {
            configuration.savePassword(passwordTextField.getText());
        }

        JOptionPane.showMessageDialog(null, "Configurações Salvas com Sucesso", null, JOptionPane.INFORMATION_MESSAGE);
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

}
