package textquest.service;

import org.apache.log4j.Logger;
import textquest.enums.MessageNumbers;
import textquest.enums.MessageParameters;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

    private static final Logger LOGGER = Logger.getLogger(PropertiesLoader.class);
    public static Properties properties;

    public void loadProperties() {
        try {
            InputStream input = this.getClass().getClassLoader().getResourceAsStream("quest.properties");
            properties = new Properties();
            properties.load(input);
        } catch (IOException ex) {
            System.out.println("Failure to load quest");
        }
        LOGGER.info("The properties of Messages have been loaded.");
    }

    protected static String getMessageProperties(MessageNumbers messageNumbers, MessageParameters messageParameters) {
        String propertyKey = messageNumbers.getName() + "_" + messageParameters.getName();
        LOGGER.info("The property of Message " + messageNumbers + " have been loaded.");
        return properties.getProperty(propertyKey);
    }
}
