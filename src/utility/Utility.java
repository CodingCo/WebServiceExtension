package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author Grønborg
 */
public class Utility {

    public static Properties initProperties(String serverProperties) {
        Properties properties = new Properties();
        try (InputStream is = new FileInputStream(serverProperties)) {
            properties.load(is);
        } catch (IOException e) {
            System.out.println("unable to load file");
           
        }
        return properties;
    }

    public static void setLogFile(String className) {
        try {
            Logger logger = Logger.getLogger(className);
            FileHandler handler = new FileHandler("logfiles/serverlog%g.txt");
            Formatter simpleFormat = new SimpleFormatter();
            handler.setFormatter(simpleFormat);
            logger.addHandler(handler);
        } catch (IOException ex) {
            System.out.println("logging error - no file");
            
        }
    }

    public static void logInfo(String className, String message) {
        Logger logger;
        logger = Logger.getLogger(className);
        logger.log(Level.INFO, message);
    }

}
