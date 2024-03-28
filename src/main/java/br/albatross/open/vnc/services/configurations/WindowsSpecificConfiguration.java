package br.albatross.open.vnc.services.configurations;

import java.io.File;
import java.util.Optional;

public interface WindowsSpecificConfiguration extends Configuration {

    Optional<String> getVncDirectory();
    String setVncDirectory(String directoryAbsolutePath);

    Optional<File> autoDetectVncDirectory();
}
