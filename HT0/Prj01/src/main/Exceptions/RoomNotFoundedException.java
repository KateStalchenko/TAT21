package main.Exceptions;

/**
 * Represents RoomNotFoundedException
 *
 * @author Katsiaryna Stalchanka
 * @since 22.10.2018
 */
public class RoomNotFoundedException extends Exception {
    public RoomNotFoundedException(String message) {
        super(message);
    }
}
