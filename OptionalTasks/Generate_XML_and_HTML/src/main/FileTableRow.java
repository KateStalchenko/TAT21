package main;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.Date;

/**
 * Represents the object of file or directory
 *
 * @author Katsiaryna Stalchanka
 * @since 31.10.2018
 */
@XStreamAlias("item")
public class FileTableRow {
    private static final int KB_IN_BYTE = 1024;

    enum Type {
        DIR,
        FILE
    }

    @XStreamAsAttribute
    private String name;

    @XStreamAsAttribute
    private Type type;

    @XStreamAsAttribute
    @XStreamAlias("created")
    private Date creationTime;

    @XStreamAsAttribute
    @XStreamAlias("size")
    private double sizeInKB;

    @XStreamImplicit
    private ArrayList<FileTableRow> childFiles;

    public FileTableRow(String name, Type type, Date creationTime, double sizeInKB) {
        this.name = name;
        this.type = type;
        this.creationTime = creationTime;
        this.sizeInKB = sizeInKB;
    }

    public FileTableRow() {
    }

    public FileTableRow(String name) {
        this.name = name;
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

    public ArrayList<FileTableRow> getChildFiles() {
        return childFiles;
    }

    public void setChildFiles(ArrayList<FileTableRow> childFiles) {
        this.childFiles = childFiles;
    }

    public static int getKbInByte() {
        return KB_IN_BYTE;
    }
}
