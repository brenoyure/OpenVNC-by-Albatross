package br.albatross.open.vnc.controllers;

import br.albatross.open.vnc.services.configurations.Configurations;
import br.albatross.open.vnc.services.gui.GuiService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static br.albatross.open.vnc.services.Alerts.newInstance;
import static javafx.scene.control.Alert.AlertType.INFORMATION;

public class ConfigurationsController extends AbstractConfigurationController {

    @FXML
    private Label savePasswordNoAvailableLabel;

    public ConfigurationsController() {
        super(Configurations.getInstance(), new GuiService());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);
        passwordTextField.setDisable(true);
        savePasswordNoAvailableLabel.setVisible(true);
    }

    @FXML
    protected void saveSettings(ActionEvent event) throws IOException {

        if (podeLimparAsCredenciaisSalvas) {
            configuration.clearCredentials();
        }

        if (!(usuarioTextField.getText() == null || usuarioTextField.getText().isBlank())) {
            configuration.saveUser(usuarioTextField.getText());
        }

        if (!(passwordTextField.getText() == null || passwordTextField.getText().isBlank())) {
            configuration.savePassword(passwordTextField.getText());
        }

        configuration.showHints(toggleHintsButton.isSelected());
        configuration.setToCheckForUpdatesAtStartUpOrNot(toggleAutoUpdates.isSelected());        

        Alert alert = newInstance(
                INFORMATION,
                "Configurações Salvas",
                "Configurações Salvas com Sucesso");
        alert
                .getButtonTypes()
                .removeIf(b -> b.equals(ButtonType.CANCEL));

        alert.show();

//        JOptionPane.showMessageDialog(null, "Configurações Salvas com Sucesso", null, INFORMATION_MESSAGE);

        backToMainButton(event);

        if (toggleAutoUpdates.isSelected()) {
            manualCheckForUpdates(event);
        }

    }

}
