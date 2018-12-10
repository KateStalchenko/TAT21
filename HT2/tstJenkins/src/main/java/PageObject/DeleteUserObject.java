package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

/**
 * @author Katsiaryna Stalchanka
 * @since 09-Dec-18
 */
public class DeleteUserObject extends DriverPageObject {
    private By textLocator = By.xpath("//div[@id='main-panel']");
    private By yesLocator = By.id("yui-gen1");

    public DeleteUserObject(WebDriver driver) {
        super(driver);
    }

    public By getTextLocator() {
        return textLocator;
    }

    /**
     * Finds specified element or throws exception if such element doesn't exist
     * @param locator represents the locator of searched element
     */
    public void isElementDisplayed (By locator){
        if (driver.findElement(locator).isDisplayed()){
            return;
        }
        throw new NoSuchElementException("The element doesn't exist on page.");
    }

    /**
     * Click on button Yes to confirm the removing of user
     */
    public void clickYesDelete(){
        driver.findElement(yesLocator).click();
    }
}
