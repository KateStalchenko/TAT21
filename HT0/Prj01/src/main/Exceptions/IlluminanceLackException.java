package main.Exceptions;

/**
 * Represents IlluminanceLackException
 *
 * @author Katsiaryna Stalchanka
 * @since 22.10.2018
 */
public class IlluminanceLackException extends Exception {
    public IlluminanceLackException(String message) {
        super(message);
    }

    public IlluminanceLackException() {
        this("There is a lack of illumination.");
    }
}
