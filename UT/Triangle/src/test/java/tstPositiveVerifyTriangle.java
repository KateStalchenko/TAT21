import org.testng.Assert;
import org.testng.annotations.Test;
import triangle.Triangle;

/**
 * @author Katsiaryna Stalchanka
 * @since 22-Nov-18
 */

public class tstPositiveVerifyTriangle {
    final int TR_EQUILATERAL = 1;
    final int TR_ISOSCELES = 2;
    final int TR_ORDYNARY = 4;
    final int TR_RECTANGULAR = 8;

    @Test(dataProvider = "triangle", dataProviderClass = tstDataProviderTriangle.class)
    public void tstCreateCorrectTriangle(double sideA, double sideB, double sideC) {
        Triangle triangle = new Triangle(sideA, sideB, sideC);
        Assert.assertTrue(triangle.checkTriangle());
    }

    @Test(dataProvider = "isoscelesTriangle", dataProviderClass = tstDataProviderTriangle.class)
    public void tstCreateIsoscelesTriangle(double sideA, double sideB, double sideC) {
        Triangle triangle = new Triangle(sideA, sideB, sideC);
        Assert.assertEquals(triangle.detectTriangle(), TR_ISOSCELES);
    }

    @Test(dataProvider = "equilateralTriangle", dataProviderClass = tstDataProviderTriangle.class)
    public void tstCreateEquilateralTriangle(double sideA, double sideB, double sideC) {
        Triangle triangle = new Triangle(sideA, sideB, sideC);
        Assert.assertEquals(triangle.detectTriangle(), TR_ISOSCELES | TR_EQUILATERAL);
    }

    @Test(dataProvider = "ordinaryTriangle", dataProviderClass = tstDataProviderTriangle.class)
    public void tstCreateOrdinaryTriangle(double sideA, double sideB, double sideC) {
        Triangle triangle = new Triangle(sideA, sideB, sideC);
        Assert.assertEquals(triangle.detectTriangle(), TR_ORDYNARY);
    }

    @Test(dataProvider = "rectangularTriangle", dataProviderClass = tstDataProviderTriangle.class)
    public void tstCreateRectangularTriangle(double sideA, double sideB, double sideC) {
        Triangle triangle = new Triangle(sideA, sideB, sideC);
        Assert.assertEquals(triangle.detectTriangle(), TR_RECTANGULAR);
    }

    @Test(dataProvider = "rectangularIsoscelesTriangle", dataProviderClass = tstDataProviderTriangle.class)
    public void tstCreateRectangularIsoscelesTriangle(double sideA, double sideB, double sideC) {
        Triangle triangle = new Triangle(sideA, sideB, sideC);
        Assert.assertEquals(triangle.detectTriangle(), (TR_RECTANGULAR | TR_ISOSCELES));
    }

    @Test(dataProvider = "findArea", dataProviderClass = tstDataProviderTriangle.class)
    public void tstFindAreaTriangle(double sideA, double sideB, double sideC, double area) {
        Triangle triangle = new Triangle(sideA, sideB, sideC);
        Assert.assertEquals(triangle.getSquare(), area);
    }
}

