package main;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Represents necessary methods to work with files
 */
public class FileTools {
    /**
     * Gets all files of indicated extension from all indicated paths
     * @param extension indicates extension of files which must to be found in the paths
     * @param paths indicates all directories where we have to search files
     * @return ArrayList<File> of found files
     * @throws InvalidPathException when the wrong path was indicated
     */
    ArrayList<File> getAllIndicatedFiles(String extension, ArrayList<String> paths) throws InvalidPathException {
        String type[] = {extension};
        ArrayList<File> allFiles = new ArrayList<>();
        for (String path : paths) {
            allFiles.addAll(FileUtils.listFiles(new File(path), type, true));
        }
        return allFiles;
    }

    /**
     * Converts all found files to FileObject
     * @param rootFiles ArrayList<File> represents all files
     * @return ArrayList<FileObject> of found files
     * @throws IOException
     */
    ArrayList<FileObject> convertToFileObjects(ArrayList<File> rootFiles) throws IOException {
        BasicFileAttributes attributes;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ArrayList<FileObject> fileObjects = new ArrayList<>();

        for (File file : rootFiles) {
            attributes = Files.readAttributes(Paths.get(file.getPath()), BasicFileAttributes.class);
            Date date = new Date(attributes.creationTime().toMillis());
            FileObject fileObject = new FileObject();
            fileObject.setName(file.getName());
            fileObject.setPath(file.getPath());
            fileObject.setSize(file.length());
            fileObject.setDateOfCreation(format.format(date));

            fileObjects.add(fileObject);
        }
        return fileObjects;
    }
}
