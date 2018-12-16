package PageObjects;

import com.epam.ta.driver.DriverSingleton;

/**
 * @author Katsiaryna Stalchanka
 * @since 16-Dec-18
 */
public class Pages {
    private EmptyRepo emptyRepoObject;
    private MainPage mainPageObject;
    private NewFilePage newFilePageObject;
    private SearchPage searchPageObject;
    private UserPage userPageObject;

    public EmptyRepo emptyRepo (){
        if (emptyRepoObject == null){
            emptyRepoObject = new EmptyRepo(DriverSingleton.getDriver());
        }
        return emptyRepoObject;
    }

    public MainPage mainPage (){
        if (mainPageObject == null){
            mainPageObject = new MainPage(DriverSingleton.getDriver());
        }
        return mainPageObject;
    }

    public NewFilePage newFilePage (){
        if (newFilePageObject == null){
            newFilePageObject = new NewFilePage(DriverSingleton.getDriver());
        }
        return newFilePageObject;
    }

    public SearchPage searchPage (){
        if (searchPageObject == null){
            searchPageObject = new SearchPage(DriverSingleton.getDriver());
        }
        return searchPageObject;
    }

    public UserPage userPage (){
        if (userPageObject == null){
            userPageObject = new UserPage(DriverSingleton.getDriver());
        }
        return userPageObject;
    }
}
