package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class Solution {
    /**
     * The Entry Point To The Program
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        FileInputStream fileReader = new FileInputStream(args[0]);
        Set<Person> persons = new XmlImporter().xmlImporter(fileReader);
        ArrayList<String> largestAndTheSmallestFIO = new PersonTools().detectTheLargestAndTheSmallest(persons);
        for (String FIO : largestAndTheSmallestFIO) {
            System.out.println(FIO);
        }
    }
}
