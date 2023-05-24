package br.albatross.open.vnc.connections.handlers.exception;

import static br.albatross.open.vnc.connections.handlers.messages.MensagemDeErro.VNC_FOLDER_NOT_FOUND_SUMMARY_MESSAGE;
import static br.albatross.open.vnc.connections.handlers.messages.MensagemDeErro.VNC_FOLDER_NOT_FOUND_DETAILED_MESSAGE;

import static javax.swing.JOptionPane.showMessageDialog;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

public class VncFolderNotFoundExceptionHandler {

    public static void showFolderNotFoundMessageDialog() {
        showMessageDialog(null, 
                VNC_FOLDER_NOT_FOUND_DETAILED_MESSAGE, 
                VNC_FOLDER_NOT_FOUND_SUMMARY_MESSAGE, 
                ERROR_MESSAGE);
    }
    
}
