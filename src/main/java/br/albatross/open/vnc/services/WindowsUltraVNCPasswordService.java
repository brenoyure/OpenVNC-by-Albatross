package br.albatross.open.vnc.services;

import java.io.IOException;

public class WindowsUltraVNCPasswordService implements PasswordService {

    @Override
    public void savePassword(String password) {

        try {

            String[] saveCommand = new String[1];
            saveCommand[0] = String.format("@setx domain_password %s", password);
            Runtime.getRuntime().exec(saveCommand);

        } catch (IOException ex) { throw new RuntimeException(ex); }

    }

}
