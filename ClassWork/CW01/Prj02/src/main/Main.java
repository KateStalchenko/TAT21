package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Person> persons = new ArrayList<>();
        int number;
        try {
            System.out.println("Enter the number of persons:\r\n");
            number = Integer.parseInt(reader.readLine());
            if (number<0){
                System.out.println("You should write a positive number.");
                return;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("You should write a positive integer NUMBER.");
            return;
        }
        System.out.println("Enter the persons:\r\n");
        for (int i = 0; i < number; i++) {
            String personData = reader.readLine();
            String[] personSplit;
            try {
                personSplit = personData.split(" ");
                persons.add(new Person(personSplit[0], personSplit[1], Integer.parseInt(personSplit[2])));
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("You should write FirstNAme LastName Age");
                return;
            }
        }

        new PersonUtils().getMinAge(persons);
        new PersonUtils().getMaxAge(persons);
        new PersonUtils().average(persons);

    }
}
