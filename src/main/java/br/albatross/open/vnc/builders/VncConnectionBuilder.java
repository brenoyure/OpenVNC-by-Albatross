package br.albatross.open.vnc.builders;

import static java.lang.String.format;

import br.albatross.open.vnc.connections.Connection;

/**
 * Responsible for building a new UltraVNC® Viewer Connection with 
 * it's connection string in the Windows® OS.
 * 
 * @author breno.brito
 */
public class VncConnectionBuilder extends AbstractVncConnectionBuilder {

    /**
     * Represents the Envoiroment Variable VNC_HOME, where the software
     * .exe of the UltraVNC® Viewer is located.
     */
    private final String vncHomeDir = System.getenv("VNC_HOME");

    /**
     * If for some reson the UltraVNC® Connection drops, it will automatically try to
     * reconnect to the user machine. This int value represents how much seconds
     * the Viewer must wait before re-sending the request to the user.
     */
    private static final byte AUTO_RECONNECT_COUNT_SECONDS = 1;

    /**
     * If for some reson the UltraVNC® Connection drops, it will automatically try to
     * reconnect to the user machine. This int value represents how much retries
     * the Viewer will re-send the request to the user, before VNC closes the
     * connection.
     */
    private static final byte AUTO_RECONNECT_COUNT = 50;

    @Override
    public String getConnectionString(Connection connection) {
        return format("%s\\vncviewer.exe -connect -autoreconnect %d -reconnectcounter %d %s:5900 -user %s -password %s",
                vncHomeDir, AUTO_RECONNECT_COUNT_SECONDS, AUTO_RECONNECT_COUNT, connection.getHost(), connection.getUserName(), connection.getPassword());
    }

}
