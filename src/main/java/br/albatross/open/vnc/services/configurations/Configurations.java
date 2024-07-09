package br.albatross.open.vnc.services.configurations;

import br.albatross.open.vnc.configurations.ApplicationProperties;
import br.albatross.open.vnc.configurations.ApplicationPropertiesFileBasedConfiguration;
import br.albatross.open.vnc.services.credentials.ApplicationPropertiesFileBasedCredentialsService;
import br.albatross.open.vnc.services.credentials.CredentialsService;

public abstract class Configurations {

    private Configurations() {

    }

    public static Configuration getInstance() {

        ApplicationProperties applicationProperties  =  new ApplicationPropertiesFileBasedConfiguration();
        CredentialsService    credentialsService     =  new ApplicationPropertiesFileBasedCredentialsService(applicationProperties);

        return new VncConfigurationService(credentialsService, applicationProperties);
    }
    
    public static WindowsSpecificConfiguration getWindowsSpecificInstance() {

        ApplicationProperties applicationProperties = new ApplicationPropertiesFileBasedConfiguration();
        CredentialsService credentialsService       = new ApplicationPropertiesFileBasedCredentialsService(applicationProperties);

        return new WindowsVncConfigurationService(applicationProperties, credentialsService);

    }
    
}
