import org.testng.Assert;
import org.testng.annotations.Test;
import triangle.Triangle;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @author Katsiaryna Stalchanka
 * @since 22-Nov-18
 */

public class VerifyTriangle {
    final int TR_EQUILATERAL = 1;
    final int TR_ISOSCELES = 2;
    final int TR_ORDYNARY = 4;
    final int TR_RECTANGULAR = 8;

    NumberFormat formatter = new DecimalFormat("#0.0");


    @Test(groups = {"positive test"},
            dataProvider = "triangle", dataProviderClass = tstDataProviderTriangle.class)
    public void correctTriangle(double sideA, double sideB, double sideC) {
        Triangle triangle = new Triangle(sideA, sideB, sideC);
        Assert.assertTrue(triangle.checkTriangle());
    }

    @Test(groups = {"positive test"},
            dataProvider = "isoscelesTriangle", dataProviderClass = tstDataProviderTriangle.class)
    public void isoscelesTriangle(double sideA, double sideB, double sideC) {
        Triangle triangle = new Triangle(sideA, sideB, sideC);
        Assert.assertEquals(triangle.detectTriangle(), TR_ISOSCELES);
    }

    @Test(groups = {"positive test"},
            dataProvider = "equilateralTriangle", dataProviderClass = tstDataProviderTriangle.class)
    public void equilateralAndIsoscelesTriangle(double sideA, double sideB, double sideC) {
        Triangle triangle = new Triangle(sideA, sideB, sideC);
        Assert.assertEquals(triangle.detectTriangle(), TR_ISOSCELES | TR_EQUILATERAL);
    }

    @Test(groups = {"positive test"},
            dataProvider = "ordinaryTriangle", dataProviderClass = tstDataProviderTriangle.class)
    public void ordinaryTriangle(double sideA, double sideB, double sideC) {
        Triangle triangle = new Triangle(sideA, sideB, sideC);
        Assert.assertEquals(triangle.detectTriangle(), TR_ORDYNARY);
    }

    @Test(groups = {"positive test"},
            dataProvider = "rectangularTriangle", dataProviderClass = tstDataProviderTriangle.class)
    public void rectangularTriangle(double sideA, double sideB, double sideC) {
        Triangle triangle = new Triangle(sideA, sideB, sideC);
        Assert.assertEquals(triangle.detectTriangle(), TR_RECTANGULAR);
    }

    @Test(groups = {"positive test"},
            dataProvider = "rectangularIsoscelesTriangle", dataProviderClass = tstDataProviderTriangle.class)
    public void rectangularIsoscelesTriangle(double sideA, double sideB, double sideC) {
        Triangle triangle = new Triangle(sideA, sideB, sideC);
        Assert.assertEquals(triangle.detectTriangle(), (TR_RECTANGULAR | TR_ISOSCELES));
    }

    @Test(groups = {"positive test"},
            dataProvider = "findArea", dataProviderClass = tstDataProviderTriangle.class)
    public void findAreaTriangle(double sideA, double sideB, double sideC, double area) {
        Triangle triangle = new Triangle(sideA, sideB, sideC);
        Assert.assertEquals(triangle.getSquare(), area);
    }

    @Test(expectedExceptions = Exception.class, groups = {"negative test"},
            dataProvider = "incorrectTriangle", dataProviderClass = tstDataProviderTriangle.class)
    public void incorrectTriangle(double sideA, double sideB, double sideC) {
        Triangle triangle = new Triangle(sideA, sideB, sideC);
        Assert.assertFalse(triangle.checkTriangle());
    }

    @Test(expectedExceptions = Exception.class, groups = {"negative test"},
            dataProvider = "maxDoubleValue", dataProviderClass = tstDataProviderTriangle.class)
    public void maxDoubleValue(double sideA, double sideB, double sideC) {
        Triangle triangle = new Triangle(sideA, sideB, sideC);
    }


}

