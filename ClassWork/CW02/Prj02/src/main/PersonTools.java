package main;

import java.util.ArrayList;
import java.util.Set;

/**
 * Represents necessary methods to work with Person objects
 */
public class PersonTools {

    /**
     * Detects the largest and the smallest FIO in the Set<Person>
     * @param persons represents Set<Person> in which it must be found the largest and the smallest FIO
     * @return ArrayList<String> with the largest and the smallest FIO
     */

    public ArrayList<String> detectTheLargestAndTheSmallest(Set<Person> persons) {
        ArrayList<Person> tempPersons = new ArrayList<>();
        tempPersons.addAll(persons);
        ArrayList<String> smallest = new ArrayList<>();
        smallest.add("The smallest FIO:");
        ArrayList<String> largest = new ArrayList<>();
        largest.add("The largest FIO:");

        int largestFIO = tempPersons.get(0).getFio().length();
        int smallestFIO = tempPersons.get(0).getFio().length();

        for (Person person : tempPersons) {
            if (person.getFio().length() > largestFIO) {
                largestFIO = person.getFio().length();
            }
            if (person.getFio().length() < smallestFIO) {
                smallestFIO = person.getFio().length();
            }
        }
        for (Person person : tempPersons) {
            if (person.getFio().length() == largestFIO) {
                largest.add(person.getFio());
            }
            if (person.getFio().length() == smallestFIO) {
                smallest.add(person.getFio());
            }
        }
        ArrayList<String> result = new ArrayList<>();
        result.addAll(smallest);
        result.addAll(largest);
        return result;
    }
}
