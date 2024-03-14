package br.albatross.open.vnc.starters;

import br.albatross.open.vnc.connections.Connection;

public interface ConnectionStarter {
    
    /**
     * Starts a given Connection.
     *
     * @param connection
     */
    void startConnection(Connection connection);
    
}
