package br.albatross.open.vnc.controllers;

import br.albatross.open.vnc.builders.ConnectionBuilder;
import br.albatross.open.vnc.builders.ConnectionBuilders;
import br.albatross.open.vnc.builders.SsVncConnectionBuilder;
import br.albatross.open.vnc.builders.UltraVncConnectionBuilder;
import br.albatross.open.vnc.configurations.ApplicationProperties;
import br.albatross.open.vnc.configurations.ApplicationPropertiesFileBasedConfiguration;
import br.albatross.open.vnc.configurations.AvailableHosts;
import br.albatross.open.vnc.connections.Connection;
import br.albatross.open.vnc.runnables.OpenVNCConnectionRunnable;
import br.albatross.open.vnc.runnables.ShowHintRunnable;
import br.albatross.open.vnc.services.configurations.*;
import br.albatross.open.vnc.services.credentials.ApplicationPropertiesFileBasedCredentialsService;
import br.albatross.open.vnc.services.credentials.CredentialsService;
import br.albatross.open.vnc.services.gui.GuiService;
import br.albatross.open.vnc.services.hints.HintService;
import br.albatross.open.vnc.services.hints.HintServiceImpl;
import br.albatross.open.vnc.starters.ConnectionStarter;
import br.albatross.open.vnc.starters.VncConnectionStarter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.net.URISyntaxException;

import static br.albatross.open.vnc.App.executorService;
import static br.albatross.open.vnc.builders.ConnectionBuilders.*;
import static br.albatross.open.vnc.configurations.AvailableProperties.IS_WINDOWS_OS;
import static br.albatross.open.vnc.services.configurations.Configurations.getInstance;
import static br.albatross.open.vnc.services.configurations.Configurations.getWindowsSpecificInstance;

public final class MainController {

    /**
     * Represents the text field in the GUI, where users type the IP or
     * Hostname.
     */
    @FXML
    private TextField host;

    /**
     * Represents connect button in the GUI's.
     */
    @FXML
    private Button connectBtn;

    /**
     * Represents the GitHub hyperlink in the left conner of the GUI.
     */
    @FXML
    private Hyperlink githubLink;

    @FXML
    private Hyperlink changePasswordLink;

    private ConnectionBuilder connectionBuilder;

    private final Configuration configuration;

    private final GuiService service;

    private final HintService<String> hintService;

    public MainController() {

        service = new GuiService();

        this.configuration = IS_WINDOWS_OS ?
                getWindowsSpecificInstance() :
                getInstance();

        hintService = new HintServiceImpl();

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
    public void connectBtnClicked(ActionEvent btnClicked) throws Exception {

    	if (connectionBuilder == null) {

            connectionBuilder = newInstance();

    	}

        Connection connection = connectionBuilder.createConnection(host.getText());

        if (configuration.isShowingHints()) {
            executorService.submit(new ShowHintRunnable(hintService, executorService));
        }

        executorService.submit(new OpenVNCConnectionRunnable(connection, configuration)).get();

        host.requestFocus();
        host.forward();

    }

    @FXML
    private void changePasswordLinkClicked(ActionEvent event) throws IOException {

        service.goToConfigurationScreen();

    }

    /**
     * Redirects the user to the Github Project Creator's page.
     *
     * @param mouseClickEvent when the link gets clicked by the user.
     * @throws IOException if URI is wrong or inaccessible.
     * @throws URISyntaxException if URI os wrong.
     */
    @FXML
    private void githubLinkClicked(ActionEvent mouseClickEvent) throws IOException, URISyntaxException {

       service.handleGitHubClickEvent(mouseClickEvent, host);
       githubLink.setVisited(false);

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
        handleHostRadioButtonClick(event, AvailableHosts.HOST_ANDAR_TERREO_TEMPLATE);
    }

    @FXML
    private void primeiroAndarRadioBtnClicked(ActionEvent event) {
        handleHostRadioButtonClick(event, AvailableHosts.HOST_PRIMEIRO_ANDAR_TEMPLATE);
    }

    @FXML
    private void segundoAndarRadioBtnClicked(ActionEvent event) {
        handleHostRadioButtonClick(event, AvailableHosts.HOST_SEGUNDO_ANDAR_TEMPLATE);
    }

    @FXML
    private void terceiroAndarRadioBtnClicked(ActionEvent event) {
        handleHostRadioButtonClick(event, AvailableHosts.HOST_TERCEIRO_ANDAR_TEMPLATE);
    }

    @FXML
    private void quartoAndarRadioBtnClicked(ActionEvent event) {
        handleHostRadioButtonClick(event, AvailableHosts.HOST_QUARTO_ANDAR_TEMPLATE);
    }

    @FXML
    private void quintoAndarRadioBtnClicked(ActionEvent event) {
        handleHostRadioButtonClick(event, AvailableHosts.HOST_QUINTO_ANDAR_TEMPLATE);
    }

    @FXML
    private void sextoAndarRadioBtnClicked(ActionEvent event) {
        handleHostRadioButtonClick(event, AvailableHosts.HOST_SEXTO_ANDAR_TEMPLATE);
    }

    @FXML
    private void setimoAndarRadioBtnClicked(ActionEvent event) {
        handleHostRadioButtonClick(event, AvailableHosts.HOST_SETIMO_ANDAR_TEMPLATE);
    }

    @FXML
    private void oitavoAndarRadioBtnClicked(ActionEvent event) {
        handleHostRadioButtonClick(event, AvailableHosts.HOST_OITAVO_ANDAR_TEMPLATE);
    }

    @FXML
    private void nonoAndarRadioBtnClicked(ActionEvent event) {
        handleHostRadioButtonClick(event, AvailableHosts.HOST_NONO_ANDAR_TEMPLATE);
    }

    @FXML
    private void maquinaDellRadioBtnClicked(ActionEvent event) {
        handleHostRadioButtonClick(event, AvailableHosts.HOST_MAQUINA_DELL_TEMPLATE);
    }

    @FXML
    private void maquinaSpaceBRRadioBtnClicked(ActionEvent event) {
        handleHostRadioButtonClick(event, AvailableHosts.HOST_MAQUINA_SPACEBR_TEMPLATE);
    }

    @FXML
    private void maquinaHPRadioBtnClicked(ActionEvent event) {
        handleHostRadioButtonClick(event, AvailableHosts.HOST_MAQUINA_HP_TEMPLATE);
    }

    @FXML
    private void maquinaDatenRadioBtnClicked(ActionEvent event) {
        handleHostRadioButtonClick(event, AvailableHosts.HOST_MAQUINA_DATEN_TEMPLATE);
    }

    @FXML
    private void casaAmarelaTransporteRadioBtnClicked(ActionEvent event) {
        handleHostRadioButtonClick(event, AvailableHosts.HOST_CASA_AMARELA_TRANSPORTE_TEMPLATE);
    }

    @FXML
    private void suporteRadioBtnClicked(ActionEvent event) {
        handleHostRadioButtonClick(event, AvailableHosts.HOST_REDE_50_TEMPLATE);
    }

}
