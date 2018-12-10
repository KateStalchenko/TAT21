package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author Katsiaryna Stalchanka
 * @since 06-Dec-18
 */
public class SignInPageObject extends DriverPageObject {
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
     * @return true if the checkbox is selected
     */
    public boolean keepMeSignInTrue() {
        if (driver.findElement(keepMeSignInBox).isSelected()) {
            return true;
        }
        return false;
    }

    /**
     * Sets username in username form
     * @param name
     * @return SignInPageObject
     */
    private SignInPageObject setUsername(String name) {
        driver.findElement(usernameLocator).clear();
        driver.findElement(usernameLocator).sendKeys(name);
        return this;
    }

    /**
     * Sets password in password form
     * @param password
     * @return SignInPageObject
     */
    private SignInPageObject setPassword(String password) {
        driver.findElement(passwordLocator).clear();
        driver.findElement(passwordLocator).sendKeys(password);
        return this;
    }

    /**
     * Makes "Keep Me SignIn" selected or not
     * @param keepMeSignIn
     * @return SignInPageObject
     */
    private SignInPageObject setKeepMeSignIn(boolean keepMeSignIn) {
        if (keepMeSignIn == true) {
            if (!driver.findElement(keepMeSignInBox).isSelected()) {
                driver.findElement(keepMeSignInBox).click();
            }
            return this;
        } else {
            if (driver.findElement(keepMeSignInBox).isSelected()) {
                driver.findElement(keepMeSignInBox).click();
            }
        }
        return this;
    }

    /**
     * Clicks on signIn button
     * @return SignInPageObject
     */
    public SignInPageObject signInClick() {
        driver.findElement(signInLocator).click();
        return this;
    }

    /**
     * Sets name and password in forms
     * @param name
     * @param password
     * @return SignInPageObject
     */
    public SignInPageObject signIn(String name, String password) {
        this.setUsername(name);
        this.setPassword(password);
        return this;
    }

    /**
     * Sets all data in signIn form
     * @param name
     * @param password
     * @param keepMeSignIn
     * @return SignInPageObject
     */
    public SignInPageObject signInAndKeepSignin(String name, String password, boolean keepMeSignIn) {
        setUsername(name);
        setPassword(password);
        setKeepMeSignIn(keepMeSignIn);
        return this;
    }
}
