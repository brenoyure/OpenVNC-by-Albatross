package br.albatross.open.vnc.services.configurations;

import br.albatross.open.vnc.services.credentials.ApplicationPropertiesFileBasedCredentialsService;
import br.albatross.open.vnc.services.credentials.CredentialsService;

public class VncConfigurationService implements Configuration {

	private CredentialsService credentialsService;

	public VncConfigurationService() {
		credentialsService = new ApplicationPropertiesFileBasedCredentialsService();
	}

	@Override
	public void saveUser(String username) {
		credentialsService.saveUser(username);
	}

	@Override
	public void savePassword(String plainTextPassword) {
		credentialsService.savePassword(plainTextPassword);;
	}

	@Override
	public String getUser() {
		return credentialsService.getUsername();
	}

	@Override
	public String getPassword() {
		return credentialsService.getPassword();

	}

}
