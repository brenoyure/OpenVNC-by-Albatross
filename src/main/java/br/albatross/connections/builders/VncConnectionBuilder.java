package br.albatross.connections.builders;

import static java.lang.String.format;

import br.albatross.connections.Connection;
import br.albatross.connections.VncConnection;

public class VncConnectionBuilder implements ConnectionBuilder {

    private final String vncHomeDir;

    public VncConnectionBuilder(String vncHomeDir) {
        this.vncHomeDir = vncHomeDir;
    }

    @Override
    public Connection createConnection(String host, String userName, String password) {
        return new VncConnection(host, userName, password);
    }

    @Override
    public String getConnectionString(Connection connection) {
        return format("%s\\vncviewer.exe -connect -autoreconnect 1 -reconnectcounter 50 %s:5900 -user %s -password %s",
                vncHomeDir, connection.getHost(), connection.getUserName(), connection.getPassword());
    }

}
