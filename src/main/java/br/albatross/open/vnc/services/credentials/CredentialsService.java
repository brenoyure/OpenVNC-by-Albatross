package br.albatross.open.vnc.services.credentials;

public interface CredentialsService {

	void saveUser(String username);
	void savePassword(String password);

	String getUsername();
	String getPassword();

}
