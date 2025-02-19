package br.albatross.open.vnc.controllers;

import static br.albatross.open.vnc.configurations.AvailableProperties.IS_WINDOWS_OS;
import static br.albatross.open.vnc.services.Alerts.newInstance;
import static javafx.scene.control.Alert.AlertType.INFORMATION;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;

import br.albatross.open.vnc.releases.runnables.CheckForUpdatesRunnable;
import br.albatross.open.vnc.releases.services.ReleasesServiceGithubImplementation;
import br.albatross.open.vnc.runnables.OpenVNCThreadPool;
import br.albatross.open.vnc.services.configurations.Configuration;
import br.albatross.open.vnc.services.gui.GuiService;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

@Dependent
public abstract class AbstractConfigurationController implements Initializable {

    @FXML
    protected TextField usuarioTextField;

    @FXML
    protected PasswordField passwordTextField;

    @FXML
    protected Label passwordLabel;

    @FXML
    protected Button saveButton;

    @FXML
    protected Button backToMainButton;

    @FXML
    protected Button limparCredenciaisSalvasButton;

    protected boolean podeLimparAsCredenciaisSalvas = false;

    @FXML
    protected Hyperlink githubLink;

    @FXML
    protected Label exibirDicasLabel;

    @FXML
    protected CheckBox toggleHintsButton;

    @FXML
    protected CheckBox toggleAutoUpdates;

    @FXML
    protected ProgressBar checkingForUpdatesProgressBar;

    @FXML
    protected Button manualCheckForUpdatesButton;

    @Inject
    @OpenVNCThreadPool
    ExecutorService executorService;

    @Inject
    FXMLLoader fxmlLoader;

    @Inject
    Configuration configuration;    

    @Inject
    GuiService guiService;    

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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

        checkingForUpdatesProgressBar.setVisible(false);
        
    }

    @FXML
    protected void backToMainButton(ActionEvent event) throws IOException {
        guiService.changeScreen(event, "main");
    }

    @FXML
    protected void onKeyTyped(KeyEvent event) {

        if (usuarioTextField.getText().isBlank() || (passwordTextField.getText().isBlank() && IS_WINDOWS_OS)) {
            saveButton.setDisable(true);
            return;
        }

        saveButton.setDisable(false);

    }

    @FXML
    protected void limparCredenciaisSalvas(ActionEvent event) {
        podeLimparAsCredenciaisSalvas = true;
        usuarioTextField.clear();
        passwordTextField.clear();
        saveButton.setDisable(false);
        usuarioTextField.requestFocus();
    }

    @FXML
    protected void githubLinkClicked(ActionEvent event) throws IOException, URISyntaxException {
        guiService.handleGitHubClickEvent(event, usuarioTextField);
        githubLink.setVisited(false);
    }

    @FXML
    protected void toggleHints(ActionEvent event) {

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
    protected void toggleAutoUpdates(ActionEvent event) {

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
    protected void manualCheckForUpdates(ActionEvent event) {

        executorService.submit(
                new CheckForUpdatesRunnable(executorService,
                        new ReleasesServiceGithubImplementation(),
                        checkingForUpdatesProgressBar,
                        manualCheckForUpdatesButton));

        usuarioTextField.requestFocus();
        usuarioTextField.forward();

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

        backToMainButton(event);

        if (toggleAutoUpdates.isSelected()) {
            manualCheckForUpdates(event);
        }

    }

}
