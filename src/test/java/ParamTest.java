import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParamTest {

    @Parameters({"login-value", "pass-value"})
    @Test
    public void paramTest(@Optional("Default login") String login, @Optional("111111111") String pass) {
        System.out.println("Login is: " + login);
        System.out.println("Password is: " + pass);
    }
}
