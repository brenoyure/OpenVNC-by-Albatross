package br.albatross.open.vnc;

import br.albatross.open.vnc.services.ConfigurationService;
import br.albatross.open.vnc.services.VncConfigurationService;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;

public class ConfigurationsController {

    @FXML
    private TextField usuarioTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button saveButton;

    @FXML
    private Button backToMainButton;

    private ConfigurationService service;    

    @FXML
    private void saveSettings(ActionEvent event) {
        
        if (service == null) {
            service = new VncConfigurationService();
        }

        if (!(passwordTextField.getText() == null || passwordTextField.getText().isBlank())) {
            service.getPasswordService().savePassword(passwordTextField.getText());
        }

        if (!(usuarioTextField.getText() == null || usuarioTextField.getText().isBlank())) {
            service.getUsuarioService().saveUser(usuarioTextField.getText());
        }

        JOptionPane.showMessageDialog(null, "Configurações Salvas com Sucesso", null, JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(null, "Reinicie o OpenVNC para que as alterações entrem em vigor", null, JOptionPane.INFORMATION_MESSAGE);

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
