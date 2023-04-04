package br.albatross.connections.builders;

import br.albatross.connections.Connection;

public interface ConnectionBuilder {

    Connection createConnection(String host, String userName, String password);
    
    String getConnectionString(Connection connection);
    
}
