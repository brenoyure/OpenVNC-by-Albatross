package br.albatross.open.vnc.builders;

import static br.albatross.open.vnc.configurations.AvailableProperties.IS_WINDOWS_OS;
import static br.albatross.open.vnc.services.configurations.Configurations.getWindowsSpecificInstance;

public abstract class ConnectionBuilders {

    private ConnectionBuilders() {

    }

    public static SsVncConnectionBuilder newSsVncBuilderInstance() {
        return new SsVncConnectionBuilder();
    }

    public static UltraVncConnectionBuilder newUltraVncBuilderInstance() {
        return new UltraVncConnectionBuilder(getWindowsSpecificInstance());
    }

    public static ConnectionBuilder newInstance() {

        if (IS_WINDOWS_OS) {
            return newUltraVncBuilderInstance();
        }

        return newSsVncBuilderInstance();

    }

}
