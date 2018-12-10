package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author Katsiaryna Stalchanka
 * @since 06-Dec-18
 */
public class TasksMenuObject extends DriverPageObject {
    private By newItem = By.xpath("//div[@id='tasks']//a[text()='New Item']");
    private By people = By.xpath("//div[@id='tasks']//a[text()='People']");
    private By buildHistory = By.xpath("//div[@id='tasks']//a[text()='Build History']");
    private By manageJenkins = By.xpath("//div[@id='tasks']//a[text()='Manage Jenkins']");
    private By myViews = By.xpath("//div[@id='tasks']//a[text()='My Views']");
    private By lockableResources = By.xpath("//div[@id='tasks']//a[text()='Lockable Resources']");
    private By credentials = By.xpath("//div[@id='tasks']//a[text()='Credentials']");
    private By newView = By.xpath("//div[@id='tasks']//a[text()='New View']");

    public TasksMenuObject(WebDriver driver) {
        super(driver);
    }

    /**
     * Clicks on Manage Jenkins link
     */
    public void clickManageJenkins(){
        driver.findElement(manageJenkins).click();
    }
}
