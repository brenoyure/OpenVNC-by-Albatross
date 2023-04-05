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

    /**
     * Starts a VNC Connection and put it's process/thread to sleep, using the waitFor() while
     * connection is running, preventing opening multiple connections from the
     * same instance.
     *
     * @param vncConnection
     * @throws IOException if VNC_HOME envoriment variable does not exists, or
     * it does not have the value of correct VNC installation folder
     *
     * @throws InterruptedException
     */
    @Override
    public void startConnection(Connection vncConnection) throws IOException, InterruptedException {
        String connectionString = builder.getConnectionString(vncConnection);
        getRuntime().exec(connectionString).waitFor();
    }

}
