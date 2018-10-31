package main;

import java.util.ArrayList;

/**
 * Creates HTML table
 * @author Katsiaryna Stalchanka
 * @since 25.10.2018
 */

public class HTML_Generator {

    public String tableGenerator(ArrayList<FileTableRow> rootFilesTableRow) {
        StringBuilder table = new StringBuilder("<table border=\"1\" cellspacing=\"0\">\n" +
                "\t<tbody>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<th>NAME</th>\n" +
                "\t\t\t<th>TYPE</th>\n" +
                "\t\t\t<th>DATE OF CREATION</th>\n" +
                "\t\t\t<th>SIZE (in KB)</th>\n" +
                "\t\t</tr>");

        for (FileTableRow file : rootFilesTableRow) {
            table.append("<tr>");
            table.append("<td>" + file.getName() + "</td>");
            table.append("<td>" + file.getType() + "</td>");
            table.append("<td>" + new FileUtils().formatDate(file.getCreationTime()) + "</td>");
            table.append("<td>" + Math.round(file.getSizeInKB()) + "</td>");
            table.append("</tr>");
        }
        table.append("</tbody>\n" +
                "</table>");
        return String.valueOf(table);
    }
}
