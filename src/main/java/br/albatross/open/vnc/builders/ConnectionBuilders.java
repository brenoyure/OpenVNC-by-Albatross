package br.albatross.open.vnc.builders;

import static br.albatross.open.vnc.configurations.AvailableProperties.IS_WINDOWS_OS;

import br.albatross.open.vnc.services.configurations.WindowsSpecificSettings;
import br.albatross.open.vnc.services.configurations.WindowsSpecificConfiguration;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

public class ConnectionBuilders {

    @Inject
    @WindowsSpecificSettings
    WindowsSpecificConfiguration windowsSpecificConfiguration;

    @Produces @ApplicationScoped
    public ConnectionBuilder newInstance() {

        if (IS_WINDOWS_OS) {
            return new UltraVncConnectionBuilder(windowsSpecificConfiguration);
        }

        return new SsVncConnectionBuilder();

    }

}
