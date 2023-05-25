package br.albatross.open.vnc.builders;

import br.albatross.open.vnc.connections.Connection;

import static java.lang.String.format;

/**
 * Responsible for building a new SSVNC Viewer Connection with 
 * it's connection string in a Linux Distribution.
 * 
 * @author breno.brito
 */
public class SsVncConnectionBuilder extends AbstractVncConnectionBuilder {

    @Override
    public String getConnectionString(Connection connection) {
        return format("ssvncviewer -mslogon %s %s:5900", connection.getUserName(), connection.getHost());
    }

}
