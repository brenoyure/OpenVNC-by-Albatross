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
    private Button limparCredenciaisSalvasButton;
    
    private boolean podeLimparAsCredenciaisSalvas = false;

    @FXML
    private Hyperlink githubLink;

    private GuiService guiService;    

    private WindowsSpecificConfiguration configuration;

    @FXML
    private Label exibirDicasLabel;

    @FXML
    private CheckBox toggleHintsButton;
    @FXML
    private CheckBox toggleAutoUpdates;

    public WindowsConfigurationsController() {
        this.configuration = getWindowsSpecificInstance();
        this.guiService    = new GuiService();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        configuration.getVncDirectory().ifPresent(selectUltraVNCInstallDirTextField::setText);
        configuration.getUser().ifPresent(savedUsername -> {
            usuarioTextField.setText(savedUsername);
            usuarioTextField.requestFocus();

            for (int i = 0; i < usuarioTextField.lengthProperty().get(); i++) {
                usuarioTextField.forward();
            }

            limparCredenciaisSalvasButton.setDisable(false);
        });

        toggleHintsButton.setSelected(configuration.isShowingHints());
        toggleAutoUpdates.setSelected(configuration.isCheckForUpdatesEnabledAtStartUp());

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

        if (!(selectUltraVNCInstallDirTextField.getText() == null || selectUltraVNCInstallDirTextField.getText().isBlank())) {
            configuration.setVncDirectory(selectUltraVNCInstallDirTextField.getText());
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
    private void githubLinkClicked(ActionEvent event) throws IOException, URISyntaxException {
        guiService.handleGitHubClickEvent(event, usuarioTextField);
        githubLink.setVisited(false);
    }

    @FXML
    private void limparCredenciaisSalvas(ActionEvent event) {
        podeLimparAsCredenciaisSalvas = true;
        usuarioTextField.clear();
        passwordTextField.clear();
        saveButton.setDisable(false);
        usuarioTextField.requestFocus();
    }

    @FXML
    private void toggleHints(ActionEvent event) {

        if (toggleHintsButton.isSelected()) {

            Alert alert = newInstance(
                    INFORMATION,
                    "Dicas do OpenVNC",
                    "Dicas do OpenVNC",
                    "O OpenVNC poderá exibir dicas enquanto a conexão remota não é aceita pelo usuário");
            alert.getButtonTypes().removeIf(b -> b.equals(ButtonType.CANCEL));

            alert.show();

            /*

            JOptionPane.showMessageDialog(null, 
                    "O OpenVNC exibirá dicas enquanto a conexão remota não é aceita pelo usuário", 
                    "Dicas do OpenVNC", 
                    INFORMATION_MESSAGE);

             */
        }        

        saveButton.setDisable(false);

    }

    @FXML
    private void toggleAutoUpdates(ActionEvent event) {

        if (toggleAutoUpdates.isSelected()) {
            
            Alert alert = newInstance(
                    INFORMATION,
                    "Atualizações do OpenVNC",
                    "Atualizações do OpenVNC",
                    "O OpenVNC poderá verificar se há atualizações disponíveis, segundos após iniciar o aplicativo");
            alert.getButtonTypes().removeIf(b -> b.equals(ButtonType.CANCEL));

            alert.show();

            /*

            JOptionPane.showMessageDialog(null, 
                    "O OpenVNC exibirá dicas enquanto a conexão remota não é aceita pelo usuário", 
                    "Dicas do OpenVNC", 
                    INFORMATION_MESSAGE);

             */
        }

        saveButton.setDisable(false);
            
        
    }

    @FXML
    private void manualCheckForUpdates(ActionEvent event) {

        ExecutorService executorService = App.executorService;
        executorService.submit(new CheckForUpdatesRunnable(executorService, new ReleasesServiceGithubImplementation()));

        usuarioTextField.requestFocus();
        usuarioTextField.forward();

    }

}
