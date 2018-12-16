package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author Katsiaryna Stalchanka
 * @since 09-Dec-18
 */
public class DeleteUserPage extends BasePage {
    private By textLocator = By.xpath("//div[@id='main-panel']");
    private By yesLocator = By.id("yui-gen1");

    public DeleteUserPage(WebDriver driver) {
        super(driver);
    }

    public boolean isTextLocatorDisplayed() {
        return driver.findElement(textLocator).isDisplayed();
    }

    /**
     * Click on button Yes to confirm the removing of user
     */
    public void clickYesDelete() {
        driver.findElement(yesLocator).click();
    }
}
