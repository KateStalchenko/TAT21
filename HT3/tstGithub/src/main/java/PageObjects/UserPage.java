package PageObjects;

import com.epam.ta.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Katsiaryna Stalchanka
 * @since 15-Dec-18
 */
public class UserPage extends AbstractPage {
    private final String BASE_URL = "https://github.com/";
    private String username;

    @FindBy(xpath = "//span[@class='p-nickname vcard-username d-block']")
    private WebElement usernameElement;

    @FindBy(xpath = "//div[contains(@class,'user-profile-following-container ')]//button[contains(text(), 'Follow')]")
    private WebElement followButton;

    @FindBy(xpath = "//div[contains(@class,'user-profile-following-container ')]//button[contains(text(), 'Unfollow')]")
    private WebElement unfollowButton;

    public UserPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage() {
        driver.navigate().to(BASE_URL + username);
    }

    public void setUsername(String name) {
        username = name;
    }

    public boolean checkFollowed() {
        if (unfollowButton.isDisplayed()) {
            return true;
        }
        return false;
    }

    public void followPerson() {
        if (followButton.isDisplayed()) {
            followButton.click();
            return;
        }
        System.out.println("You have already followed this person.");
    }

    public void unfollowPerson() {
        if (unfollowButton.isDisplayed()) {
            unfollowButton.click();
            return;
        }
        System.out.println("You don't followed this person.");
    }
}
