package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Katsiaryna Stalchanka
 * @since 31.10.2018
 */
public class HtmlExporter implements Export {
    @Override
    public void export(String filePath, ArrayList<FileTableRow> rows) throws IOException {
        StringBuilder table = new StringBuilder("<table border=\"1\" cellspacing=\"0\">\n" +
                "\t<tbody>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<th>NAME</th>\n" +
                "\t\t\t<th>TYPE</th>\n" +
                "\t\t\t<th>DATE OF CREATION</th>\n" +
                "\t\t\t<th>SIZE (in KB)</th>\n" +
                "\t\t</tr>");

        for (FileTableRow file : rows) {
            table.append("<tr>");
            table.append("<td>" + file.getName() + "</td>");
            table.append("<td>" + file.getType() + "</td>");
            table.append("<td>" + new FileUtils().formatDate(file.getCreationTime()) + "</td>");
            table.append("<td>" + Math.round(file.getSizeInKB()) + "</td>");
            table.append("</tr>");
        }
        table.append("</tbody>\n" +
                "</table>");

        File htmlFile = new File(filePath);
        FileWriter fileWriter = new FileWriter(htmlFile);
        fileWriter.write(table.toString());
        fileWriter.flush();
    }
}
