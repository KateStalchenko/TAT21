package main;


public class Rectangle implements Figures {
    private double aSide;
    private double bSide;

    public Rectangle(double aSide, double bSide) {
        this.aSide = aSide;
        this.bSide = bSide;
    }

    public Rectangle() {
    }

    public double getaSide() {
        return aSide;
    }

    public void setaSide(double aSide) {
        this.aSide = aSide;
    }

    public double getbSide() {
        return bSide;
    }

    public void setbSide(double bSide) {
        this.bSide = bSide;
    }

    @Override
    public double getArea() {
        double area = 0;
        try {
            area = getaSide() * getbSide();
        } catch (Exception e) {
            System.out.println("Too large numbers.");
        }
        return area;
    }
}
