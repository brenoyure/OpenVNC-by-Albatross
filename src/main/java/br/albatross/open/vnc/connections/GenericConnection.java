package br.albatross.open.vnc.connections;

public abstract class GenericConnection implements Connection {

	private final String host;
	private String username;
	private String password;

	public GenericConnection(String host) {
		this.host = host;
	}

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

	@Override
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

}
