package br.albatross.open.vnc.connections;

import static java.lang.String.format;

import java.io.File;

import br.albatross.open.vnc.services.configurations.WindowsSpecificConfiguration;

/**
 * Represents a UltraVNC® Viewer Connection with 
 * it's connection string in the Windows® OS.
 * 
 * @author breno.brito
 */
public final class UltraVNCConnection extends GenericConnection {

	private WindowsSpecificConfiguration configuration;

    /**
     * If for some reson the UltraVNC® Connection drops, it will automatically try to
     * reconnect to the user machine. This int value represents how much seconds
     * the Viewer must wait before re-sending the request to the user.
     */
    private static final byte AUTO_RECONNECT_COUNT_SECONDS = 3;

    /**
     * If for some reson the UltraVNC® Connection drops, it will automatically try to
     * reconnect to the user machine. This int value represents how much retries
     * the Viewer will re-send the request to the user, before VNC closes the
     * connection.
     */
    private static final byte AUTO_RECONNECT_COUNT = 50;

    private static final String VNC_CONNECTION_TEMPLATE_STRING = format("vncviewer.exe -connect -autoreconnect %d -reconnectcounter %d ", AUTO_RECONNECT_COUNT_SECONDS, AUTO_RECONNECT_COUNT);

    public UltraVNCConnection(String host, WindowsSpecificConfiguration configuration) {
    	super(host);
    	this.configuration = configuration;
    	configuration.getUser().ifPresent(super::setUsername);
    	configuration.getPassword().ifPresent(super::setPassword);
    }

    public UltraVNCConnection(String host, String userName, String password, WindowsSpecificConfiguration configuration) {
        super(host, userName, password);
        this.configuration = configuration;
    }

    @Override
    public String getConnectionString() {

        StringBuilder sb = new StringBuilder();
        configuration.getVncDirectory().ifPresent(directoryString -> sb
        		.append(directoryString)
        		.append(File.separator));

        sb
            .append(VNC_CONNECTION_TEMPLATE_STRING)
            .append(String.format("%s:5900", this.getHost()));

        if (this.getUserName() == null || this.getPassword() == null || this.getUserName().isBlank() || this.getPassword().isBlank()) {

            return sb.toString();

        }

        sb.append(format(" -user %s -password %s", this.getUserName(), this.getPassword()));

        return sb.toString();

    }

}
