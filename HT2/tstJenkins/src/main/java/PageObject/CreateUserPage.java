package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Katsiaryna Stalchanka
 * @since 09-Dec-18
 */
public class CreateUserPage extends BasePage {

    public CreateUserPage(WebDriver driver) {
        super(driver);
    }

    private By usernameTextLocator = By.xpath("//div[@id='main-panel']//td[text()='Username:']");
    private By usernameInputLocator = By.id("username");

    private By passwordTextLocator = By.xpath("//div[@id='main-panel']//td[text()='Password:']");
    private By passwordInputLocator = By.xpath("//div[@id='main-panel']//input[@name='password1']");

    private By confirmPasswordTextLocator = By.xpath("//div[@id='main-panel']//td[text()='Confirm password:']");
    private By confirmPasswordInputLocator = By.xpath("//div[@id='main-panel']//input[@name='password2']");

    private By fullNameTextLocator = By.xpath("//div[@id='main-panel']//td[text()='Full name:']");
    private By fullNameInputLocator = By.xpath("//div[@id='main-panel']//input[@name='fullname']");

    private By eMailAddressTextLocator = By.xpath("//div[@id='main-panel']//td[text()='E-mail address:']");
    private By eMailAddressInputLocator = By.xpath("//div[@id='main-panel']//input[@name='email']");

    private By createUserLocator = By.id("yui-gen1-button");

    /**
     * Verifies if the form for create user exists on page and the fields are empty.
     */
    public void isCreateUserFormExist() {
        if (isElementExist(usernameInputLocator)) {
            isElementEmpty(usernameInputLocator);
        }
        if (isElementExist(passwordInputLocator)) {
            isElementEmpty(passwordInputLocator);
        }
        if (isElementExist(confirmPasswordInputLocator)) {
            isElementEmpty(confirmPasswordInputLocator);
        }
        if (isElementExist(fullNameInputLocator)) {
            isElementEmpty(fullNameInputLocator);
        }
        if (isElementExist(eMailAddressInputLocator)) {
            isElementEmpty(fullNameInputLocator);
        }
    }

    /**
     * Verifies if the element is displayed
     *
     * @param byLocator
     * @return true if the element is displayed
     * and false if such element isn't displayed
     */
    private boolean isElementExist(By byLocator) {
        if (!driver.findElement(byLocator).isDisplayed()) {
            throw new Error(String.format("The element %s isn't displayed", driver.findElement(byLocator).getAttribute("name")));
        }
        return true;
    }

    /**
     * Verifies if the element is empty
     *
     * @param locator
     * @return true if the element is empty
     * and false if such element isn't empty
     */
    private boolean isElementEmpty(By locator) {
        WebElement element = driver.findElement(locator);
        if ((!element.getAttribute("type").equals("text") || !element.getAttribute("type").equals("password")) &&
                !element.getAttribute("value").isEmpty()) {
            throw new Error(String.format("The element %s isn't empty", element.getAttribute("name")));
        }
        return true;
    }

    /**
     * Sets username on Username input field
     *
     * @param username
     */
    private void setUsername(String username) {
        driver.findElement(usernameInputLocator).sendKeys(username);
    }

    /**
     * Sets password on Password input field
     *
     * @param password
     */
    private void setPassword(String password) {
        driver.findElement(passwordInputLocator).sendKeys(password);
    }

    /**
     * Sets password on Confirm password input field
     *
     * @param password
     */
    private void setConfirmPassword(String password) {
        driver.findElement(confirmPasswordInputLocator).sendKeys(password);
    }

    /**
     * Sets full name on Full Name input field
     *
     * @param fullName
     */
    private void setFullName(String fullName) {
        driver.findElement(fullNameInputLocator).sendKeys(fullName);
    }

    /**
     * Sets e-mail on E-Mail input field
     *
     * @param eMail
     */
    private void setEMail(String eMail) {
        driver.findElement(eMailAddressInputLocator).sendKeys(eMail);
    }

    /**
     * Sets All necessary data in user creation form
     *
     * @param username
     * @param password
     * @param fullName
     * @param eMail
     */
    public void populateForm(String username, String password, String fullName, String eMail) {
        setUsername(username);
        setPassword(password);
        setConfirmPassword(password);
        setFullName(fullName);
        setEMail(eMail);
    }

    /**
     * Clicks on create user
     */
    public void clickCreateUser() {
        driver.findElement(createUserLocator).click();
    }
}
