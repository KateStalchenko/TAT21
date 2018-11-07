package main;

import java.util.ArrayList;

/**
 * Represents the tools to work with collections
 */
public class CollectionTools {
    /**
     * Joins all paths of files in String
     * @param fileObjects of type   ArrayList<FileObject>
     * @return string value of paths
     */
    public String joinFilePaths(ArrayList<FileObject> fileObjects) {
        StringBuilder paths = new StringBuilder();
        for (FileObject file : fileObjects) {
            paths.append(file.getPath());
            paths.append("\r\n");
        }
        return paths.toString();
    }
}
