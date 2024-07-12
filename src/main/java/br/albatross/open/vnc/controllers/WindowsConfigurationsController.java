package br.albatross.open.vnc.controllers;

import static br.albatross.open.vnc.services.Alerts.newInstance;
import static br.albatross.open.vnc.services.configurations.Configurations.getWindowsSpecificInstance;
import static javafx.scene.control.Alert.AlertType.INFORMATION;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;


import br.albatross.open.vnc.App;
import br.albatross.open.vnc.releases.runnables.CheckForUpdatesRunnable;
import br.albatross.open.vnc.releases.services.ReleasesServiceGithubImplementation;
import br.albatross.open.vnc.services.configurations.Configurations;
import br.albatross.open.vnc.services.configurations.WindowsSpecificConfiguration;
import br.albatross.open.vnc.services.gui.GuiService;
import java.util.concurrent.ExecutorService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;

public class WindowsConfigurationsController extends AbstractConfigurationController {

    @FXML
    private Label selectUltraVNCInstallDirLabel;

    @FXML
    private TextField selectUltraVNCInstallDirTextField;

    @FXML
    private Button autoDetectUltraVNCInstallDirButton;    

    private final WindowsSpecificConfiguration configuration;

    public WindowsConfigurationsController() {
        super(Configurations.getInstance(), new GuiService());
        configuration = getWindowsSpecificInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);
        configuration.getVncDirectory().ifPresent(selectUltraVNCInstallDirTextField::setText);
    }

    @FXML
    protected void selectUltraVNCInstallDir(MouseEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(null);

        if (selectedDirectory == null) {
            return;
        }

        saveButton.setDisable(false);
        selectUltraVNCInstallDirTextField.setText(selectedDirectory.getAbsolutePath());

    }

    @FXML
    private void autoDetectUltraVNCInstallDir(ActionEvent event) {

        configuration.autoDetectVncDirectory().ifPresentOrElse(directory -> {
            selectUltraVNCInstallDirTextField.setText(directory.getAbsolutePath());
            saveButton.setDisable(false);
        }, () -> {
            Alert alert = newInstance(
                    Alert.AlertType.ERROR, 
                    "Pasta do UltraVNC® Viewer não encontrada", 
                    "Pasta do UltraVNC® Viewer não encontrada", 
                    "Clique no campo de texto, a esquerda, e procure pela pasta em que está localizado, o .exe do UltraVNC® Viewer.");
            alert
                .getButtonTypes()
                .removeIf(b -> b.equals(ButtonType.CANCEL));
            
            alert.show();
            
//            JOptionPane.showMessageDialog(null, "Clique no campo de texto, a esquerda, e procure pela pasta em que está localizado, o .exe do UltraVNC® Viewer.", "Pasta do UltraVNC® Viewer não encontrada", JOptionPane.WARNING_MESSAGE);
        });

    }

    @FXML
    protected void saveSettings(ActionEvent event) throws IOException {

        if (!(selectUltraVNCInstallDirTextField.getText() == null || selectUltraVNCInstallDirTextField.getText().isBlank())) {
            configuration.setVncDirectory(selectUltraVNCInstallDirTextField.getText());
        }

        super.saveSettings(event);

    }

}
