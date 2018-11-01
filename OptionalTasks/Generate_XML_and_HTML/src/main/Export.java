package main;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Katsiaryna Stalchanka
 * @since 31.10.2018
 */
public interface Export {
    void export(String filePath, ArrayList<FileTableRow> rows) throws IOException;
}
