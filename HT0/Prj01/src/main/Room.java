package main;

import main.Exceptions.IlluminanceLackException;
import main.Exceptions.IlluminanceTooMuchException;
import main.Exceptions.SpaceUsageTooMuchException;
import main.Furniture.Furniture;
import main.Furniture.HardFurniture;
import main.Furniture.SoftFurniture;
import main.Furniture.Table;
import main.LightBulbs.LightBulbs;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Represents object of room
 *
 * @author Katsiaryna Stalchanka
 * @since 22.10.2018
 */
public class Room {
    private final int MAX_LIGHT = 4000;
    private final int MIN_LIGHT = 300;
    private final double RATIO_ALLOWED_SPACE_TAKEN = 0.7;
    private final int WINDOW_ILLUMINATION = 700;

    DecimalFormat decimalFormat = new DecimalFormat("##.##");

    private String name;
    private int windowsNumber;
    private double roomSize;

    private ArrayList<LightBulbs> bulbs = new ArrayList<>();
    private ArrayList<Furniture> furniture = new ArrayList<>();

    public Room(String name, double roomSize, int windowsNumber) {
        this.name = name;
        this.roomSize = roomSize;
        this.windowsNumber = windowsNumber;
    }

    public int getWindowsNumber() {
        return windowsNumber;
    }

    public double getRoomSize() {
        return roomSize;
    }

    public String getName() {
        return name;
    }

    public ArrayList<LightBulbs> getBulbs() {
        return bulbs;
    }

    public int getTotalWindowsIllumination() {
        return this.windowsNumber * WINDOW_ILLUMINATION;
    }

    public int getMAX_LIGHT() {
        return MAX_LIGHT;
    }

    public int getMIN_LIGHT() {
        return MIN_LIGHT;
    }

    public ArrayList<Furniture> getFurniture() {
        return furniture;
    }

    /**
     * Adds light bulb in the room and in the ArrayList<LightBulbs> bulbs
     *
     * @param bulb new object of LightBulbs
     * @return this room to make possible the call of another methods
     */
    public Room addLightBulb(LightBulbs bulb) {
        bulbs.add(bulb);
        return this;
    }

    /**
     * Adds furniture in the room and in the ArrayList<Furniture> furniture
     *
     * @param furniture new object of Furniture
     * @return this room to make possible the call of another methods
     */
    public Room addFurniture(Furniture furniture) {
        this.furniture.add(furniture);
        return this;
    }

    /**
     * Calculates the power of all light bulbs existed in the room
     *
     * @param bulbs ArrayList<LightBulbs> of bulbs existed in the room
     * @return sum of light bulbs power
     */
    public double getBulbsPower(ArrayList<LightBulbs> bulbs) {
        int bulbsPower = 0;
        for (int i = 0; i < bulbs.size(); i++) {
            bulbsPower += bulbs.get(i).getBulbPower();
        }
        return bulbsPower;
    }

    /**
     * Calculates total illumination in the room
     *
     * @return total illumination in the room
     */
    public double getTotalRoomIllimination() {
        return getTotalWindowsIllumination() + getBulbsPower(this.getBulbs());
    }

    /**
     * Calculates space which can be occupied with furniture in the room
     *
     * @return allowed space which can be occupied
     */
    public double getAllowedOccupiedSpace() {
        return RATIO_ALLOWED_SPACE_TAKEN * roomSize;
    }

    /**
     * Calculates minimum occupied space with furniture in the room
     *
     * @return minimum occupied space
     */
    public double getMINOccupiedSpace() {
        double minOccupiedSpace = 0;
        for (int i = 0; i < getFurniture().size(); i++) {
            if (getFurniture().get(i) instanceof SoftFurniture) {
                minOccupiedSpace += ((SoftFurniture) getFurniture().get(i)).getMinSize();
            } else if (getFurniture().get(i) instanceof Table) {
                minOccupiedSpace += ((Table) getFurniture().get(i)).getSize();
            }
        }
        return minOccupiedSpace;
    }

    /**
     * Calculates maximum occupied space with furniture in the room
     *
     * @return maximum occupied space
     */
    public double getMAXOccupiedSpace() {
        double maxOccupiedSpace = 0;
        for (int i = 0; i < getFurniture().size(); i++) {
            if (getFurniture().get(i) instanceof SoftFurniture) {
                maxOccupiedSpace += ((SoftFurniture) getFurniture().get(i)).getMaxSize();
            } else if (getFurniture().get(i) instanceof Table) {
                maxOccupiedSpace += ((Table) getFurniture().get(i)).getSize();
            }
        }
        return maxOccupiedSpace;
    }

    /**
     * Calculates minimum free space rest in the room
     *
     * @return minimum free space rest
     */
    public double getMINFreeSpaceRest() {
        return this.roomSize - this.getMAXOccupiedSpace();
    }

    /**
     * Calculates maximum free space rest in the room
     *
     * @return maximum free space rest
     */
    public double getMAXFreeSpaceRest() {
        return this.roomSize - this.getMINOccupiedSpace();
    }

    /**
     * Calculates the percent of free space rest in the room
     *
     * @return percent of free space rest
     */
    public double getMINFreeSpaceRestPERCENT() {
        return Math.round(((this.getMINFreeSpaceRest() / this.getRoomSize()) * 100));
    }

