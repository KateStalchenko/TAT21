package PageObjectTests;

import PageObject.Pages;
import driver.DriverSingleton;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * @author Katsiaryna Stalchanka
 * @since 17-Dec-18
 */
public class tstBase {
    Pages pages = new Pages();

    @BeforeClass
    public void beforeClass() {
        DriverSingleton.getDriver();
    }

    @AfterClass
    public void afterClass() {
        DriverSingleton.closeDriver();
    }
}
