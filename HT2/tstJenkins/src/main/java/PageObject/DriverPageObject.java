package PageObject;

import org.openqa.selenium.WebDriver;

/**
 * @author Katsiaryna Stalchanka
 * @since 08-Dec-18
 */
public class DriverPageObject {
    protected final WebDriver driver;

    public DriverPageObject(WebDriver driver) {
        this.driver = driver;
    }
}
