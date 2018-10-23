package main;

import java.util.ArrayList;

/**
 * Is used for calculating roots
 *
 * @author Katsiaryna Stalchanka
 */

public class QuadraticEquation {
    private double a;
    private double b;
    private double c;
    private double discriminant;

    QuadraticEquation(double arg1, double arg2, double arg3) {
        a = arg1;
        b = arg2;
        c = arg3;
    }

    /**
     * Calculates roots
     *
     * @return List of root(s)
     * @throws ArithmeticException if discriminant is less than zero
     */
    public ArrayList<Double> calculateRoots() {
        discriminant = b * b - (4 * a * c);
        ArrayList<Double> roots = new ArrayList<>();
        if (discriminant == 0) {
            double root = -(b / 2 * a);
            roots.add(root);
            return roots;
        } else if (discriminant > 0) {
            double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            roots.add(root1);
            roots.add(root2);
            return roots;
        } else if (discriminant < 0) {
            throw new ArithmeticException("The quadratic equation has no real solutions. Discriminant is less than zero.");
        }
        return roots;
    }
}
