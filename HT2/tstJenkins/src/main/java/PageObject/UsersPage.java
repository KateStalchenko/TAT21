package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * @author Katsiaryna Stalchanka
 * @since 09-Dec-18
 */
public class UsersPage extends BasePage {
    private By createUserLocator = By.xpath("//div[@id='tasks']//a[text()='Create User']");
    private By deleteUserLocator = By.xpath("//table[@id='people']//a[@href='user/tat21_user/delete']");

    public UsersPage(WebDriver driver) {
        super(driver);
    }


    public boolean isDisplayedCreateUserLocator() {
        return driver.findElement(createUserLocator).isDisplayed();
    }

    /**
     * Clicks on create user button
     */
    public void clickCreateUser() {
        driver.findElement(createUserLocator).click();
    }

    /**
     * Finds tr element with text "tat21_user"
     */
    public boolean findTrElementWithSpecifiedText() {
        if (!driver.findElements(By.xpath("//tr[.//a[text()='tat21_user']]")).isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * Clicks on delete user button
     */
    public void clickDeleteUser() {
        driver.findElement(deleteUserLocator).click();
    }

    /**
     * Verifies that tr with specified text doesn't exist on page
     *
     * @param text indicates the text which must to not exist on page
     * @return true if the text doesn't exist or false if the text exist
     */
    public boolean isUserExistInTable(String text) {
        List<WebElement> elements = driver.findElements(By.xpath("//tr[.//td[a]]"));
        for (WebElement element : elements) {
            if (element.getText().equals(text)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Verifies that href with specified data doesn't exist on page
     *
     * @param data represents data which it is needed to not find
     * @return true if it doesn't exist and false if it exists
     */
    public boolean isHrefExist(String data) {
        List<WebElement> elements = driver.findElements(By.xpath("//a[@href]"));
        for (WebElement element : elements) {
            if (element.getAttribute("href").equals(data)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Verifies that Create User Link exists
     */
    public void createUserLinkExist() {
        driver.findElement(createUserLocator).isDisplayed();
    }
}
