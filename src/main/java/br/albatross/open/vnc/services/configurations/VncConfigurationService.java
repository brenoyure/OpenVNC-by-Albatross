package br.albatross.open.vnc.services.configurations;

import br.albatross.open.vnc.configurations.ApplicationPropertiesFileBasedConfiguration;
import br.albatross.open.vnc.services.credentials.ApplicationPropertiesFileBasedCredentialsService;
import br.albatross.open.vnc.services.credentials.CredentialsService;

import java.util.Base64;
import static br.albatross.open.vnc.configurations.AvailableProperties.ON_WINDOWS_VNC_HOME_DIR;

public class VncConfigurationService implements Configuration {

    private ApplicationPropertiesFileBasedConfiguration properties;

    private CredentialsService credentialsService;

    public VncConfigurationService() {
        if (properties == null) {
            properties = new ApplicationPropertiesFileBasedConfiguration();
        }
        credentialsService = new ApplicationPropertiesFileBasedCredentialsService(properties);
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
    public String getUser() {
        return credentialsService.getUsername();
    }

    @Override
    public String getPassword() {
        return credentialsService.getPassword();

    }

    @Override
    public void onWindowsSaveVNCDirectory(String absolutePath) {
        String encodedDirectory = Base64.getEncoder().encodeToString(absolutePath.getBytes());
        properties.saveProperty(ON_WINDOWS_VNC_HOME_DIR, encodedDirectory);
    }

    @Override
    public String onWindowsGetVNCDirectory() {
        String encodedDirectory = properties.getProperty(ON_WINDOWS_VNC_HOME_DIR);
        String decodedDirectory = new String(Base64.getDecoder().decode(encodedDirectory));

        return properties.getProperty(decodedDirectory);

    }

}
