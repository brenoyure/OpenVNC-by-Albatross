package br.albatross.open.vnc.configurations;

import java.util.Optional;

public interface ApplicationProperties {

    Optional<String> getProperty(String propertyKey);
    String saveProperty(String propertyKey, String propertyValue);

    void clearProperty(String propertyKey);

}
