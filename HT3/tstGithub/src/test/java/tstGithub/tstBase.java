package tstGithub;

import Logger.Log;
import PageObjects.Pages;
import com.epam.ta.steps.Steps;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * @author Katsiaryna Stalchanka
 * @since 15-Dec-18
 */
public class tstBase extends Pages {
    protected final String USERNAME = "testautomationuser";
    protected final String PASSWORD = "Time4Death!";
    protected static final String SOME_USER_NAME = "afc163";
    protected final String REPOSITORY_NAME = "TAT21_";
    protected final String DESCRIPTION = "my auto-generated repo";
    protected static final String PROGRAMMING = "I hate programming! I hate programming! It works! I love programming!!!";
    protected static final String SOME_COMMIT = "Update file.";

    protected final int REPOSITORY_NAME_POSTFIX_LENGTH = 3;

    protected Steps steps = new Steps();
    protected Pages pages = new Pages();

    @BeforeMethod
    public void startUp() {
        steps.openBrowser();
        steps.loginGithub(USERNAME, PASSWORD);
    }

    @AfterMethod
    public void quit() {
        steps.closeBrowser();
        Log.info("Browser closed.");
    }

}
