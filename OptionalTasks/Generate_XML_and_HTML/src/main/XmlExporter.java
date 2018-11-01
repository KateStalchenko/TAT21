package main;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Katsiaryna Stalchanka
 * @since 31.10.2018
 */

public class XmlExporter implements Export {
    @Override
    public void export(String filePath, ArrayList<FileTableRow> rows) throws IOException {
        FileWriter writeXml = new FileWriter(filePath);
        XStream xstream = new XStream(new StaxDriver());

        xstream.processAnnotations(FileTableRow.class);
        String dateFormat = "dd-MM-YYYY";
        String[] acceptableFormats = {dateFormat};
        xstream.registerConverter(new DateConverter(dateFormat, acceptableFormats));

        writeXml.write(xstream.toXML(rows));
        writeXml.flush();
        writeXml.close();
    }

}

