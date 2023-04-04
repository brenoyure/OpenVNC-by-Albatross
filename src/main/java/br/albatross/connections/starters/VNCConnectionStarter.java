package br.albatross.connections.starters;

import static java.lang.Runtime.getRuntime;

import java.io.IOException;

import br.albatross.connections.Connection;
import br.albatross.connections.builders.ConnectionBuilder;

public class VNCConnectionStarter implements ConnectionStarter {

    private final ConnectionBuilder builder;

    public VNCConnectionStarter(ConnectionBuilder builder) {
        this.builder = builder;
    }

    @Override
    public void startConnection(Connection vncConnection) throws IOException, InterruptedException {
        String connectionString = builder.getConnectionString(vncConnection);
        System.out.println(connectionString);
        getRuntime().exec(connectionString);
    }

}
