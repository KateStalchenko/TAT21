package PageObjects;

import com.epam.ta.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Katsiaryna Stalchanka
 * @since 13-Dec-18
 */
public class SearchPage extends AbstractPage {
    private static final String BASE_URL = "https://github.com/search";

    @FindBy(xpath = "//div[@id='js-pjax-container']//a[text()='Users']")
    private WebElement users;

    @FindBy(xpath = "//div[@id='user_search_results']//div[@class='user-list-info ml-2 min-width-0']//a[//em[text()='afc163']]")
    private WebElement userName;

    public SearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage() {
        driver.navigate().to(BASE_URL);
    }

    public void clickUsers() {
        users.click();
    }

    public boolean existPerson(String text) {
        if (driver.findElements(By.xpath(String.format("//div[contains(@class, 'user-list-item') and .//*[contains(text(),'%s')]]", text))).size() > 0) {
            return true;
        }
        return false;
    }
}
