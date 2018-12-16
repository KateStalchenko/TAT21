package PageObject;

import driver.DriverSingleton;

/**
 * @author Katsiaryna Stalchanka
 * @since 17-Dec-18
 */
public class Pages {
    private CreateUserPage createUserPageObject;
    private DeleteUserPage deleteUserPageObject;
    private HeaderPageComponent headerPageComponentObject;
    private ManageJenkinsPage manageJenkinsPageObject;
    private SignInPageObject signInPageObject;
    private TasksMenuPage tasksMenuPageObject;
    private UsersPage usersPageObject;

    public CreateUserPage createUserPage() {
        if (createUserPageObject == null) {
            createUserPageObject = new CreateUserPage(DriverSingleton.getDriver());
        }
        return createUserPageObject;
    }

    public DeleteUserPage deleteUserPage() {
        if (deleteUserPageObject == null) {
            deleteUserPageObject = new DeleteUserPage(DriverSingleton.getDriver());
        }
        return deleteUserPageObject;
    }

    public HeaderPageComponent headerPageComponent() {
        if (headerPageComponentObject == null) {
            headerPageComponentObject = new HeaderPageComponent(DriverSingleton.getDriver());
        }
        return headerPageComponentObject;
    }

    public ManageJenkinsPage manageJenkinsPage() {
        if (manageJenkinsPageObject == null) {
            manageJenkinsPageObject = new ManageJenkinsPage(DriverSingleton.getDriver());
        }
        return manageJenkinsPageObject;
    }

    public SignInPageObject signInPage() {
        if (signInPageObject == null) {
            signInPageObject = new SignInPageObject(DriverSingleton.getDriver());
        }
        return signInPageObject;
    }

    public TasksMenuPage tasksMenuPage() {
        if (tasksMenuPageObject == null) {
            tasksMenuPageObject = new TasksMenuPage(DriverSingleton.getDriver());
        }
        return tasksMenuPageObject;
    }

    public UsersPage usersPage() {
        if (usersPageObject == null) {
            usersPageObject = new UsersPage(DriverSingleton.getDriver());
        }
        return usersPageObject;
    }
}
