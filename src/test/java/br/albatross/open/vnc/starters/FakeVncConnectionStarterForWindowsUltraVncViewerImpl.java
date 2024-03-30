package br.albatross.open.vnc.starters;

import java.io.IOException;

import br.albatross.open.vnc.connections.Connection;

/**
 * Implementação Fake do <code>ConnectionStarter</code>.
 * 
 * @author breno.brito
 */
public class FakeVncConnectionStarterForWindowsUltraVncViewerImpl implements ConnectionStarter {

	@Override
	public void startConnection(Connection connection) {

		if (!connection.getConnectionString().startsWith("C:\\Program Files\\uvnc bvba\\UltraVnc")) {
			throw new RuntimeException(new IOException());
		}
		
	}

}
