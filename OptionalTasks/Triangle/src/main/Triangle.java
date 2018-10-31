package main;

/**
 * Represents object of triangle
 *
 * @author Katsiaryna Stalchanka
 * @since 29.10.2018
 */
public class Triangle {
    private double sideA;
    private double sideB;
    private double sideC;

    public Triangle(double sideA, double sideB, double sideC) throws Exception {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
        isSidesPositiveNumbers();
        verifyIfExist();
    }

    public double getSideA() {
        return sideA;
    }

    public double getSideB() {
        return sideB;
    }

    public double getSideC() {
        return sideC;
    }

    /**
     * Identifies type of triangle
     *
     * @return String message about this type
     */
    public String getTriangleType() {
        if (isIsoscelesTriangle() == true) {
            return "Your triangle is Isosceles Triangle.";
        }
        if (isEquilateralTriangle() == true) {
            return "Your triangle is Equilateral Triangle.";
        }
        return "Your triangle is ordinary.";
    }

    /**
     * Verifies if the triangle can exist
     *
     * @throws Exception if sides a + b < c, this signifies that triangle cannot exist
     */
    private void verifyIfExist() throws Exception {
        if (getSideA() + getSideB() > getSideC() ||
                getSideA() + getSideC() > getSideB() ||
                getSideB() + getSideC() > getSideC()) {
            return;
        }
        throw new Exception("This triangle cannot exist. You should observe a rule: a + b > c");
    }

    /**
     * Verifies if all sides of triangle are positive numbers
     * @throws Exception when at least one side is equal to zero or less than zero
     */
    private void isSidesPositiveNumbers() throws Exception {
        if (getSideA() > 0 && getSideB() > 0 && getSideC() > 0) {
            return;
        }
        throw new Exception("The sides of triangle cannot be equal to zero or less than zero.");
    }

    /**
     * Verifies if triangle is equilateral
     * @return true if it is Equilateral or false if it isn't
     */
    private boolean isEquilateralTriangle() {
        if ((getSideA() == getSideB()) && (getSideB() == getSideC())) {
            return true;
        }
        return false;
    }

    /**
     * Verifies if triangle is Isosceles
     * @return true if it is Isosceles or false if it isn't
     */
    private boolean isIsoscelesTriangle() {
        if ((getSideA() == getSideB()) || (getSideB() == getSideC()) || (getSideC() == getSideA())) {
            return true;
        }
        return false;
    }
}
