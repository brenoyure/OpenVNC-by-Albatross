package br.albatross.open.vnc.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import jakarta.enterprise.context.Dependent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

@Dependent
public class ConfigurationsController extends AbstractConfigurationController {

    @FXML
    private Label savePasswordNoAvailableLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);
        passwordTextField.setDisable(true);
        savePasswordNoAvailableLabel.setVisible(true);
    }

}
