package br.albatross.open.vnc.services.configurations;

public interface Configuration {

    void saveUser(String username);
    void savePassword(String plainTextPassword);
    void onWindowsSaveVNCDirectory(String absolutePath);

    String getUser();
    String getPassword();
    String onWindowsGetVNCDirectory();

}
