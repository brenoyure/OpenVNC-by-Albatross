package br.albatross.open.vnc.builders;

import br.albatross.open.vnc.connections.Connection;
import br.albatross.open.vnc.connections.UltraVNCConnection;
import br.albatross.open.vnc.services.configurations.WindowsSpecificConfiguration;

public class UltraVncConnectionBuilder implements ConnectionBuilder {

    private WindowsSpecificConfiguration configuration;

    public UltraVncConnectionBuilder(WindowsSpecificConfiguration configuration) {

        this.configuration = configuration;

    }

    @Override
    public Connection createConnection(String host) {

        return new UltraVNCConnection(host, configuration);

    }

    @Override
    public Connection createConnection(String host, String userName, String password) {

        return new UltraVNCConnection(host, userName, password, configuration);

    }

}
