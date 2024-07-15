package br.albatross.open.vnc.controllers;

import br.albatross.open.vnc.services.configurations.Configurations;
import br.albatross.open.vnc.services.gui.GuiService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

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

}
