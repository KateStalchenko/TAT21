package PageObjectTests;

import ConstantsString.StringConsts;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Katsiaryna Stalchanka
 * @since 10-Dec-18
 */
public class tstDeleteUser extends tstBase {
    @Test
    public void deleteUser() {
        pages.signInPage().openPage(StringConsts.BASE_URL);
        pages.signInPage().signInAndKeepSignin(StringConsts.USERNAME, StringConsts.PASSWORD, StringConsts.isKeepSignIn);

        pages.tasksMenuPage().clickManageJenkins();

        pages.manageJenkinsPage().clickManageUsers();

        pages.usersPage().clickDeleteUser();

        if (pages.deleteUserPage().isTextLocatorDisplayed()) {
            pages.deleteUserPage().clickYesDelete();
        }

        Assert.assertFalse(pages.usersPage().isUserExistInTable(StringConsts.SOME_USER));
        Assert.assertFalse(pages.usersPage().isHrefExist("user/tat21_user/delete"));
        Assert.assertFalse(pages.usersPage().isHrefExist("user/admin/delete"));
    }
}
