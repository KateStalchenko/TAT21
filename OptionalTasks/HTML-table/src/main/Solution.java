package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Katsiaryna Stalchanka
 * @since 24.10.2018
 */

public class Solution {
    /**
     * Entry point to the Program
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        File dir = new File("E:\\Documents\\Portugais\\ДОКУМЕНТЫ");
        File[] dataFromDir = dir.listFiles();
        ArrayList<FileTableRow> filesTableRow = new ArrayList<>();

        for (File file : dataFromDir) {
            FileTableRow fileForTable = new FileTableRow();
            BasicFileAttributes attributes = Files.readAttributes(Paths.get(file.getPath()), BasicFileAttributes.class);
            double size = 0;
            fileForTable.setName(file.getName());
            Date d1 = new Date(attributes.creationTime().toMillis());
            fileForTable.setCreationTime(d1);
            if (file.isDirectory()) {
                fileForTable.setType(FileTableRow.Type.DIR);
                size += new FileUtils().getSize(file);
                fileForTable.setSizeInKB(size / fileForTable.getKbInByte());
            } else if (file.isFile()) {
                fileForTable.setType(FileTableRow.Type.FILE);
                fileForTable.setSizeInKB(file.length() / fileForTable.getKbInByte());
            }
            filesTableRow.add(fileForTable);
        }

        File myTable = new File("E:\\Dev\\notes3.html");
        FileWriter writer = new FileWriter(myTable);
        writer.write(new HTML_Generator().tableGenerator(filesTableRow));
        writer.flush();
    }
}
