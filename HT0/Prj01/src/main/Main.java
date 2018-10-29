package main;

import main.Exceptions.RoomNotFoundedException;
import main.Furniture.Armchair;
import main.Furniture.Table;
import main.LightBulbs.LightBulbs;

/**
 * Main class
 *
 * @author Katsiaryna Stalchanka
 * @since 22.10.2018
 */

public class Main {
    /**
     * Entry point to the program
     */
    public static void main(String[] args) {

        Building building1 = new Building("Building_A");
        building1.addRoom(new Room("Kitchen", 256, 2));
        try {
            building1.getRoom("Kitchen")
                    .addLightBulb(new LightBulbs(25))
                    .addFurniture(new Armchair("Armchair_1", 25, 45))
                    .addLightBulb(new LightBulbs(58));
        } catch (RoomNotFoundedException e) {
            System.out.println(e.getMessage());
        }
        building1.addRoom(new Room("Bedroom", 258, 4));
        try {
            building1.getRoom("Bedroom")
                    .addLightBulb(new LightBulbs(684))
                    .addLightBulb(new LightBulbs(158))
                    .addFurniture(new Table("Table", 45))
                    .addFurniture(new Armchair("Armchair_2", 12, 15));
        } catch (RoomNotFoundedException e) {
            System.out.println(e.getMessage());
        }

        building1.addRoom(new Room("Bathroom", 25, 1));
        if (building1.verifyBuilding() == false) {
            return;
        }
        System.out.println(building1.describe());
    }
}
