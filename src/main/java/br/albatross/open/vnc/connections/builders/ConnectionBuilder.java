package br.albatross.open.vnc.connections.builders;

import br.albatross.open.vnc.connections.Connection;

public interface ConnectionBuilder {

    Connection createConnection(String host, String userName, String password);
    
    String getConnectionString(Connection connection);
    
}
