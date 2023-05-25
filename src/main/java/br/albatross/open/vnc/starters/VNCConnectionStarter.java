package br.albatross.open.vnc.starters;

import static java.lang.Runtime.getRuntime;

import java.io.IOException;

import br.albatross.open.vnc.connections.Connection;
import br.albatross.open.vnc.builders.ConnectionBuilder;
import static br.albatross.open.vnc.handlers.exception.VncNotFoundExceptionHandler.showNotFoundMessageDialog;

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
     */
    @Override
    public void startConnection(Connection vncConnection) {
        String connectionString = builder.getConnectionString(vncConnection);
        
        try {
            getRuntime().exec(connectionString).waitFor();           
            
        } catch (IOException e) {
            showNotFoundMessageDialog();

        } catch (InterruptedException e) { /*Nothing to catch*/ }
        
    }

}
