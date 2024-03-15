package br.albatross.open.vnc.builders;

import static br.albatross.open.vnc.configurations.AvailableProperties.IS_WINDOWS_OS;

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

	@Override
	public Connection createConnection(String host, String username, String password) {

		return (IS_WINDOWS_OS) ? 
				new UltraVNCConnection(host, username, password) : 
				new SsVncConnection(host, username);

	}

}
