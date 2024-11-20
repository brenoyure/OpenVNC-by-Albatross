package br.albatross.open.vnc.starters;

import static br.albatross.open.vnc.handlers.exception.VncNotFoundExceptionHandler.showNotFoundMessageDialog;
import static java.lang.Runtime.getRuntime;

import java.io.IOException;

import br.albatross.open.vnc.connections.Connection;

public final class VncConnectionStarter implements ConnectionStarter {

    /**
     * Starts a Connection and put it's process/thread to sleep, using the waitFor() while
     * connection is running, preventing opening multiple connections from the
     * same instance.
     *
     * @param connection
     */
    @Override
    public void startConnection(Connection connection) {

        try {

            getRuntime().exec(connection.getConnectionString().split(" ")).waitFor();           

        } catch (IOException e) {

            showNotFoundMessageDialog();

        } catch (InterruptedException e) { /*Nothing to catch*/ }

    }

}