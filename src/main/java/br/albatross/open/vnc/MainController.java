package br.albatross.open.vnc;

import br.albatross.connections.Connection;
import br.albatross.connections.builders.ConnectionBuilder;
import br.albatross.connections.builders.VncConnectionBuilder;
import br.albatross.connections.starters.ConnectionStarter;
import br.albatross.connections.starters.VNCConnectionStarter;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseDragEvent;

public class MainController {

    @FXML
    private TextField host;
    @FXML
    private ToggleGroup andares_radio_button;
    @FXML
    private ToggleGroup maquinas_radio_button;
    
    
    private final String userName = System.getProperty("user.name");
    private final String password = System.getenv("domain_password");
    private final String vncHomeDir = "C:\\Program Files\\uvnc_baba\\UltraVNC";
    
    
    
    

    private void switchToSecondary() throws IOException {
        App.setRoot("main");
    }

    @FXML
    private void connectBtnClicked(ActionEvent event) {
        
        ConnectionBuilder builder = new VncConnectionBuilder(vncHomeDir);
        Connection connection = builder.createConnection(vncHomeDir, userName, password);
        ConnectionStarter starter = new VNCConnectionStarter(builder);
        
        try {
            starter.startConnection(connection);
            
        } catch (InterruptedException | IOException ex) {
            ex.printStackTrace();
        }
        
    }

    @FXML
    private void andarTerroRadioBtnClicked(ActionEvent event) {
        host.setText("10.40.10.");
    }

    @FXML
    private void primeiroAndarRadioBtnClicked(ActionEvent event) {
        host.setText("10.40.1.");
    }

    @FXML
    private void quartoAndarRadioBtnClicked(ActionEvent event) {
        host.setText("10.40.4.");
    }

    @FXML
    private void quintoAndarRadioBtnClicked(ActionEvent event) {
        host.setText("10.40.5.");
    }

    @FXML
    private void sextoAndarRadioBtnClicked(ActionEvent event) {
        host.setText("10.40.6.");
    }

    @FXML
    private void setimoAndarRadioBtnClicked(ActionEvent event) {
        host.setText("10.40.7.");
    }

    @FXML
    private void oitavoAndarRadioBtnClicked(ActionEvent event) {
        host.setText("10.40.8.");
    }

    @FXML
    private void nonoAndarRadioBtnClicked(ActionEvent event) {
        host.setText("10.40.9.");
    }

    @FXML
    private void maquinaDellRadioBtnClicked(ActionEvent event) {
        host.setText("W142");
    }

    @FXML
    private void maquinaSpaceBRRadioBtnClicked(ActionEvent event) {
        host.setText("W145");
    }

    @FXML
    private void maquinaHPRadioBtnClicked(ActionEvent event) {
        host.setText("W200");
    }

    @FXML
    private void maquinaDatenRadioBtnClicked(ActionEvent event) {
        host.setText("W204");
    }

    @FXML
    private void mouseOverHostTextField(MouseDragEvent event) {
    }

}
