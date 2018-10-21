package main;

import java.util.ArrayList;

public class Main {
    /**
     * The entry point to the program
     * @param args Command Line Params
     */
    public static void main(String[] args) {
        double[] array;
        ArrayList<Double> roots;

        try {
            array = new NumberParser().parseStringArrayToDoubleArray(args);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            return;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        QuadraticEquation equation = new QuadraticEquation(array[0], array[1], array[2]);
        try {
            roots = equation.calculateRoots();
            for (int i = 0; i < roots.size(); i++) {
                System.out.println("Root " + i + " = " + roots.get(i));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return;
        }
        catch (ArithmeticException e){
            System.out.println(e.getMessage());
        }
    }
}
