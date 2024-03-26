package br.albatross.open.vnc;

import static br.albatross.open.vnc.configurations.AvailableProperties.IS_LINUX_OS;
import static br.albatross.open.vnc.configurations.AvailableProperties.IS_WINDOWS_OS;
import java.io.IOException;

import javax.swing.JOptionPane;

import br.albatross.open.vnc.services.configurations.Configuration;
import br.albatross.open.vnc.services.configurations.VncConfigurationService;
import java.awt.Window;
import java.io.File;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;

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
    private Label onWindowsSelectUltraVNCInstallDirLabel;

    @FXML
    private Button onWindowsSelectUltraVNCInstallDirButton;

    @FXML
    private TextField onWindowsSelectUltraVNCInstallDirTextField;

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

        if (IS_WINDOWS_OS) {

            onWindowsSelectUltraVNCInstallDirLabel.setVisible(true);
            onWindowsSelectUltraVNCInstallDirTextField.setVisible(true);
            onWindowsSelectUltraVNCInstallDirButton.setVisible(true);

            String vncInstallDir = configuration.onWindowsGetVNCDirectory();

            if (vncInstallDir == null) {
                return;
            }

            onWindowsSelectUltraVNCInstallDirTextField.setText(vncInstallDir);

        }

    }

    @FXML
    private void saveSettings(ActionEvent event) throws IOException {

        if (!(usuarioTextField.getText() == null || usuarioTextField.getText().isBlank())) {
            configuration.saveUser(usuarioTextField.getText());
        }

        if (!(passwordTextField.getText() == null || passwordTextField.getText().isBlank())) {
            configuration.savePassword(passwordTextField.getText());
        }

        if (IS_WINDOWS_OS) {
            String vncInstallDir = onWindowsSelectUltraVNCInstallDirTextField.getText();
            
            if (!(vncInstallDir == null || vncInstallDir.isBlank())) {
                configuration.onWindowsSaveVNCDirectory(vncInstallDir);
            }
            
        }

        JOptionPane.showMessageDialog(null, "Configurações Salvas com Sucesso", null, JOptionPane.INFORMATION_MESSAGE);
        backToMainButton(event);

    }

    @FXML
    private void onWindowsSelectUltraVNCInstallDir(MouseEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(null);

        if (selectedDirectory == null) {
            return;
        }

        saveButton.setDisable(false);
        onWindowsSelectUltraVNCInstallDirTextField.setText(selectedDirectory.getAbsolutePath());

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
