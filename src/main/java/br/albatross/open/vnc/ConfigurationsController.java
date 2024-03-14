package br.albatross.open.vnc;

import java.io.IOException;

import javax.swing.JOptionPane;

import br.albatross.open.vnc.services.configurations.Configuration;
import br.albatross.open.vnc.services.configurations.VncConfigurationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class ConfigurationsController {

    @FXML
    private TextField usuarioTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button saveButton;

    @FXML
    private Button backToMainButton;

    private Configuration configuration;    

    @FXML
    private void saveSettings(ActionEvent event) {
        
        if (configuration == null) {
        	configuration = new VncConfigurationService();
        }

        if (!(usuarioTextField.getText() == null || usuarioTextField.getText().isBlank())) {
        	configuration.saveUser(usuarioTextField.getText());
        }        

        if (!(passwordTextField.getText() == null || passwordTextField.getText().isBlank())) {
        	configuration.savePassword(passwordTextField.getText());
        }

        JOptionPane.showMessageDialog(null, "Configurações Salvas com Sucesso", null, JOptionPane.INFORMATION_MESSAGE);

    }

    @FXML
    private void backToMainButton(ActionEvent event) throws IOException {
        App.setRoot("main");

    }

    @FXML
    private void onKeyTyped(KeyEvent event) {
        saveButton.setDisable(false);
    }

}
