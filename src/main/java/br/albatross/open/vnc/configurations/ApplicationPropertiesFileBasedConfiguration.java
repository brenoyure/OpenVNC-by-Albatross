package br.albatross.open.vnc.configurations;

import static br.albatross.open.vnc.configurations.AvailableProperties.IS_WINDOWS_OS;
import static java.lang.Runtime.getRuntime;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Optional;
import java.util.Properties;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ApplicationPropertiesFileBasedConfiguration implements ApplicationProperties {

    private Properties properties;    

    private static final File PROPERTIES_FOLDER = new File(System.getProperty("user.home"), ".openvnc");
    private static final File PROPERTIES_FILE   = new File(PROPERTIES_FOLDER, "application.properties");

    private static final String[] WINDOWS_ADD_HIDE_ATTRIB_CMD_ARRAY = { "attrib", "+h", PROPERTIES_FOLDER.getAbsolutePath() };

    @Override
    public Optional<String> getProperty(String propertyKey) {
        return Optional.ofNullable(properties.getProperty(propertyKey));
    }

    @Override
    public String saveProperty(String key, String value) {

        try (OutputStream fos = new BufferedOutputStream(new FileOutputStream(PROPERTIES_FILE))) {

            properties.setProperty(key, value);
            properties.store(fos, null);

            return value;

        } catch (IOException ex) { throw new RuntimeException(ex); }
    }

    @Override
    public void clearProperty(String propertyKey) {

        try (OutputStream fos = new BufferedOutputStream(new FileOutputStream(PROPERTIES_FILE))) {

            properties.remove(propertyKey);
            properties.store(fos, null);

        } catch (IOException ex) { throw new RuntimeException(ex); }

    }

    @PostConstruct
    void loadProperties() {

        try {

            if (properties == null) {
                properties = new Properties();
            }

            if (!PROPERTIES_FOLDER.exists()) {
                PROPERTIES_FOLDER.mkdir();
            }

            if (IS_WINDOWS_OS) {

                getRuntime().exec(WINDOWS_ADD_HIDE_ATTRIB_CMD_ARRAY);
            }

            if (!PROPERTIES_FILE.exists()) {
                PROPERTIES_FILE.createNewFile();
            }

            try (InputStream fis = new BufferedInputStream(new FileInputStream(PROPERTIES_FILE))) {
                properties.load(fis);

            }

        } catch (IOException e) { throw new RuntimeException(e); }

    }    

}
