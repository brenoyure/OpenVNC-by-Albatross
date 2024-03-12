package br.albatross.open.vnc.builders;

import br.albatross.open.vnc.connections.Connection;

/**
 * Responsible for building a new SSVNC Viewer Connection with 
 * it's connection string in a Linux Distribution.
 * 
 * @author breno.brito
 */
public final class SsVncConnectionBuilder extends AbstractVncConnectionBuilder {

    private static final byte QUALITY_LEVEL  =  2;
    private static final byte COMPRESS_LEVEL =  7;

    private static final String CONNECTION_STRING_TEMPLATE = String.format("ssvncviewer -quality %d -compresslevel %d -16bpp ", QUALITY_LEVEL, COMPRESS_LEVEL);

    @Override
    public String getConnectionString(Connection connection) {

        StringBuilder sb = new StringBuilder(CONNECTION_STRING_TEMPLATE);
        sb.append(String.format(" %s:5900 ", connection.getHost()));

    	if (connection.getUserName() == null || connection.getUserName().isBlank()) {
            return sb.toString();
    	}

        return sb.append(String.format(" -mslogon %s", connection.getUserName())).toString();
    }

}
