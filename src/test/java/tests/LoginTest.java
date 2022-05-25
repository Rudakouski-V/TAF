package tests;

import baseEntities.BaseTest;
import configuration.ReadProperties;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.io.ByteArrayInputStream;
import java.util.UUID;

public class LoginTest extends BaseTest {

    @Test
    public void successLoginTest() {
        Assert.assertTrue(
                loginStep.successLogin(
                                ReadProperties.username(),
                                ReadProperties.password()
                        )
                        .isPageOpened()
        );
    }

    @Test
    public void incorrectEmailLoginTest() {
        Assert.assertEquals(
                loginStep.incorrectLogin("sdsd", ReadProperties.password()).getErrorTextElement().getText(),
                "Email/Login or Password is incorrect. Please try again.",
                "Неверное сообщение об ошибке");

        Allure.addAttachment(UUID.randomUUID().toString(), new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

        // заведомо вносим ошибку для проверки скриншота
        Assert.assertTrue(false);

        Allure.addAttachment(UUID.randomUUID().toString(), new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Test
    public void incorrectPswLoginTest() {
        Assert.assertEquals(
                loginStep.incorrectLogin(ReadProperties.username(), "123").getErrorTextElement().getText(),
                "Email/Login or Password is incorrect. Please try again.",
                "Неверное сообщение об ошибке");
    }

    @Test
    public void successLoginInvocationsTest() {
        Assert.assertTrue(
                new LoginPage(driver)
                        .successLogin("123", "123")
                        .openProject("jdfhdjf")
                        .getTabByName("2567").isDisplayed()
        );
    }
}


