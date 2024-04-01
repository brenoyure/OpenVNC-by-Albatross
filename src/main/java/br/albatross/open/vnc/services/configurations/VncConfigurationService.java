package br.albatross.open.vnc.services.configurations;

import br.albatross.open.vnc.services.credentials.CredentialsService;
import java.util.Optional;

public class VncConfigurationService implements Configuration {

    private final CredentialsService credentialsService;

    public VncConfigurationService(CredentialsService credentialsService) {
    	this.credentialsService = credentialsService;
    }

    @Override
    public void saveUser(String username) {
        credentialsService.saveUser(username);
    }

    @Override
    public void savePassword(String plainTextPassword) {
        credentialsService.savePassword(plainTextPassword);;
    }

    @Override
    public Optional<String> getUser() {
        return credentialsService.getUsername();
    }

    @Override
    public Optional<String> getPassword() {
        return credentialsService.getPassword();

    }
    
    @Override
    public void clearCredentials() {
        credentialsService.clear();
    }

}
