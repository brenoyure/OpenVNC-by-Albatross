package br.albatross.open.vnc.connections.starters;

import static java.lang.Runtime.getRuntime;

import java.io.IOException;

import br.albatross.open.vnc.connections.Connection;
import br.albatross.open.vnc.connections.builders.ConnectionBuilder;
import br.albatross.open.vnc.connections.handlers.exception.VncFolderNotFoundExceptionHandler;
import br.albatross.open.vnc.connections.handlers.exception.VncInvalidPasswordExceptionHandler;

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
     * @throws InterruptedException
     */
    @Override
    public void startConnection(Connection vncConnection) {
        String connectionString = builder.getConnectionString(vncConnection);
        
        try {

            if (vncConnection.getPassword() == null || vncConnection.getPassword().isEmpty()) {
                VncInvalidPasswordExceptionHandler.showInvalidPasswordMessageDialog();
                return;
            }

            getRuntime().exec(connectionString).waitFor();           
            
        } catch (IOException e) {
            VncFolderNotFoundExceptionHandler.showFolderNotFoundMessageDialog();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }

}
