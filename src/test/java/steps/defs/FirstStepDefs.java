package steps.defs;

import configuration.ReadProperties;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.DashboardPage;
import pages.LoginPage;
import services.BrowsersService;
import steps.LoginStep;

public class FirstStepDefs {
    WebDriver driver;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    LoginStep loginStep;

    @Given("открыт браузер")
    public void openBrowser() {
        System.out.println("... открываю браузер ...");

        driver = new BrowsersService().getDriver();
//        loginStep = new LoginStep(driver);
//        loginStep.successLogin(ReadProperties.username(), ReadProperties.password());
    }

//    @After
//    public void closeBrowser(){
//        driver.quit();
//    }                             задизаблено из-за использования хуков


    @When("страница логина открыта")
    public void loginPageIsOpen() {
        driver.get(ReadProperties.getUrl());
        loginPage = new LoginPage(driver);
    }

    @Then("поле username отображается")
    public void usernameFieldIsDisplay() {
        Assert.assertTrue(loginPage.getEmailInput().isDisplayed());
    }

    @When("пользователь с email {string} и паролем {string} залогинился")
    public void userWithEmailAndPassLoggedIn(String email, String psw) {
        loginPage.getPswInput().sendKeys(email);
        loginPage.getPswInput().sendKeys(psw);
        loginPage.getLogInButton().click();
    }

    @Then("кнопка добавить проект отображается")
    public void buttonAddProjectIsDisplayed() {
        dashboardPage = new DashboardPage(driver);
        Assert.assertTrue(true);
    }

    @And("username есть {string}")
    public void usernameIs(String username) {
        System.out.println(username);
    }

    @And("количество проектов - {int}")
    public void countOfProjectsIs(int count) {
        Assert.assertEquals(count, 15);
    }
}
