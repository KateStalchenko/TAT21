import org.testng.Assert;
import org.testng.annotations.Test;
import triangle.Triangle;

/**
 * @author Katsiaryna Stalchanka
 * @since 29-Nov-18
 */

public class tstNegativeVerifyTriangle {
    @Test(expectedExceptions = Exception.class,
            dataProvider = "incorrectTriangle", dataProviderClass = tstDataProviderTriangle.class)
    public void tstCreateIncorrectTriangle(double sideA, double sideB, double sideC) {
        Triangle triangle = new Triangle(sideA, sideB, sideC);
        Assert.assertFalse(triangle.checkTriangle());
    }

    @Test(expectedExceptions = Exception.class,
            dataProvider = "maxDoubleValue", dataProviderClass = tstDataProviderTriangle.class)
    public void tstCreateMaxDoubleValue(double sideA, double sideB, double sideC) {
        Triangle triangle = new Triangle(sideA, sideB, sideC);
    }
}
