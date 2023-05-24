package br.albatross.open.vnc.connections;

public abstract class GenericConnection implements Connection {

    private final String host;
    private final String userName;
    private final String password;

    public GenericConnection(String host, String userName, String password) {
        this.host = host;
        this.userName = userName;
        this.password = password;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

}
