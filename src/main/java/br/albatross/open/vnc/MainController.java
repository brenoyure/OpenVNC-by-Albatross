package br.albatross.open.vnc;

import br.albatross.open.vnc.connections.Connection;
import br.albatross.open.vnc.builders.ConnectionBuilder;
import br.albatross.open.vnc.builders.SsVncConnectionBuilder;
import br.albatross.open.vnc.builders.VncConnectionBuilder;
import br.albatross.open.vnc.services.MainService;
import br.albatross.open.vnc.services.PasswordService;
import br.albatross.open.vnc.starters.ConnectionStarter;
import br.albatross.open.vnc.starters.VNCConnectionStarter;

import static java.awt.Desktop.getDesktop;
import java.awt.TrayIcon;

import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;

public class MainController {

    /**
     * Represents the text field in the GUI, where users type the IP or
     * Hostname.
     */
    @FXML
    private TextField host;

    /**
     * Represents the Host-Radio-Button group in the GUI. This group of radio
     * buttons is used so that users can select only ONE button.
     */
    @FXML
    private ToggleGroup host_radio_button;

    /**
     * Represents connect button in the GUI's.
     */
    @FXML
    private Button connectBtn;

    /**
     * Represents the github hyperlink in the left conner of the GUI.
     */
    @FXML
    private Hyperlink githubLink;

    /**
     * Represents connection user name, that is recovered by the "user.name"
     * System Property. Basically, the current logged user.
     */
    private final String userName = System.getenv("VNC_USER");
    private final String password = System.getenv("domain_password");

    private static final String OS_NAME = System.getProperty("os.name");

    private ConnectionBuilder builder;

    private ConnectionStarter connectionStarter;

    private MainService service;

    private PasswordService passwordService;

    @FXML
    private Hyperlink changePasswordLink;

    public MainController() {
        builder = (OS_NAME.contains("Windows")) ? builder = new VncConnectionBuilder() : new SsVncConnectionBuilder();
        connectionStarter = new VNCConnectionStarter(builder);
        service = new MainService();
    } 

    /**
     * Triggers the Builder and Starter components to build and start a VNC
     * Connection.
     * 
     * If the target platform is Windows® VncConnection Builder will be created.
     * IF it's a Linux, a SSVNC will be created.
     *
     * @param btnClicked
     */
    @FXML
    private void connectBtnClicked(ActionEvent btnClicked) {

        Connection connection = builder.createConnection(host.getText(), userName, password);
        connectionStarter.startConnection(connection);

    }

    @FXML
    private void changePasswordLinkClicked(ActionEvent event) {
       if (!OS_NAME.contains("Windows")) {
           changePasswordLink.setDisable(true);
           changePasswordLink.setVisited(false);
           JOptionPane.showMessageDialog(null, "Opção de Salvar a senha de acesso ainda não disponível no Linux.", "Opção Não Disponivel", JOptionPane.WARNING_MESSAGE);
           changePasswordLink.setText("Opção Atualmente Indisponível");
       }
       
       else {

           String password = JOptionPane.showInputDialog(null, "Você pode salvar sua senha de acesso, geralmente a senha de domínio, para não precisar informa-la no UltraVNC® sempre que for realizar um acesso remoto.", "Salvar Senha de Acesso");

           if (password.isBlank()) {
               JOptionPane.showMessageDialog(null, "Senha não pode ficar em branco", "Senha Em Branco", JOptionPane.ERROR_MESSAGE);
               return;
           }

           passwordService.savePassword(password);

       }

       service.refocusTextFieldAfterHyperLinkClickEvent(changePasswordLink, host);

    }

    /**
     * Redirects the user to the Github Project Creator's page.
     *
     * @param mouseClick when the link gets clicked by the user.
     * @throws IOException if URI is wrong or inacessible.
     * @throws URISyntaxException if URI os wrong.
     */
    @FXML
    private void githubLinkClicked(ActionEvent mouseClick) throws IOException, URISyntaxException {

        if (OS_NAME.contains("Linux")) {
            Runtime.getRuntime().exec("browse https://github.com/brenoyure");
        }

        else {
            getDesktop().browse(new URI("https://github.com/brenoyure"));

        }

        service.refocusTextFieldAfterHyperLinkClickEvent(githubLink, host);

    }

    /**
     * If host/ip text field is empty, this method will disable the Connect
     * Button in the GUI.
     *
     * @param keyType keys being typed.
     */
    @FXML
    private void hostBeingTyped(KeyEvent keyType) {

        if (host.getText().isBlank()) {
            connectBtn.setDisable(true);
            return;

        }

        connectBtn.setDisable(false);

    }

    private void handleHostRadioButtonClick(ActionEvent event, String hostnameOrIp) {
        service.handleHostRadioButtonClick(host, event, hostnameOrIp);
        connectBtn.setDisable(true);
    }

    @FXML
    private void andarTerroRadioBtnClicked(ActionEvent event) {
        handleHostRadioButtonClick(event, "10.40.10.");
    }

    @FXML
    private void primeiroAndarRadioBtnClicked(ActionEvent event) {
        handleHostRadioButtonClick(event, "10.40.1.");
    }

    @FXML
    private void segundoAndarRadioBtnClicked(ActionEvent event) {
        handleHostRadioButtonClick(event, "10.40.2.");
    }

    @FXML
    private void terceiroAndarRadioBtnClicked(ActionEvent event) {
        handleHostRadioButtonClick(event, "10.40.3.");
    }

    @FXML
    private void quartoAndarRadioBtnClicked(ActionEvent event) {
        handleHostRadioButtonClick(event, "10.40.4.");
    }

    @FXML
    private void quintoAndarRadioBtnClicked(ActionEvent event) {
        handleHostRadioButtonClick(event, "10.40.5.");
    }

    @FXML
    private void sextoAndarRadioBtnClicked(ActionEvent event) {
        handleHostRadioButtonClick(event, "10.40.6.");
    }

    @FXML
    private void setimoAndarRadioBtnClicked(ActionEvent event) {
        handleHostRadioButtonClick(event, "10.40.7.");
    }

    @FXML
    private void oitavoAndarRadioBtnClicked(ActionEvent event) {
        handleHostRadioButtonClick(event, "10.40.8.");
    }

    @FXML
    private void nonoAndarRadioBtnClicked(ActionEvent event) {
        handleHostRadioButtonClick(event, "10.40.9.");
    }

    @FXML
    private void maquinaDellRadioBtnClicked(ActionEvent event) {
        handleHostRadioButtonClick(event, "W142");
    }

    @FXML
    private void maquinaSpaceBRRadioBtnClicked(ActionEvent event) {
        handleHostRadioButtonClick(event, "W145");
    }

    @FXML
    private void maquinaHPRadioBtnClicked(ActionEvent event) {
        handleHostRadioButtonClick(event, "W200");
    }

    @FXML
    private void maquinaDatenRadioBtnClicked(ActionEvent event) {
        handleHostRadioButtonClick(event, "W204");
    }

    @FXML
    private void casaAmarelaTransporteRadioBtnClicked(ActionEvent event) {
        handleHostRadioButtonClick(event, "192.168.30.");
    }

    @FXML
    private void suporteRadioBtnClicked(ActionEvent event) {
        handleHostRadioButtonClick(event, "10.40.50.");
    }

}
