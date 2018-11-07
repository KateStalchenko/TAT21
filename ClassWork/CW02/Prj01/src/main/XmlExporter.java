package main;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;

/**
 * Exports to the xml document
 */
public class XmlExporter {
    /**
     * Exports indicated data to the xml document in the indicated path
     * @param path indicates where will be created the xml file
     * @param fileObjects represents data which will be converted to the xml
     * @throws IOException
     */
    public <T> void exportXml(String path, T fileObjects) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.writeValue(new File(path), fileObjects);
    }
}
