package main;

import java.util.Date;

/**
 * Represents the object of file or directory
 *
 * @author Katsiaryna Stalchanka
 * @since 24.10.2018
 */
public class FileTableRow {
    private static final int KB_IN_BYTE = 1024;

    public static int getKbInByte() {
        return KB_IN_BYTE;
    }

    enum Type {
        DIR,
        FILE
    }

    private String name;
    private Type type;
    private Date creationTime;
    private double sizeInKB;

    public FileTableRow(String name, Type type, Date creationTime, double sizeInKB) {
        this.name = name;
        this.type = type;
        this.creationTime = creationTime;
        this.sizeInKB = sizeInKB;
    }

    public FileTableRow() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public double getSizeInKB() {
        return sizeInKB;
    }

    public void setSizeInKB(double sizeInKB) {
        this.sizeInKB = sizeInKB;
    }
}
