package config;
import jdk.dynalink.beans.StaticClass;
import net.bytebuddy.implementation.bytecode.Throw;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    public static Properties properties;

    static
    {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties");
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load configration file.");
        }

    }

    public static String GetProperty(String key){
        return properties.getProperty(key);

    }
}
