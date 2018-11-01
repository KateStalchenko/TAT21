package main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Katsiaryna Stalchanka
 * @since 31.10.2018
 */
public class Main {
    public static void main(String[] args) throws IOException {
        File dir = new File("E:\\Documents\\Portugais\\ДОКУМЕНТЫ");
        File[] files = dir.listFiles();
        ArrayList<FileTableRow> filesInfo = new FileUtils().collectAllFileData(files);

        new HtmlExporter().export("E:\\Dev\\html_table.html\\", filesInfo);
        new XmlExporter().export("E:\\Dev\\xml_table.xml\\", filesInfo);

    }
}
