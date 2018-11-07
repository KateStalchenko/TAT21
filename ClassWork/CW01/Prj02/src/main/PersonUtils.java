package main;

import java.util.ArrayList;


public class PersonUtils {
    public void getMinAge(ArrayList<Person> personsList) {
        int minAge = personsList.get(0).getAge();
        for (Person human : personsList) {
            if (human.getAge() < minAge) {
                minAge = human.getAge();
            }
        }

        ArrayList<Person> peopleWithMinAge = new ArrayList<>();
        for (int i = 0; i < personsList.size(); i++) {
            if (personsList.get(i).getAge() == minAge) {
                peopleWithMinAge.add(personsList.get(i));
            }
        }

        System.out.println("Min age is " + minAge);
        for (Person humanMinAge : peopleWithMinAge) {
            System.out.println(humanMinAge.getFName() + " " + humanMinAge.getLName());
        }
        System.out.println();
    }

    public void getMaxAge(ArrayList<Person> personsList) {
        int maxAge = personsList.get(0).getAge();
        for (Person human : personsList) {
            if (human.getAge() > maxAge) {
                maxAge = human.getAge();
            }
        }

        ArrayList<Person> peopleWithMaxAge = new ArrayList<>();
        for (int i = 0; i < personsList.size(); i++) {
            if (personsList.get(i).getAge() == maxAge) {
                peopleWithMaxAge.add(personsList.get(i));
            }
        }

        System.out.println("Max age is " + maxAge);
        for (Person humanMaxAge : peopleWithMaxAge) {
            System.out.println(humanMaxAge.getFName() + " " + humanMaxAge.getLName());
        }
        System.out.println();
    }

    public void average(ArrayList<Person> personsList) {
        int totalAge = 0;
        int peopleNumber = personsList.size();

        for (Person person : personsList) {
            totalAge += person.getAge();
        }

        double averageAge = Math.round(totalAge / peopleNumber);

        System.out.println("Average age of all people is " + averageAge);
        System.out.println();
    }
}
