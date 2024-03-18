package br.albatross.open.vnc.connections;

import static java.lang.String.format;

/**
 * Responsible a UltraVNC® Viewer Connection with 
 * it's connection string in the Windows® OS.
 * 
 * @author breno.brito
 */
public final class UltraVNCConnection extends GenericConnection {

    /**
     * Represents the Envoiroment Variable VNC_HOME, where the software
     * .exe of the UltraVNC® Viewer is located.
     */
    private static final String VNC_HOME_DIR = System.getenv("VNC_HOME");

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

    private static final String VNC_CONNECTION_TEMPLATE_STRING = format("%s\\vncviewer.exe -connect -autoreconnect %d -reconnectcounter %d ", VNC_HOME_DIR, AUTO_RECONNECT_COUNT_SECONDS, AUTO_RECONNECT_COUNT);

    public UltraVNCConnection(String host) {
    	super(host, null, null);
    }

    public UltraVNCConnection(String host, String userName) {
    	super(host, userName, null);
    }

    public UltraVNCConnection(String host, String userName, String password) {
        super(host, userName, password);
    }

	@Override
	public String getConnectionString() {

        StringBuilder sb = new StringBuilder(VNC_CONNECTION_TEMPLATE_STRING);
        sb.append(String.format("%s:5900", this.getHost()));

        if (this.getUserName() == null || this.getUserName().isBlank()) {

            return sb.toString();

        }

        if (this.getPassword() == null || this.getPassword().isBlank()) {

        	return sb.toString();		

        }

        sb.append(format(" -user %s -password %s ", this.getUserName(), this.getPassword()));

        return sb.toString();

	}

}
