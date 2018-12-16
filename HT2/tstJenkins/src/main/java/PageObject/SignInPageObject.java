package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author Katsiaryna Stalchanka
 * @since 06-Dec-18
 */
public class SignInPageObject extends BasePage {
    private By usernameLocator = By.id("j_username");
    private By passwordLocator = By.xpath("//div[@class = 'formRow']//input[@name='j_password']");
    private By signInLocator = By.xpath("//div[@class='submit formRow']//input[@name='Submit']");
    private By keepMeSignInBox = By.xpath("//div[@class='Checkbox Checkbox-medium']//label[@class='Checkbox-wrapper']");

    public SignInPageObject(WebDriver driver) {
        super(driver);
    }

    public By getUsernameLocator() {
        return usernameLocator;
    }

    public By getPasswordLocator() {
        return passwordLocator;
    }

    public String getUsername() {
        return driver.findElement(usernameLocator).getAttribute("value");
    }

    public String getPassword() {
        return driver.findElement(passwordLocator).getAttribute("value");
    }

    /**
     * Verifies if the checkbox "Keep Me SignIn" is selected or not
     *
     * @return true if the checkbox is selected
     */
    public boolean isKeepMeSignedInChecked() {
        if (driver.findElement(keepMeSignInBox).isSelected()) {
            return true;
        }
        return false;
    }

    /**
     * Sets username in username form
     *
     * @param name
     */
    private void setUsername(String name) {
        driver.findElement(usernameLocator).clear();
        driver.findElement(usernameLocator).sendKeys(name);
    }

    /**
     * Sets password in password form
     *
     * @param password
     */
    private void setPassword(String password) {
        driver.findElement(passwordLocator).clear();
        driver.findElement(passwordLocator).sendKeys(password);
    }

    /**
     * Makes "Keep Me SignIn" selected or not
     *
     * @param keepMeSignIn
     */
    private void setKeepMeSignedCheckboxState(boolean keepMeSignIn) {
        if (keepMeSignIn) {
            if (!driver.findElement(keepMeSignInBox).isSelected()) {
                driver.findElement(keepMeSignInBox).click();
            }
        } else {
            if (driver.findElement(keepMeSignInBox).isSelected()) {
                driver.findElement(keepMeSignInBox).click();
            }
        }
    }

    /**
     * Sets all data in signIn form
     *
     * @param name
     * @param password
     * @param keepMeSignIn
     */
    public void signInAndKeepSignin(String name, String password, boolean keepMeSignIn) {
        setUsername(name);
        setPassword(password);
        setKeepMeSignedCheckboxState(keepMeSignIn);
        driver.findElement(signInLocator).click();
    }
}
