package tests;

import baseEntities.BaseTest;
import models.UserBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MenuTest extends BaseTest {
    @Test
    public void validate_Dashboard_Menu_Test() {
        Assert.assertTrue(
                loginStep.successLogin(new UserBuilder())
                        .topMenuPage.isPageOpened());
    }

    @Test
    public void validate_Projects_SideMenu_Test() {
        loginStep.successLogin(new UserBuilder());

        Assert.assertTrue(
                navigationStep.navigateToProjectsPage()
                        .sideMenuPage.isPageOpened());
    }
}
