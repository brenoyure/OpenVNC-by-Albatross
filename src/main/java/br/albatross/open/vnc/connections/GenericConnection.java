package br.albatross.open.vnc.connections;

public abstract class GenericConnection implements Connection {

	private final String host;
	private final String username;
	private final String password;

	public GenericConnection(String host, String username, String password) {
		this.host = host;
		this.username = username;
		this.password = password;
	}

	@Override
	public String getHost() {
		return host;
	}

	@Override
	public String getUserName() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}

}
