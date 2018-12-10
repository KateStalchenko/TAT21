package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author Katsiaryna Stalchanka
 * @since 06-Dec-18
 */
public class HeaderPageObject extends DriverPageObject {
    private By jenkinsNameHeaderLocator = By.id("jenkins-name-icon");
    private By jenkinsImageLocator = By.id("jenkins-head-item");
    private By searchLocator = By.id("search-box");
    private By questionLocator = By.xpath("//div[@id='searchform']//img[@class='icon-help icon-sm']");
    private By userLocator = By.xpath("//div[@id='header']//a[@class='model-link inside inverse']");
    private By menuSelectorLocator = By.id("menuSelector");
    private By logOutLocator = By.xpath("//div[@id='header']//a[@href='/logout']");
    private By jenkinsLocator = By.xpath("//ul[@id='breadcrumbs']//a");
    private By jenkinsMeuSelectorLocator= By.id("menuSelector");
    private By arrowLocator = By.xpath("//ul[@id='breadcrumbs']//li[@class='children']");
    private By enableAutoRefreshLocator = By.xpath("//div[@id='right-top-nav']//a");

    public HeaderPageObject(WebDriver driver) {
        super(driver);
    }
}
