package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Katsiaryna Stalchanka
 * @since 29.10.2018
 */
public class Main {
    /**
     * Entry point to the program
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        double number_A = 0;
        double number_B = 0;
        double number_C = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter three numbers, sides of the triangle.");
        try {
            System.out.println("Enter the first number: ");
            number_A = Double.parseDouble(reader.readLine());
            System.out.println("Enter the second number: ");
            number_B = Double.parseDouble(reader.readLine());
            System.out.println("Enter the third number: ");
            number_C = Double.parseDouble(reader.readLine());
        } catch (IllegalArgumentException e) {
            System.out.println("Please, enter the numbers!");
            return;
        }

        Triangle triangle;

        try {
            triangle = new Triangle(number_A, number_B, number_C);
            System.out.println(triangle.getTriangleType());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
