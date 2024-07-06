package br.albatross.open.vnc.services.configurations;

import static br.albatross.open.vnc.configurations.AvailableProperties.SHOW_HINTS_BEFORE_REMOTE;

import java.util.Optional;

import br.albatross.open.vnc.configurations.ApplicationProperties;
import br.albatross.open.vnc.services.credentials.CredentialsService;

public class VncConfigurationService implements Configuration {

    private final CredentialsService credentialsService;
    private final ApplicationProperties applicationProperties;

    public VncConfigurationService(CredentialsService credentialsService, ApplicationProperties applicationProperties) {
    	this.credentialsService = credentialsService;
        this.applicationProperties = applicationProperties;
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

    @Override
    public boolean isShowingHints() {
        Optional<String> showHintOptional = applicationProperties.getProperty(SHOW_HINTS_BEFORE_REMOTE);

        if (showHintOptional.isEmpty()) {
            applicationProperties.saveProperty(SHOW_HINTS_BEFORE_REMOTE, Boolean.toString(true));
            return true;
        }

        return Boolean.parseBoolean(showHintOptional.get());

    }

    @Override
    public void showHints(boolean trueOrFalse) {
        applicationProperties.saveProperty(SHOW_HINTS_BEFORE_REMOTE, Boolean.toString(trueOrFalse));
    }

}
