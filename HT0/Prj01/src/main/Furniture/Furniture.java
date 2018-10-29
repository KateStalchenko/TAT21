package main.Furniture;

/**
 * Abstract class of Furniture
 *
 * @author Katsiaryna Stalchanka
 * @since 22.10.2018
 */
public abstract class Furniture {
    private String name;

    public Furniture(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
