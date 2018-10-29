package main.Exceptions;

/**
 * Represents IlluminanceTooMuchException
 *
 * @author Katsiaryna Stalchanka
 * @since 22.10.2018
 */
public class IlluminanceTooMuchException extends Exception {
    public IlluminanceTooMuchException(String message) {
        super(message);
    }

    public IlluminanceTooMuchException() {
        this("Too much illumination in the room.");
    }
}
