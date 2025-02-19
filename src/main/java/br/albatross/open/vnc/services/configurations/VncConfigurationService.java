package br.albatross.open.vnc.services.configurations;

import static br.albatross.open.vnc.configurations.AvailableProperties.CHECK_FOR_UPDATES_AT_STARTUP;
import static br.albatross.open.vnc.configurations.AvailableProperties.SHOW_HINTS_BEFORE_REMOTE;

import java.util.Optional;

import br.albatross.open.vnc.configurations.ApplicationProperties;
import br.albatross.open.vnc.services.credentials.CredentialsService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class VncConfigurationService implements Configuration {

    @Inject
    CredentialsService credentialsService;

    @Inject
    ApplicationProperties applicationProperties;

    @Override
    public void saveUser(String username) {
        credentialsService.saveUser(username);
    }

    @Override
    public void savePassword(String plainTextPassword) {
        credentialsService.savePassword(plainTextPassword);
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
        return applicationProperties
                .getProperty(SHOW_HINTS_BEFORE_REMOTE)
                .map(Boolean::parseBoolean).orElse(true);
    }

    @Override
    public void showHints(boolean trueOrFalse) {
        applicationProperties.saveProperty(SHOW_HINTS_BEFORE_REMOTE, Boolean.toString(trueOrFalse));
    }

    @Override
    public boolean isCheckForUpdatesEnabledAtStartUp() {
        return applicationProperties
                .getProperty(CHECK_FOR_UPDATES_AT_STARTUP)
                .map(Boolean::parseBoolean).orElse(true);
    }

    @Override
    public void setToCheckForUpdatesAtStartUpOrNot(boolean trueOrFalse) {
        applicationProperties.saveProperty(CHECK_FOR_UPDATES_AT_STARTUP, Boolean.toString(trueOrFalse));
    }

}
