package br.albatross.open.vnc.handlers.exception;

import br.albatross.open.vnc.handlers.messages.MensagemDeErro;

import static javax.swing.JOptionPane.showMessageDialog;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

public class VncInvalidPasswordExceptionHandler {
    
    public static void showInvalidPasswordMessageDialog() {
        showMessageDialog(null, 
                MensagemDeErro.VNC_PASSWORD_NOT_VALID_DETAILED_MESSAGE, 
                MensagemDeErro.VNC_PASSWORD_NOT_VALID_SUMMARY_MESSAGE, 
                ERROR_MESSAGE);
    }
    
}
