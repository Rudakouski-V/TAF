package configuration;

import java.io.IOException;
import java.util.Properties;

public class ReadProperties {
    private static Properties properties;
    private static String filename = "config.properties";

    static {
        properties = new Properties();
        try {
            properties.load(ReadProperties.class.getClassLoader().getResourceAsStream(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getUrl() {
        return properties.getProperty("url");
    }

    public static String browserName() {
        return properties.getProperty("browser");
    }

    public static String username() {
        return properties.getProperty("username");
    }

    public static String password() {
        return properties.getProperty("password");
    }

    public static String noAccessUsername() {
        return properties.getProperty("noAccessUsername");
    }

    public static String noAccessPassword() {
        return properties.getProperty("noAccessPassword");
    }

    public static boolean isHeadless() {
        return properties.getProperty("headless").equalsIgnoreCase("true");
    }

    public static int timeout() {
        return Integer.parseInt(properties.getProperty("timeout"));
    }
}
