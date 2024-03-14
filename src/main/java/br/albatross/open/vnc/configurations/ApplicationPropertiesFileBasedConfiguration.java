package br.albatross.open.vnc.configurations;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public final class ApplicationPropertiesFileBasedConfiguration {

    private Properties properties;    

    private static final File PROPERTIES_FOLDER = new File(System.getProperty("user.home"), ".openvnc");
    private static final File PROPERTIES_FILE   = new File(PROPERTIES_FOLDER, "application.properties");

    public ApplicationPropertiesFileBasedConfiguration() {
    	loadProperties();
    }

    public String getProperty(String propertyKey) {
        return properties.getProperty(propertyKey);
    }

    public void saveProperty(String key, String value) {
        
        try (OutputStream fos = new BufferedOutputStream(new FileOutputStream(PROPERTIES_FILE))) {

            loadProperties();
            properties.setProperty(key, value);
            properties.store(fos, null);

        } catch (IOException ex) { throw new RuntimeException(ex); }
    }

    public boolean contains(String value) {
        return properties.containsValue(value);
    }

    public void loadProperties() {

		try {

			if (properties == null) {
				properties = new Properties();
			}

			if (!PROPERTIES_FOLDER.exists()) {
				PROPERTIES_FOLDER.mkdir();
			}

			if (!PROPERTIES_FILE.exists()) {
				PROPERTIES_FILE.createNewFile();
			}

			try (InputStream fis = new BufferedInputStream(new FileInputStream(PROPERTIES_FILE))) {
				properties.load(fis);

			}
		}

		catch (IOException e) { throw new RuntimeException(e); }

	}    

}
