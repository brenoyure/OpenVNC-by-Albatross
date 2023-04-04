package br.albatross.connections.starters;

import java.io.IOException;

import br.albatross.connections.Connection;

public interface ConnectionStarter {
    
    void startConnection(Connection connection) throws InterruptedException, IOException;
    
}
