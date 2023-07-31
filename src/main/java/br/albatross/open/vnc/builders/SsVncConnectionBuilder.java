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

    private static final byte QUALITY_LEVEL  =  2;
    private static final byte COMPRESS_LEVEL =  7;

    @Override
    public String getConnectionString(Connection connection) {
    	
    	if (connection.getUserName() == null || connection.getUserName().isBlank()) {
    		return format("ssvncviewer %s:5900 -quality %d -compresslevel %d -16bpp", connection.getHost(), QUALITY_LEVEL, COMPRESS_LEVEL);
    	}

        return format("ssvncviewer -mslogon %s %s:5900 -quality %d -compresslevel %d -16bpp", connection.getUserName(), connection.getHost(), QUALITY_LEVEL, COMPRESS_LEVEL);
    }

}
