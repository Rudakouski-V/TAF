import org.testng.annotations.*;

@Listeners(Listener.class)
public class BaseTest {
    protected Calculator calculator = new Calculator("тестовый калькулятор");

    @BeforeSuite
    public void beforeSuite() {System.out.println("BeforeSuite ...");}

    @BeforeTest
    public void beforeTest() {System.out.println("BeforeTest ...");}

    @BeforeClass
    public void beforeClass() {System.out.println("BeforeClass ...");}

    @BeforeMethod
    public void beforeMethod() {System.out.println("BeforeMethod ...");}

    @BeforeGroups
    public void beforeGroups() {System.out.println("BeforeGroups ...");}





    @AfterGroups
    public void afterGroups() {System.out.println("AfterGroups ...");}

    @AfterMethod
    public void afterMethod() {System.out.println("AfterMethod ...");}

    @AfterClass
    public void afterClass() {System.out.println("AfterClass ...");}

    @AfterTest
    public void afterTest() {System.out.println("AfterTest ...");}

    @AfterSuite
    public void afterSuite() {System.out.println("AfterSuite ...");}

}
