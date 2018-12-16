package Logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Katsiaryna Stalchanka
 * @since 16-Dec-18
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
