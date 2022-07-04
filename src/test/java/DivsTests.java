import data.StaticProvider;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.internal.thread.ThreadTimeoutException;

@Listeners(Listener.class)
public class DivsTests {
    Calculator calculator = new Calculator("Division calculator");

    @Test
    public void divIntsTest() {
        Assert.assertEquals(calculator.divInts(4, 2), 2, "Wrong result...");
    }

    @Test(enabled = false)
    public void divDoublesTest() {
        Assert.assertEquals(calculator.divDoubles(4, 2), 2, "Wrong result...");
    }

    @Test(description = "Test with a description")
    public void divIntsTest1() {
        Assert.assertEquals(calculator.divInts(4, 2), 2, "Wrong result...");
    }

    @Test(testName = "Test with a name")
    public void divDoublesTest1() {
        Assert.assertEquals(calculator.divDoubles(4, 2), 2, "Wrong result...");
    }

    @Test(priority = 1)
    public void divIntsTest2() {
        Assert.assertEquals(calculator.divInts(4, 2), 2, "Wrong result...");
    }

    @Test(priority = 2)
    public void divDoublesTest2() {
        Assert.assertEquals(calculator.divDoubles(4, 2), 2, "Wrong result...");
    }

    @Test(dependsOnMethods = "divDoublesTest2")
    public void divIntsTest3() {
        Assert.assertEquals(calculator.divInts(4, 2), 2, "Wrong result...");
    }

    @Test(dependsOnMethods = "divDoublesTest2")
    public void divDoublesTest3normallyCrashed() {
        Assert.assertEquals(calculator.divDoubles(4, 2), 1, "Waiting for wrong result...");
    }

    @Test(dependsOnMethods = "divDoublesTest3normallyCrashed")
    public void divIntsTest4normallyIgnored() {
        Assert.assertEquals(calculator.divInts(4, 2), 2, "Wrong result...");
    }

    @Test(dependsOnMethods = "divDoublesTest3normallyCrashed", alwaysRun = true)
    public void divDoublesTest4() {
        Assert.assertEquals(calculator.divDoubles(4, 2), 2, "Wrong result...");
    }

    @Test(timeOut = 1000, expectedExceptions = ThreadTimeoutException.class)
    public void divIntsTest5() throws InterruptedException {
        Assert.assertEquals(calculator.divInts(4, 2), 2, "Wrong result...");
        Thread.sleep(1500);
    }

    @Test(timeOut = 1000)
    public void divDoublesTest5() throws InterruptedException {
        Assert.assertEquals(calculator.divDoubles(4, 2), 2, "Wrong result...");
        Thread.sleep(500);
    }

    @Test(invocationCount = 3, invocationTimeOut = 1000, threadPoolSize = 3)
    public void divIntsTest6() throws InterruptedException {
        Assert.assertEquals(calculator.divInts(4, 2), 2, "Wrong result...");
        Thread.sleep(500);
    }

    @Test(invocationCount = 3, invocationTimeOut = 1000, threadPoolSize = 3)
    public void divDoublesTest6() throws InterruptedException {
        Assert.assertEquals(calculator.divDoubles(4, 2), 2, "Wrong result...");
        Thread.sleep(999);
    }

    @Test(expectedExceptions = ArithmeticException.class)
    public void divIntsTest8() {
        Assert.assertEquals(calculator.divInts(4, 0), 0, "Wrong result...");
    }

    @Test(expectedExceptions = AssertionError.class)
    public void divDoublesTest8() {
        Assert.assertEquals(calculator.divDoubles(4, 0), 0.0, "Wrong result...");
    }

    @Test(groups = "smoke")
    public void divIntsTest9() {
        Assert.assertEquals(calculator.divInts(4, 2), 2, "Wrong result...");
    }

    @Test(groups = {"smoke", "regression"})
    public void divDoublesTest9() {
        Assert.assertEquals(calculator.divDoubles(4, 2), 2, "Wrong result...");
    }

    @Parameters({"oneParam", "twoParam", "threeParam"})
    @Test
    public void divIntsTest10(int firstArg, int secondArg, int thirdArg) {
        Assert.assertEquals(calculator.divInts(firstArg, secondArg), thirdArg, "Wrong result...");
    }

    @Parameters({"oneParam", "twoParam", "threeParam"})
    @Test
    public void divDoublesTest10(@Optional("10") double firstArg, @Optional("5") double secondArg, @Optional("2") double thirdArg) {
        Assert.assertEquals(calculator.divDoubles(firstArg, secondArg), thirdArg, "Wrong result...");
    }

    @Test(groups = "FirstGroup")
    public void divIntsTest11() {
        Assert.assertEquals(calculator.divInts(4, 2), 2, "Wrong result...");
    }

    @Test(dependsOnGroups = "FirstGroup")
    public void divDoublesTest11() {
        Assert.assertEquals(calculator.divDoubles(4, 2), 2, "Wrong result...");
    }
}
