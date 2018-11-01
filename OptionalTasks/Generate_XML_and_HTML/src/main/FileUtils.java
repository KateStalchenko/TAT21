package main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Contains methods to interact with files
 *
 * @author Katsiaryna Stalchanka
 * @since 31.10.2018
 */

public class FileUtils {
    /**
     * Calculates the size of file or directory
     *
     * @param rootFile
     * @return this size
     */
    public double getSize(File rootFile) {
        double size = 0;
        File[] files = rootFile.listFiles();
        for (File file1 : files) {
            if (file1.isDirectory()) {
                size += getSize(file1);
            } else {
                size += file1.length();
            }
        }
        return size;
    }

    public String formatDate(Date rootDate) {
        DateFormat df = new SimpleDateFormat("dd.MM.YYYY");
        return df.format(rootDate);
    }

    public ArrayList<FileTableRow> collectAllFileData(File[] dataFromDir) throws IOException {
        ArrayList<FileTableRow> allFileData = new ArrayList<>();

        for (File currentFile : dataFromDir) {
            FileTableRow fileTableRow = generateFileInfo(currentFile);
            allFileData.add(fileTableRow);
        }
        return allFileData;
    }

    public ArrayList<FileTableRow> getChildFiles(File rootFile) throws IOException {
        ArrayList<FileTableRow> childFiles = new ArrayList<>();
        File[] files = rootFile.listFiles();
        for (File currentFile : files) {
            FileTableRow tempFileTableRow = generateFileInfo(currentFile);
            if (currentFile.isDirectory()) {
                tempFileTableRow.setChildFiles(getChildFiles(currentFile));
            } else {
                tempFileTableRow.setChildFiles(new ArrayList<>());
            }
            childFiles.add(tempFileTableRow);
        }
        return childFiles;
    }

    private FileTableRow generateFileInfo(File tempFile) throws IOException {
        FileTableRow fileTableRow = new FileTableRow();
        BasicFileAttributes attributes = Files.readAttributes(Paths.get(tempFile.getPath()), BasicFileAttributes.class);
        fileTableRow.setName(tempFile.getName());
        Date date = new Date(attributes.creationTime().toMillis());
        fileTableRow.setCreationTime(date);

        if (tempFile.isFile()) {
            fileTableRow.setType(FileTableRow.Type.FILE);
            fileTableRow.setSizeInKB(tempFile.length() / fileTableRow.getKbInByte());
        } else if (tempFile.isDirectory()) {
            fileTableRow.setType(FileTableRow.Type.DIR);
            fileTableRow.setChildFiles(getChildFiles(tempFile));
            double size = new FileUtils().getSize(tempFile);
            fileTableRow.setSizeInKB(size / fileTableRow.getKbInByte());
        }
        return fileTableRow;
    }
}
