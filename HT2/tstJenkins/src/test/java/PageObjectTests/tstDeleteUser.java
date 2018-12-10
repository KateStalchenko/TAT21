package PageObjectTests;

import ConstantsString.StringConsts;
import PageObject.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author Katsiaryna Stalchanka
 * @since 10-Dec-18
 */
public class tstDeleteUser {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        System.setProperty(StringConsts.GECKODRIVER, StringConsts.PATH_GECKODRIVER);
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }

    @Test
    public void deleteUser() {
        driver.get(StringConsts.BASE_URL);

        SignInPageObject signInPageObject = new SignInPageObject(driver);
        signInPageObject.signIn(StringConsts.USERNAME, StringConsts.PASSWORD).signInClick();

        TasksMenuObject tasksMenuObject = new TasksMenuObject(driver);
        tasksMenuObject.clickManageJenkins();

        ManageJenkinsObject manageJenkinsObject = new ManageJenkinsObject(driver);
        manageJenkinsObject.clickManageUsers();

        UsersObject usersObject = new UsersObject(driver);
        usersObject.clickDeleteUser();

        DeleteUserObject deleteUserObject = new DeleteUserObject(driver);
        deleteUserObject.isElementDisplayed(deleteUserObject.getTextLocator());
        deleteUserObject.clickYesDelete();

        if (usersObject.notExistTrWithSpecifiedText(StringConsts.SOME_USER) == false) {
            Assert.fail();
        }

        if (usersObject.notExistHREFWithSpecifiedData("user/tat21_user/delete") == false) {
            Assert.fail();
        }

        if (usersObject.notExistHREFWithSpecifiedData("user/admin/delete") == false) {
            Assert.fail();
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
