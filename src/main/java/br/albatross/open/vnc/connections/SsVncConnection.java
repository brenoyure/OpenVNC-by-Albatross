package br.albatross.open.vnc.connections;

/**
 * Represents a SSVNC Viewer Connection with 
 * it's connection string to be used in a Linux Distribution.
 * 
 * @author breno.brito
 */
public class SsVncConnection extends GenericConnection {

    private static final byte QUALITY_LEVEL  =  2;
    private static final byte COMPRESS_LEVEL =  7;

    private static final String CONNECTION_STRING_TEMPLATE = String.format("ssvncviewer -quality %d -compresslevel %d -16bpp ", QUALITY_LEVEL, COMPRESS_LEVEL);
	
	public SsVncConnection(String host, String userName) {
		super(host, userName, null);
	}

	@Override
	public String getConnectionString() {

        StringBuilder sb = new StringBuilder(CONNECTION_STRING_TEMPLATE);
        sb.append(String.format(" %s:5900 ", this.getHost()));

    	if (this.getUserName() == null || this.getUserName().isBlank()) {

            return sb.toString();
    	}

        return sb.append(String.format(" -mslogon %s", this.getUserName())).toString();

	}

}
