package PageObjects;

import com.epam.ta.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Katsiaryna Stalchanka
 * @since 12-Dec-18
 */
public class EmptyRepo extends AbstractPage {
    private final String BASE_URL = "https://github.com/testautomationuser/";

    public EmptyRepo(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath = "//div[@id='js-repo-pjax-container']//a[text()='creating a new file']")
    private WebElement createNewFile;

    @FindBy(xpath = "//a[@data-pjax='#js-repo-pjax-container']")
    private WebElement linkCurrentRepository;

    @Override
    public void openPage() {
        driver.navigate().to(BASE_URL + getLinkCurrentRepository());
    }

    public String getLinkCurrentRepository() {
        return linkCurrentRepository.getText();
    }

    public void clickCreateNewFile(){
        createNewFile.click();
    }
}
