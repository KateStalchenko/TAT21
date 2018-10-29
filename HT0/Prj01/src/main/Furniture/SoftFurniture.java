package main.Furniture;

/**
 * Represents object of SoftFurniture
 *
 * @author Katsiaryna Stalchanka
 * @since 22.10.2018
 */
public abstract class SoftFurniture extends Furniture {
    private int minSize;
    private int maxSize;

    public SoftFurniture(String name, int minSize, int maxSize) {
        super(name);
        this.minSize = minSize;
        this.maxSize = maxSize;
    }

    public int getMinSize() {
        return minSize;
    }

    public int getMaxSize() {
        return maxSize;
    }
}
