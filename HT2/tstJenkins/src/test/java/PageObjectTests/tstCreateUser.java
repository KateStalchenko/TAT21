package PageObjectTests;

import ConstantsString.StringConsts;
import PageObject.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author Katsiaryna Stalchanka
 * @since 06-Dec-18
 */
public class tstCreateUser {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        System.setProperty(StringConsts.GECKODRIVER, StringConsts.PATH_GECKODRIVER);
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }

    @Test
    public void tstCreateUser() {
        driver.get(StringConsts.BASE_URL);

        SignInPageObject signInPageObject = new SignInPageObject(driver);
        signInPageObject.signIn(StringConsts.USERNAME, StringConsts.PASSWORD).signInClick();

        TasksMenuObject tasksMenuObject = new TasksMenuObject(driver);
        tasksMenuObject.clickManageJenkins();

        ManageJenkinsObject manageJenkinsObject = new ManageJenkinsObject(driver);
        manageJenkinsObject.clickManageUsers();

        UsersObject usersObject = new UsersObject(driver);
        usersObject.createUserLinkExist();
        usersObject.isDisplayed(usersObject.getCreateUserLocator());
        usersObject.clickCreateUser();

        CreateUserObject createUserObject = new CreateUserObject(driver);
        createUserObject.isCreateUserFormExist();
        createUserObject.setAllData(StringConsts.SOME_USER, StringConsts.SOME_PASSWORD,
                StringConsts.SOME_FULL_NAME, StringConsts.SOME_EMAIL);
        createUserObject.clickCreateUser();

        usersObject.findTrElementWithSpecifiedText();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
