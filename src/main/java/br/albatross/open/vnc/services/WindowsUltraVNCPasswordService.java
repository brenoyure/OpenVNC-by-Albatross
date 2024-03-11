package br.albatross.open.vnc.services;

import java.io.IOException;

public class WindowsUltraVNCPasswordService implements PasswordService {

    @Override
    public void savePassword(String password) {

        try {

            String[] saveCommand = { "setx", "domain_password", password };
            Runtime.getRuntime().exec(saveCommand);

        } catch (IOException ex) { throw new RuntimeException(ex); }

    }

}
