package br.albatross.open.vnc.handlers.exception;

import static br.albatross.open.vnc.handlers.messages.MensagemDeErro.VNC_NOT_FOUND_SUMMARY_MESSAGE;
import static br.albatross.open.vnc.handlers.messages.MensagemDeErro.VNC_NOT_FOUND_DETAILED_MESSAGE;
import br.albatross.open.vnc.services.Alerts;
import javafx.application.Platform;
import javafx.scene.control.Alert;


public class VncNotFoundExceptionHandler {

    public static void showNotFoundMessageDialog() {

        Platform.runLater(() -> {

            Alert alert = Alerts.newInstance(
                    Alert.AlertType.ERROR,
                    VNC_NOT_FOUND_SUMMARY_MESSAGE,
                    VNC_NOT_FOUND_SUMMARY_MESSAGE,
                    VNC_NOT_FOUND_DETAILED_MESSAGE);

            alert.show();

        });
        
//        showMessageDialog(null, 
//                VNC_NOT_FOUND_DETAILED_MESSAGE, 
//                VNC_NOT_FOUND_SUMMARY_MESSAGE, 
//                ERROR_MESSAGE);
    }
    
}
