package main;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;
import java.util.Set;

/**
 * Represents method to import xml file
 */
class XmlImporter {
    /**
     * Imports the xml file to the Set<Person> to avoid duplicates
     * @param file represents xml file
     * @return Set<Person> of persons from xml file
     * @throws IOException
     */
    Set<Person> xmlImporter(FileInputStream file) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        String xmlPersons = inputStreamToString(file);
        return xmlMapper.reader().forType(new TypeReference<Set<Person>>() {
        }).readValue(xmlPersons);
    }

    private static String inputStreamToString(InputStream is) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        bufferedReader.close();
        return stringBuilder.toString();
    }
}