    /**
     * Verifies room if it can be build or not, verifies illumination and occupied space in the room
     *
     * @return ArrayList<Exception> of exceptions existed in the room or empty ArrayList<Exception>
     * if there isn't any exception
     */
    public ArrayList<Exception> verifyRoom() {
        ArrayList<Exception> exceptions = new ArrayList<>();
        if (this.getTotalWindowsIllumination() + this.getBulbsPower(this.getBulbs()) < this.getMIN_LIGHT()) {
            exceptions.add(new IlluminanceLackException());
        }
        if (this.getTotalWindowsIllumination() + this.getBulbsPower(this.getBulbs()) > this.getMAX_LIGHT()) {
            exceptions.add(new IlluminanceTooMuchException());
        }
        if (this.getMINOccupiedSpace() > this.getAllowedOccupiedSpace() ||
                this.getMAXOccupiedSpace() > this.getAllowedOccupiedSpace()) {
            exceptions.add(new SpaceUsageTooMuchException());
        }
        return exceptions;
    }

    /**
     * Calculates light bulbs power and convert it to String
     *
     * @return light bulbs power in String format
     */
    public String getBulbsPowerString() {
        StringBuilder bulbsPowerString = new StringBuilder();
        for (int i = 0; i < this.getBulbs().size(); i++) {
            bulbsPowerString.append(String.valueOf(this.getBulbs().get(i).getBulbPower()));
            bulbsPowerString.append(" lux");
            if (i != this.getBulbs().size() - 1) {
                bulbsPowerString.append(" ,");
            }
        }
        return bulbsPowerString.toString();
    }

    /**
     * Returns all room illumination in String Format
     */
    private String getRoomIlluminationString() {
        StringBuilder roomIllumination = new StringBuilder();
        if (this.getBulbs().size() != 0) {
            roomIllumination.append(String.format("\t\tIllumination = %s (%d windows, 700 lux each one, bulbs %s )",
                    decimalFormat.format(this.getTotalRoomIllimination()), this.getWindowsNumber(), this.getBulbsPowerString()));
            return roomIllumination.toString();
        } else if (this.getWindowsNumber() > 1) {
            roomIllumination.append(String.format("\t\tIllumination = %s (%s windows, 700 lux each one)",
                    decimalFormat.format(this.getTotalRoomIllimination()), decimalFormat.format(this.getWindowsNumber())));
        } else if (this.getWindowsNumber() == 1) {
            roomIllumination.append(String.format("\t\tIllumination = %s (%s window of 700 lux)",
                    decimalFormat.format(this.getTotalRoomIllimination()), decimalFormat.format(this.getWindowsNumber())));
        }
        return roomIllumination.toString();
    }

    /**
     * Verifies if there is furniture in the room
     *
     * @return boolean value has room furniture or not
     */
    public boolean hasFurniture() {
        if (this.getFurniture().size() != 0) {
            return true;
        }
        return false;
    }

    /**
     * Returns room area, occupied area and free area in String Format
     */
    private String getRoomAreaString() {
        StringBuilder roomArea = new StringBuilder();
        if (this.hasFurniture() == true) {
            roomArea.append(String.format("\r\n\t\t\tArea = %s m^2 (occupied %s-%s, guaranteed free of furniture %s m^2 or %s%% of area.)",
                    decimalFormat.format(this.getRoomSize()), decimalFormat.format(this.getMINOccupiedSpace()),
                    decimalFormat.format(this.getMAXOccupiedSpace()), decimalFormat.format(this.getMINFreeSpaceRest()),
                    decimalFormat.format(this.getMINFreeSpaceRestPERCENT())));
            return roomArea.toString();
        } else {
            roomArea.append(String.format("\r\n\t\t\tArea = %s m^2 (100%% of area is free of furniture.)",
                    decimalFormat.format(this.getRoomSize())));
        }
        return roomArea.toString();
    }

    /**
     * Prints furniture existed in the room in String Format or message that there isn't any furniture
     */
    private String getRoomFurnitureString() {
        StringBuilder roomFurniture = new StringBuilder();
        if (this.hasFurniture() == true) {
            for (Furniture furniture : this.getFurniture()) {
                if (furniture instanceof HardFurniture) {
                    roomFurniture.append(String.format("\t\t\t%s (area %s m^2)\r\n",
                            furniture.getName(), decimalFormat.format(((HardFurniture) furniture).getSize())));
                } else if (furniture instanceof SoftFurniture) {
                    roomFurniture.append(String.format("\t\t\t%s (area between %s - %s m^2)\r\n",
                            furniture.getName(), decimalFormat.format(((SoftFurniture) furniture).getMinSize()),
                            decimalFormat.format(((SoftFurniture) furniture).getMaxSize())));
                }
            }
            return roomFurniture.toString();
        } else {
            roomFurniture.append("\t\t\tThere isn't furniture in the room.");
            return roomFurniture.toString();
        }
    }

    /**
     * Returns description of the room in String Format
     */
    public String getRoomDescriptionString() {
        StringBuilder roomDescription = new StringBuilder();
        roomDescription.append("\t" + this.getName() + "\r\n");
        roomDescription.append(this.getRoomIlluminationString());
        roomDescription.append(this.getRoomAreaString());
        roomDescription.append("\r\n\t\tFurniture:" + "\r\n");
        roomDescription.append(this.getRoomFurnitureString());
        return roomDescription.toString();
    }
}
