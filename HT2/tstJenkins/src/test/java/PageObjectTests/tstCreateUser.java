package PageObjectTests;

import ConstantsString.StringConsts;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Katsiaryna Stalchanka
 * @since 06-Dec-18
 */
public class tstCreateUser extends tstBase {
    @Test
    public void tstCreateUser() {
        pages.signInPage().openPage(StringConsts.BASE_URL);
        pages.signInPage().signInAndKeepSignin(StringConsts.USERNAME, StringConsts.PASSWORD, StringConsts.isKeepSignIn);

        pages.tasksMenuPage().clickManageJenkins();

        pages.manageJenkinsPage().clickManageUsers();

        pages.usersPage().createUserLinkExist();
        if (pages.usersPage().isDisplayedCreateUserLocator()) {
            pages.usersPage().clickCreateUser();
        }

        pages.createUserPage().isCreateUserFormExist();
        pages.createUserPage().populateForm(StringConsts.SOME_USER, StringConsts.SOME_PASSWORD,
                StringConsts.SOME_FULL_NAME, StringConsts.SOME_EMAIL);
        pages.createUserPage().clickCreateUser();

        Assert.assertTrue(pages.usersPage().findTrElementWithSpecifiedText());
    }
}
