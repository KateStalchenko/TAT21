import org.testng.Assert;
import org.testng.annotations.Test;
import triangle.Triangle;

/**
 * @author Katsiaryna Stalchanka
 * @since 22-Nov-18
 */

public class VerifyTriangle {

    @Test(groups = {"positive test"})
    public void correctTriangle() {
        Triangle triangle = new Triangle(5, 2, 6);
    }

    @Test(groups = {"positive test"})
    public void isoscelesTriangle() {
        Triangle triangle = new Triangle(5.2, 5.2, 4);
        Assert.assertEquals(triangle.detectTriangle(), 2);
    }

    @Test(groups = {"positive test"})
    public void equilateralTriangle() {
        Triangle triangle = new Triangle(2, 2, 2);
        Assert.assertEquals(triangle.detectTriangle(), 3);
    }

    @Test(groups = {"positive test"})
    public void ordinaryTriangle() {
        Triangle triangle = new Triangle(2, 5, 6);
        Assert.assertEquals(triangle.detectTriangle(), 4);
    }

    @Test(groups = {"positive test"})
    public void rectangularTriangle() {
        Triangle triangle = new Triangle(6, 8, 10);
        Assert.assertEquals(triangle.detectTriangle(), 8);
    }

    @Test(groups = {"positive test"})
    public void findAreaTriangle() {
        Triangle triangle = new Triangle(4, 13, 15);
        Assert.assertEquals(triangle.getSquare(), 24);
    }

    @Test(expectedExceptions = Exception.class,
            groups = {"negative test"})
    public void incorrectTriangle() {
        Triangle triangle = new Triangle(1, 2, 8);
    }

    @Test(expectedExceptions = Exception.class,
            groups = {"negative test"})
    public void lessZeroSide() {
        Triangle triangle = new Triangle(1, -2, 8);
    }

    @Test(expectedExceptions = Exception.class,
            groups = {"negative test"})
    public void equalToZeroSide() {
        Triangle triangle = new Triangle(0, 0, 0);
    }

    @Test(expectedExceptions = Exception.class,
            groups = {"negative test"})
    public void maxDoubleValue() {
        Triangle triangle = new Triangle(Double.MAX_VALUE, (Double.MAX_VALUE - 8), 8);
    }


}

