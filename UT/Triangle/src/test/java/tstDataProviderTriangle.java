import org.testng.annotations.DataProvider;

/**
 * @author Katsiaryna Stalchanka
 * @since 29-Nov-18
 */

public class tstDataProviderTriangle {
    @DataProvider(name = "triangle")
    public Object[][] dataTriangle() {
        return new Object[][]{
                {5, 2, 6},
                {2, 5, 6},
                {6, 2, 5}
        };
    }

    @DataProvider(name = "isoscelesTriangle")
    public Object[][] dataIsoscelesTriangle() {
        return new Object[][]{
                {5.2, 5.2, 4},
                {5.2, 4, 5.2},
                {4, 5.2, 5.2}
        };
    }

    @DataProvider(name = "equilateralTriangle")
    public Object[][] dataEquilateralTriangle() {
        return new Object[][]{
                {9, 9, 9},
                {12, 12, 12}
        };
    }

    @DataProvider(name = "ordinaryTriangle")
    public Object[][] dataOrdinaryTriangle() {
        return new Object[][]{
                {5, 6, 2},
                {2, 5, 6},
                {6, 2, 5}
        };
    }

    @DataProvider(name = "rectangularTriangle")
    public Object[][] dataRectangularTriangle() {
        return new Object[][]{
                {4, 5, 3},
                {3, 4, 5},
                {5, 3, 4}
        };
    }

    @DataProvider(name = "rectangularIsoscelesTriangle")
    public Object[][] dataRectangularIsoscelesTriangle() {
        return new Object[][]{
                {4, 4, 5.5},
                {4, 5.5, 4},
                {5.5, 4, 4}
        };
    }

    @DataProvider(name = "incorrectTriangle")
    public Object[][] dataIncorrectTriangle() {
        return new Object[][]{
                {1, 2, 8},
                {1, -2, 8},
                {-2, 1, 8},
                {1, 8, -2},
                {0, 0, 0},
                {0, 5, 0},
                {0, 2, 2},
        };
    }

    @DataProvider(name = "findArea")
    public Object[][] dataFindArea() {
        return new Object[][]{
                {3, 5, 4, 6},
                {5, 4, 3, 6},
                {4, 3, 5, 6},
        };
    }

    @DataProvider(name = "maxDoubleValue")
    public Object[][] dataMaxDoubleValue() {
        return new Object[][]{
                {Double.MAX_VALUE, 5, 4},
                {5, 4, Double.MAX_VALUE},
                {4, Double.MAX_VALUE, 5},
        };
    }
}
