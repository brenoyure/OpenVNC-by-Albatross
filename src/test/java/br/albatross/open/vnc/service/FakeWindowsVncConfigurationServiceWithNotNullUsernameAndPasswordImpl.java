package br.albatross.open.vnc.service;

import java.util.Optional;

public class FakeWindowsVncConfigurationServiceWithNotNullUsernameAndPasswordImpl
		extends FakeWindowsVncConfigurationServiceWithNotNullVncDirectoryAndNullUsernameAndPasswordImpl {

	@Override
	public Optional<String> getUser() {
		return Optional.ofNullable("albatross18");
	}

	@Override
	public Optional<String> getPassword() {
		return Optional.ofNullable("connection-password-123");
	}

}
