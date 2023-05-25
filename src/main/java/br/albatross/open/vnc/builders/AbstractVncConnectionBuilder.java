package br.albatross.open.vnc.builders;

import br.albatross.open.vnc.connections.Connection;
import br.albatross.open.vnc.connections.VncConnection;

public abstract class AbstractVncConnectionBuilder implements ConnectionBuilder {

    @Override
    public Connection createConnection(String host, String userName, String password) {
        return new VncConnection(host, userName, password);
    }

    @Override
    public abstract String getConnectionString(Connection connection);

}
