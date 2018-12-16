package PageObjects;

import com.epam.ta.pages.AbstractPage;
import com.epam.ta.utils.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Katsiaryna Stalchanka
 * @since 13-Dec-18
 */
public class NewFilePage extends AbstractPage {
    private final String BASE_URL = "https://github.com/testautomationuser/";
    private final String URL_END = "/new/master";

    private final int FILE_NAME_LENGTH = 7;

    public NewFilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath = "//a[@data-pjax='#js-repo-pjax-container']")
    private WebElement linkCurrentRepository;

    @FindBy(xpath = "//div[@id='js-repo-pjax-container']//input[@class='form-control js-blob-filename js-breadcrumb-nav']")
    private WebElement fileName;

    @FindBy(xpath = "//div[@id='js-repo-pjax-container']//div[@class='CodeMirror cm-s-github-light']")
    private WebElement newFileField;

    @FindBy(id = "commit-summary-input")
    private WebElement commitText;

    @FindBy(id = "submit-file")
    private WebElement commitNewFile;

    public String getLinkCurrentRepository() {
        return linkCurrentRepository.getText();
    }

    @Override
    public void openPage() {
        driver.navigate().to(BASE_URL + getLinkCurrentRepository() + URL_END);
    }

    public void setFileName() {
        fileName.sendKeys(Utils.getRandomString(FILE_NAME_LENGTH));
    }

    public void writeTextNewFileField(String text) {
        newFileField.click();
        sendKeys(text);
    }

    private void sendKeys(String text){
        Actions action = new Actions(driver);
        action.sendKeys(text).perform();
        action.release();
    }

    public void setCommitText(String text){
        commitText.sendKeys(text);
    }

    public void clickCommitNewFile(){
        commitNewFile.click();
    }

    public WebElement getCommitNewFile() {
        return commitNewFile;
    }
}
