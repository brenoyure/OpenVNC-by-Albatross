package br.albatross.open.vnc.builders;

import br.albatross.open.vnc.connections.Connection;
import br.albatross.open.vnc.connections.VncConnection;
import br.albatross.open.vnc.starters.ConnectionStarter;
import br.albatross.open.vnc.starters.VNCConnectionStarter;

public abstract class AbstractVncConnectionBuilder implements ConnectionBuilder {

    private ConnectionStarter connectionStarter;

    @Override
    public Connection createConnection(String host, String userName, String password) {
        return new VncConnection(host, userName, password);
    }

    @Override
    public abstract String getConnectionString(Connection connection);

    @Override
    public ConnectionStarter getConnectionStarter() {

        if (connectionStarter == null) {
            return connectionStarter = new VNCConnectionStarter(this);
        }

        return connectionStarter;
    }

}
