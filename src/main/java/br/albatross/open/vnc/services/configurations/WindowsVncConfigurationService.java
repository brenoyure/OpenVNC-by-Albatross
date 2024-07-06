package br.albatross.open.vnc.services.configurations;

import static br.albatross.open.vnc.configurations.AvailableProperties.ON_WINDOWS_VNC_HOME_DIR;
import static java.util.Base64.getEncoder;

import java.io.File;
import java.util.Base64;
import java.util.Optional;

import br.albatross.open.vnc.configurations.ApplicationProperties;
import br.albatross.open.vnc.services.credentials.CredentialsService;

public class WindowsVncConfigurationService extends VncConfigurationService implements WindowsSpecificConfiguration {

    private final ApplicationProperties applicationProperties;

    public WindowsVncConfigurationService(ApplicationProperties applicationProperties, CredentialsService credentialsService) {
        super(credentialsService, applicationProperties);
        this.applicationProperties = applicationProperties;
    }

    @Override
    public Optional<String> getVncDirectory() {

        Optional<String> encodedDirectory = applicationProperties.getProperty(ON_WINDOWS_VNC_HOME_DIR);

        if (encodedDirectory.isEmpty()) {
            return Optional.empty();
        }

        String decodedDirectory = new String(Base64.getDecoder().decode(encodedDirectory.get()));
        return Optional.of(decodedDirectory);

    }

    @Override
    public String setVncDirectory(String directoryAbsolutePath) {

        String encodedDirectory = getEncoder().encodeToString(directoryAbsolutePath.getBytes());
        return applicationProperties.saveProperty(ON_WINDOWS_VNC_HOME_DIR, encodedDirectory);

    }

    @Override
    public Optional<File> autoDetectVncDirectory() {

        File cRootDrive = File.listRoots()[0];
        StringBuilder sb = new StringBuilder(cRootDrive.getAbsolutePath());

        sb
            .append(File.separator)
            .append("Program Files")
            .append(File.separator)
            .append("uvnc bvba")
            .append(File.separator)
            .append("UltraVnc");

        File ultraVncHomeDir = new File(sb.toString());

        return ultraVncHomeDir.exists() ?
                Optional.ofNullable(ultraVncHomeDir) : 
                Optional.empty();

    }

}
