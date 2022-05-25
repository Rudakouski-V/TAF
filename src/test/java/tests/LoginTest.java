package tests;

import baseEntities.BaseTest;
import configuration.ReadProperties;
import models.User;
import models.UserBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void successLoginTest() {
        Assert.assertTrue(
                loginStep.successLogin(new UserBuilder()).isPageOpened()
        );
    }

    @Test
    public void incorrectEmailLoginTest() {
        User user = new User();
        user.setEmail("sdsd");
        user.setPsw(ReadProperties.password());

        Assert.assertEquals(
                loginStep.incorrectLogin(user).getErrorTextElement().getText(),
                "Email/Login or Password is incorrect. Please try again.",
                "Неверное сообщение об ошибке");

        // заведомо вносим ошибку для проверки скриншота
        Assert.assertTrue(false);
    }

    @Test
    public void incorrectPswLoginTest() {
        User user = new User();
        user.setEmail(ReadProperties.username());
        user.setPsw("123");

        Assert.assertEquals(
                loginStep.incorrectLogin(user).getErrorTextElement().getText(),
                "Email/Login or Password is incorrect. Please try again.",
                "Неверное сообщение об ошибке");
    }

    @Test
    public void successLoginBuilderTest() {
//        UserBuilder user = new UserBuilder.Builder()
//                .withEmail("345678")
//                .withPsw("qwerty")
//                .build();

        Assert.assertTrue(
                loginStep.successLogin(new UserBuilder()).isPageOpened()
        );
    }
}
