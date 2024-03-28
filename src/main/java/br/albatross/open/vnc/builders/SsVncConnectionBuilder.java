package br.albatross.open.vnc.builders;

import br.albatross.open.vnc.connections.Connection;
import br.albatross.open.vnc.connections.SsVncConnection;

/**
 * 
 * Responsible for building a new VNC Connection
 * 
 * @author breno.brito
 */
public class SsVncConnectionBuilder implements ConnectionBuilder {

    @Override
    public Connection createConnection(String host) {

        return new SsVncConnection(host);

    }

    @Override
    public Connection createConnection(String host, String username, String password) {

        return new SsVncConnection(host, username);

    }

}
