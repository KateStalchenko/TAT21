package main.Furniture;

/**
 * Represents object of "HardFurniture"
 *
 * @author Katsiaryna Stalchanka
 * @since 29.10.2018
 */
public abstract class HardFurniture extends Furniture {
    private int size;

    public HardFurniture(String name, int size) {
        super(name);
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
