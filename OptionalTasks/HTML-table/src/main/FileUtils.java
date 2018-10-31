package main;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Contains methods to interact with files
 *
 * @author Katsiaryna Stalchanka
 * @since 24.10.2018
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
}
