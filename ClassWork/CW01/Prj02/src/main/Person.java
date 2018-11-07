package main;

public class Person {
    private String FName;
    private String LName;
    private int Age;

    public Person(String FName, String LName, int age) {
        this.FName = FName;
        this.LName = LName;
        Age = age;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        if (age >= 1 && age <= 200) {
            Age = age;
        } else {
            throw new IllegalArgumentException("The incorrect age!");
        }
    }
}
