package br.albatross.open.vnc.services.credentials;

import static br.albatross.open.vnc.configurations.AvailableProperties.CONNECTION_PASSWORD;
import static br.albatross.open.vnc.configurations.AvailableProperties.CONNECTION_USER;

import java.util.Base64;

import br.albatross.open.vnc.configurations.ApplicationProperties;
import java.util.Optional;

public final class ApplicationPropertiesFileBasedCredentialsService implements CredentialsService {

    private final ApplicationProperties properties;

    public ApplicationPropertiesFileBasedCredentialsService(ApplicationProperties properties) {
    	this.properties = properties;
    }

    @Override
    public void saveUser(String username) {

        String encodedUsername = Base64.getEncoder().encodeToString(username.getBytes());
        properties.saveProperty(CONNECTION_USER, encodedUsername);

    }

    @Override
    public void savePassword(String password) {

        String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes());
        properties.saveProperty(CONNECTION_PASSWORD, encodedPassword);

    }

    @Override
    public Optional<String> getUsername() {

        Optional<String> encodedUsername = properties.getProperty(CONNECTION_USER);

        if (encodedUsername.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(new String(Base64.getDecoder().decode(encodedUsername.get())));

    }

    @Override
    public Optional<String> getPassword() {

        Optional<String> encodedPassword = properties.getProperty(CONNECTION_PASSWORD);

        if (encodedPassword.isEmpty()) {
            return Optional.empty();
        }

        String decodedPassword = new String(Base64.getDecoder().decode(encodedPassword.get()));
        return Optional.ofNullable(decodedPassword);

    }

    @Override
    public void clear() {
        properties.clearProperty(CONNECTION_USER);
        properties.clearProperty(CONNECTION_PASSWORD);
    }

}
