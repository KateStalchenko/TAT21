package tstGithub;

import Logger.Log;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Katsiaryna Stalchanka
 * @since 13-Dec-18
 */
public class tstFollowUser extends tstBase {
    @Test
    public void findAndFollowUser() {
        pages.userPage().setUsername(SOME_USER_NAME);
        Log.info("Username is set");
        pages.userPage().openPage();
        Log.info("User page is opened");

        if (!pages.userPage().checkFollowed()) {
            pages.userPage().followPerson();
            Log.info("User is followed");
        }

        Assert.assertTrue(pages.userPage().checkFollowed());
    }
}
