package tstGithub;

import Logger.Log;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Katsiaryna Stalchanka
 * @since 15-Dec-18
 */
public class tstSearchPerson extends tstBase {
    @Test
    public void findAndFollowUser() {
        pages.mainPage().setValueInputSearch(SOME_USER_NAME);
        Log.info("Name is entered in search input.");
        pages.searchPage().clickUsers();
        Log.info("Users was selected.");
        Assert.assertTrue(pages.searchPage().existPerson(SOME_USER_NAME));
    }

}
