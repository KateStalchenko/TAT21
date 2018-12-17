package tstGithub;

import Logger.Log;
import com.epam.ta.utils.Utils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Katsiaryna Stalchanka
 * @since 12-Dec-18
 */
public class tstCreateNewFile extends tstBase {
    @BeforeMethod
    public void start() {
        String repoNamePostfix = Utils.getRandomString(REPOSITORY_NAME_POSTFIX_LENGTH);
        StringBuilder repoName = new StringBuilder();
        repoName.append(REPOSITORY_NAME);
        repoName.append(repoNamePostfix);
        steps.createNewRepository(repoName.toString(), DESCRIPTION);
        Log.info("New Repository is created.");
    }

    @Test
    public void createFileEmptyRepo() {
        if (steps.currentRepositoryIsEmpty()) {
            pages.emptyRepo().clickCreateNewFile();
            Log.info("New File is created.");
        } else {
            Error error = new Error("This Repo isn't empty!");
            Log.error(error.getMessage());
            throw error;
        }

        pages.newFilePage().setFileName();
        Log.info("File is named");
        pages.newFilePage().writeTextNewFileField(PROGRAMMING);
        Log.info("File is fulfilled");
        pages.newFilePage().setCommitText(SOME_COMMIT);
        if (pages.newFilePage().isCommitNewFileEnabled()) {
            pages.newFilePage().clickCommitNewFile();
            Log.info("File is committed");
        } else {
            Error error = new Error("Not all fields are fulfilled.");
            Log.error(error.getMessage());
            throw error;
        }
    }
}
