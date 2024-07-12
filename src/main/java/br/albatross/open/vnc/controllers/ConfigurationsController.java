package br.albatross.open.vnc.controllers;

import static br.albatross.open.vnc.configurations.AvailableProperties.IS_WINDOWS_OS;
import static br.albatross.open.vnc.services.Alerts.newInstance;

import static javafx.scene.control.Alert.AlertType.INFORMATION;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import br.albatross.open.vnc.App;
import br.albatross.open.vnc.releases.runnables.CheckForUpdatesRunnable;
import br.albatross.open.vnc.releases.services.ReleasesServiceGithubImplementation;

import br.albatross.open.vnc.services.configurations.Configuration;
import br.albatross.open.vnc.services.configurations.Configurations;
import br.albatross.open.vnc.services.gui.GuiService;
import java.util.concurrent.ExecutorService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
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

    @FXML
    private Label exibirDicasLabel;

    @FXML
    private CheckBox toggleHintsButton;

    @FXML
    private CheckBox toggleAutoUpdates;

    public ConfigurationsController() {

    	this.configuration = Configurations.getInstance();
    	this.guiService = new GuiService();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        passwordTextField.setDisable(true);
        savePasswordNoAvailableLabel.setVisible(true);

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
