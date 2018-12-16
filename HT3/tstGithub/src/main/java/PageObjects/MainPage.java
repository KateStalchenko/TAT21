package PageObjects;

import com.epam.ta.pages.AbstractPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Katsiaryna Stalchanka
 * @since 13-Dec-18
 */
public class MainPage extends AbstractPage {
    private static final String BASE_URL = "https://github.com/";

    @FindBy(xpath = "//input[@class='form-control header-search-input jump-to-field js-jump-to-field js-site-search-focus']")
    private WebElement inputSearch;

    @FindBy(xpath = "//div[@class='header-search   js-site-search position-relative js-jump-to']")
    private WebElement divSearch;

    @FindBy(xpath = "//li[@id='jump-to-suggestion-search-global']")
    private WebElement firstResult;

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage() {
        driver.navigate().to(BASE_URL);
    }

    public void setValueInputSearch(String text){
        divSearch.click();
        sendKeys(text);
        clickFirstResult();
    }

    private void sendKeys(String text){
        Actions action = new Actions(driver);
        action.sendKeys(text).perform();
    }

    private void clickFirstResult(){
        firstResult.click();
    }
}
