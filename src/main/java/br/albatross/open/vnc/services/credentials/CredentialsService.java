package br.albatross.open.vnc.services.credentials;

import java.util.Optional;

public interface CredentialsService {

    void saveUser(String username);
    void savePassword(String password);

    Optional<String> getUsername();
    Optional<String> getPassword();

}
