package br.albatross.open.vnc.builders;

import br.albatross.open.vnc.connections.Connection;
import br.albatross.open.vnc.connections.SsVncConnection;
import br.albatross.open.vnc.connections.UltraVNCConnection;

/**
 * 
 * Responsible for building a new VNC Connection
 * 
 * @author breno.brito
 */
public class VncConnectionBuilder implements ConnectionBuilder {

	private static final boolean IS_WINDOWS = System.getProperty("os.name").contains("Windows");

	@Override
	public Connection createConnection(String host, String username, String password) {

		return (IS_WINDOWS) ? 
				new UltraVNCConnection(host, username, password) : 
				new SsVncConnection(host, username);

	}

}
