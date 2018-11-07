import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Represents Logger class
 *
 * @author Katsiaryna Stalchanka
 * @since 05.11.2018
 */
public class Log {
    private static final Logger logger = LogManager.getLogger("Logger");

    public static void debug(String message) {
        logger.debug(message);
    }

    public static void info(String message) {
        logger.info(message);
    }

    public static void error(String message) {
        logger.error(message);
    }
}
