package main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
public class Solution {
    /**
     * The entry point to the program
     * @param args represents paths and extension of files
     * @throws IOException when the wrong paths were indicated
     */
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("You need to enter at least One Path and extension\r\n"
                    + "Example: \"C:SomePath\" docx");
        }

        ArrayList<String> paths = new ArrayList<>();
        String extension = args[args.length - 1];
        for (int i = 0; i < args.length - 1; i++) {
            paths.add(args[i]);
        }

        ArrayList<File> allIndicatedFiles;
        try {
            allIndicatedFiles = new FileTools().getAllIndicatedFiles(extension, paths);
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return;
        }
        if (allIndicatedFiles.size()==0){
            System.out.println("There isn't any file with the same extension in the directory(ies).\r\n"+
                    "Or, probably, you have entered the wrong extension.");
        }

        ArrayList<FileObject> fileObjects = new FileTools().convertToFileObjects(allIndicatedFiles);
        System.out.println(new CollectionTools().joinFilePaths(fileObjects));
        new XmlExporter().exportXml("E://Dev//files.xml", fileObjects);
    }
}
