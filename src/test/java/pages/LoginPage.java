package pages;

import baseEntities.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {
    // Блок описания селекторов для элементов
    private By emailInputLocator = By.id("name");
    private By pswInputLocator = By.id("password");
    private By logInButtonLocator = By.id("button_primary");
    private By errorTextLocator = By.className("error-text");


    // Блок инициализации
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected By getPageIdentifier() {
        return emailInputLocator;
    }


    // Блок атомарных методов
    public WebElement getEmailInput() {
        return waitsService.waitForExists(emailInputLocator);
    }

    public WebElement getPswInput() {
        return waitsService.waitForExists(pswInputLocator);
    }

    public WebElement getLogInButton() {
        return waitsService.waitForExists(logInButtonLocator);
    }

    public WebElement getErrorTextElement() {
        return waitsService.waitForExists(errorTextLocator);
    }


    // Блок комплексных методов
    @Step("Успешный логин с {email}/{psw}")
    public DashboardPage successLogin(String email, String psw) {
        login(email, psw);

        return new DashboardPage(driver);
    }

    @Step("Неудачный логин с {email}/{psw}")
    public LoginPage incorrectLogin(String email, String psw) {
        login(email, psw);

        return this;
    }

    private void login(String email, String psw) {
        getEmailInput().sendKeys(email);
        getPswInput().sendKeys(psw);
        getLogInButton().click();
    }

    public LoginPage logout() {
        return this;
    }
}
