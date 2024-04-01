package br.albatross.open.vnc.services.configurations;

import java.util.Optional;

public interface Configuration {

    void saveUser(String username);
    void savePassword(String plainTextPassword);

    Optional<String> getUser();
    Optional<String> getPassword();

    void clearCredentials();

}
