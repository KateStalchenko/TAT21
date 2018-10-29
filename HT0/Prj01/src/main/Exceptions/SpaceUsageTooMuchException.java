package main.Exceptions;

/**
 * Represents SpaceUsageTooMuchException
 *
 * @author Katsiaryna Stalchanka
 * @since 22.10.2018
 */
public class SpaceUsageTooMuchException extends Exception {
    public SpaceUsageTooMuchException(String message) {
        super(message);
    }

    public SpaceUsageTooMuchException() {
        this("Too much space is occupied in the room.");
    }
}
