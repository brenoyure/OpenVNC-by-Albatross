package br.albatross.connections.builders;

import static java.lang.String.format;

import br.albatross.connections.Connection;
import br.albatross.connections.VncConnection;

public class VncConnectionBuilder implements ConnectionBuilder {

    /**
     * Represents the VNC installation folder.
     */
    private final String vncHomeDir;

    /**
     * If for some reson the VNC Connection drops, it will automatically try to
     * reconnect to the user machine. This int value represents how much seconds
     * the Viewer must wait before re-sending the request to the user.
     */
    private static final int AUTO_RECONNECT_COUNT_SECONDS = 1;

    /**
     * If for some reson the VNC Connection drops, it will automatically try to
     * reconnect to the user machine. This int value represents how much retries
     * the Viewer will re-send the request to the user, before VNC closes the
     * connection.
     */
    private static final int AUTO_RECONNECT_COUNT = 50;

    public VncConnectionBuilder(String vncHomeDir) {
        this.vncHomeDir = vncHomeDir;
    }

    @Override
    public Connection createConnection(String host, String userName, String password) {
        return new VncConnection(host, userName, password);
    }

    @Override
    public String getConnectionString(Connection connection) {
        return format("%s\\vncviewer.exe -connect -autoreconnect %d -reconnectcounter %d %s:5900 -user %s -password %s",
                vncHomeDir, AUTO_RECONNECT_COUNT_SECONDS, AUTO_RECONNECT_COUNT, connection.getHost(), connection.getUserName(), connection.getPassword());
    }

}
