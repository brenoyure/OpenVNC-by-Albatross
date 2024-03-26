package br.albatross.open.vnc.services.credentials;

import static br.albatross.open.vnc.configurations.AvailableProperties.CONNECTION_PASSWORD;
import static br.albatross.open.vnc.configurations.AvailableProperties.CONNECTION_USER;

import java.util.Base64;

import br.albatross.open.vnc.configurations.ApplicationPropertiesFileBasedConfiguration;

public final class ApplicationPropertiesFileBasedCredentialsService implements CredentialsService {

    private ApplicationPropertiesFileBasedConfiguration properties;

    public ApplicationPropertiesFileBasedCredentialsService(ApplicationPropertiesFileBasedConfiguration properties) {
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
    public String getUsername() {

        String encodedUsername = properties.getProperty(CONNECTION_USER);

        if (encodedUsername == null || encodedUsername.isBlank()) {
            return null;
        }

        String decodedUsername = new String(Base64.getDecoder().decode(encodedUsername));
        return decodedUsername;

    }

    @Override
    public String getPassword() {

        String encodedPassword = properties.getProperty(CONNECTION_PASSWORD);

        if (encodedPassword == null || encodedPassword.isBlank()) {
            return null;
        }

        String decodedPassword = new String(Base64.getDecoder().decode(encodedPassword));

        return decodedPassword;

    }

}
