package br.albatross.open.vnc.handlers.exception;

import static br.albatross.open.vnc.handlers.messages.MensagemDeErro.VNC_NOT_FOUND_SUMMARY_MESSAGE;
import static br.albatross.open.vnc.handlers.messages.MensagemDeErro.VNC_NOT_FOUND_DETAILED_MESSAGE;

import static javax.swing.JOptionPane.showMessageDialog;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

public class VncNotFoundExceptionHandler {

    public static void showNotFoundMessageDialog() {
        showMessageDialog(null, 
                VNC_NOT_FOUND_DETAILED_MESSAGE, 
                VNC_NOT_FOUND_SUMMARY_MESSAGE, 
                ERROR_MESSAGE);
    }
    
}
