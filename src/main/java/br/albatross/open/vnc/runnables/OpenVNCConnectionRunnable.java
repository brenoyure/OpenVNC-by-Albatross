package br.albatross.open.vnc.runnables;

import br.albatross.open.vnc.connections.Connection;
import br.albatross.open.vnc.services.configurations.Configuration;
import br.albatross.open.vnc.starters.ConnectionStarter;

public class OpenVNCConnectionRunnable implements Runnable {

    private final Connection connection;
    private final Configuration configuration;

    public OpenVNCConnectionRunnable(Connection connection, Configuration configuration) {
        this.connection = connection;
        this.configuration = configuration;
    }

    @Override
    public void run() {

        configuration.getUser().ifPresent(connection::setUsername);
        configuration.getPassword().ifPresent(connection::setPassword);

        connection.start();

    }

}

