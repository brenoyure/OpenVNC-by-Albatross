package br.albatross.open.vnc.services;

import java.io.IOException;

public class WindowsUltraVNCUserService implements UsuarioService {

    @Override
    public void saveUser(String username) {

        try {

            String[] saveCommand = { "setx", "VNC_USER", username };
            Runtime.getRuntime().exec(saveCommand);

        } catch (IOException ex) { throw new RuntimeException(ex); }

    }

}
