package steps;

import baseEntities.BaseStep;
import io.qameta.allure.Step;
import models.User;
import models.UserBuilder;
import org.openqa.selenium.WebDriver;
import pages.DashboardPage;
import pages.LoginPage;

public class LoginStep extends BaseStep {

    public LoginStep(WebDriver driver) {
        super(driver);
    }

    @Step("Успешный логин с {email}/{psw}")
    public DashboardPage successLogin(UserBuilder user) {
        login(user.getEmail(), user.getPsw());

        return dashboardPage;
    }

    @Step("Неудачный логин с {email}/{psw}")
    public LoginPage incorrectLogin(User user) {
        login(user.getEmail(), user.getPsw());

        return loginPage;
    }

    private void login(String email, String psw) {
        loginPage.getEmailInput().sendKeys(email);
        loginPage.getPswInput().sendKeys(psw);
        loginPage.getLogInButton().click();
    }

    public LoginPage logout() {

        return loginPage;
    }
}
