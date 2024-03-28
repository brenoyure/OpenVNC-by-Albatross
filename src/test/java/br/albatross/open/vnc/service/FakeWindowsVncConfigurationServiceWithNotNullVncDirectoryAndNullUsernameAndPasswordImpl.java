package br.albatross.open.vnc.service;

import java.util.Optional;

public class FakeWindowsVncConfigurationServiceWithNotNullVncDirectoryAndNullUsernameAndPasswordImpl extends FakeWindowsVncConfigurationServiceImpl {

	@Override
	public Optional<String> getVncDirectory() {
		return Optional.ofNullable("C:\\Program Files\\uvnc bvba\\UltraVnc");
	}	

}
