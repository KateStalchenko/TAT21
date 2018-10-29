package main.Exceptions;

/**
 * Represents RoomNotFoundException
 *
 * @author Katsiaryna Stalchanka
 * @since 22.10.2018
 */
public class RoomNotFoundException extends Exception {
    public RoomNotFoundException(String message) {
        super(message);
    }
}
