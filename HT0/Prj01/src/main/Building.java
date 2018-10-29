package main;

import main.Exceptions.RoomNotFoundException;

import java.util.ArrayList;

/**
 * Represents object of building
 *
 * @author Katsiaryna Stalchanka
 * @since 22.10.2018
 */
public class Building {
    private String name;
    private ArrayList<Room> rooms = new ArrayList<>();

    Building(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Adds room to the list of rooms existed in the building
     *
     * @param room
     */
    public void addRoom(Room room) {
        rooms.add(room);
    }

    /**
     * Gets specified room
     *
     * @param roomName String name of the room which must be returned
     * @return object of specified room
     * @throws RoomNotFoundException when list of rooms of building doesn't contain specified room
     */
    public Room getRoom(String roomName) throws RoomNotFoundException {
        for (int i = 0; i < rooms.size(); i++) {
            if (roomName == rooms.get(i).getName()) {
                return rooms.get(i);
            }
        }
        throw new RoomNotFoundException("This room doesn't exist.");
    }

    /**
     * Verifies if the building can be build or not and prints the appropriate message
     */
    public boolean verifyBuilding() {
        boolean isBuild = true;
        for (Room room : this.rooms) {
            ArrayList<Exception> exceptions = room.verifyRoom();
            if (exceptions.size() != 0) {
                isBuild = false;
                System.out.printf("There are some problems in the room: %s\r\n", room.getName());
                for (Exception exception : exceptions) {
                    System.out.println(exception.getMessage());
                }
                System.out.println("Your building cannot be build. Try again.");
                return isBuild;
            }
        }
        if (isBuild == true) {
            System.out.print("Your building will be successfully build!!!\r\n");
        }
        return isBuild;
    }

    /**
     * Prints description of the building
     */
    public String describe() {
        StringBuilder buildingDescription = new StringBuilder();
        buildingDescription.append(this.getName()+"\r\n");
        for (Room room : rooms) {
            buildingDescription.append(room.getRoomDescriptionString());
        }
        return buildingDescription.toString();
    }
}
