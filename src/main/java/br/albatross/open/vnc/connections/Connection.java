package br.albatross.open.vnc.connections;

/**
 * Interface que representa uma conexão
 * @author breno.brito
 */
public interface Connection {

    void setUsername(String username);
    void setPassword(String plainTextPassword);

    String getHost();
    String getUserName();
    String getPassword();

    String getConnectionString();

    void start();

}
