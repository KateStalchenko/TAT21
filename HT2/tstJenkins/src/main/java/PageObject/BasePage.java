package PageObject;

import driver.DriverSingleton;
import org.openqa.selenium.WebDriver;

/**
 * @author Katsiaryna Stalchanka
 * @since 08-Dec-18
 */
public class BasePage {
    protected final WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage(String url) {
        DriverSingleton.getDriver().get(url);
    }
}
