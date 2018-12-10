package PageObject;

import ConstantsString.StringConsts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * @author Katsiaryna Stalchanka
 * @since 09-Dec-18
 */
public class UsersObject extends DriverPageObject {
    private By createUserLocator = By.xpath("//div[@id='tasks']//a[text()='Create User']");
    private By deleteUserLocator = By.xpath("//table[@id='people']//a[@href='user/tat21_user/delete']");

    public UsersObject(WebDriver driver) {
        super(driver);
    }

    public By getCreateUserLocator() {
        return createUserLocator;
    }

    /**
     * Verifies if element exist on page
     * @param element
     */
    public void isDisplayed(By element) {
        if (driver.findElement(element).isDisplayed() == true) {
            return;
        }
        throw new Error("The element doesn't exist");
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
    public void findTrElementWithSpecifiedText() {
        driver.findElements(By.xpath("//tr[.//a[text()='tat21_user']]"));
    }

    /**
     * Clicks on delete user button
     */
    public void clickDeleteUser() {
        driver.findElement(deleteUserLocator).click();
    }

    /**
     * Verifies that tr with specified text doesn't exist on page
     * @param text indicates the text which must to not exist on page
     * @return true if the text doesn't exist or false if the text exist
     */
    public boolean notExistTrWithSpecifiedText(String text) {
        List<WebElement> elements = driver.findElements(By.xpath("//tr[.//td[a]]"));
        for (WebElement element:elements){
            if (element.getText().equals(text)){
                return false;
            }
        }
        return true;
    }

    /**
     * Verifies that href with specified data doesn't exist on page
     * @param data represents data which it is needed to not find
     * @return true if it doesn't exist and false if it exists
     */
    public boolean notExistHREFWithSpecifiedData(String data){
        List<WebElement> elements = driver.findElements(By.xpath("//a[@href]"));
        for (WebElement element:elements){
            if (element.getAttribute("href").equals(data)){
                return false;
            }
        }
        return true;
    }

    /**
     * Verifies that Create User Link exists
     */
    public void createUserLinkExist(){
        driver.findElement(createUserLocator).isDisplayed();
    }
}
