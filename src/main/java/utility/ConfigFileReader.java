package utility;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
    private Properties properties;

    public ConfigFileReader() {
        configRetriver();
    }

    private void configRetriver()  {
        try {
            FileReader configFile = new FileReader("./src/main/resources/config.properties");
            properties = new Properties();
            properties.load(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String Key) {
        return properties.getProperty(Key);
    }

}
