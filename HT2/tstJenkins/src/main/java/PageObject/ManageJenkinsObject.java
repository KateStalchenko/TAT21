package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author Katsiaryna Stalchanka
 * @since 06-Dec-18
 */
public class ManageJenkinsObject extends DriverPageObject {
    private By configureSystem = By.xpath("//div[@id='main-panel']//a[@title='Configure System']");
    private By configureGlobalSecurity = By.xpath("//div[@id='main-panel']//a[@title='Configure Global Security']");
    private By configureCredentials = By.xpath("//div[@id='main-panel']//a[@title='Configure Credentials']");
    private By globalToolConfiguration = By.xpath("//div[@id='main-panel']//a[@title='Global Tool Configuration']");
    private By reloadConfigurationFromDisk = By.xpath("//div[@id='main-panel']//a[.//*[text()='Reload Configuration from Disk']]");
    private By managePlugins = By.xpath("//div[@id='main-panel']//a[@title='Manage Plugins']");
    private By systemInformation = By.xpath("//div[@id='main-panel']//a[@title='System Information']");
    private By systemLog = By.xpath("//div[@id='main-panel']//a[@title='System Log']");

    private By loadStatistics = By.xpath("//div[@id='main-panel']//a[@title='Load Statistics']");
    private By jenkinsCLI = By.xpath("//div[@id='main-panel']//a[@title='Jenkins CLI']");
    private By scriptConsole = By.xpath("//div[@id='main-panel']//a[@title='Script Console']");
    private By mangeNodes = By.xpath("//div[@id='main-panel']//a[@title='Manage Nodes']");

    private By aboutJenkins = By.xpath("//div[@id='main-panel']//a[@title='About Jenkins']");
    private By mangeOldData = By.xpath("//div[@id='main-panel']//a[@title='Manage Old Data']");
    private By manageUsers = By.xpath("//div[@id='main-panel']//a[@title='Manage Users']");
    private By prepareForShutdown = By.xpath("//div[@id='main-panel']//a[@class='post ']");

    public ManageJenkinsObject(WebDriver driver) {
        super(driver);
    }

    /**
     * Clicks on Manage User link
     */
    public void clickManageUsers(){
        driver.findElement(manageUsers).click();
    }
}
