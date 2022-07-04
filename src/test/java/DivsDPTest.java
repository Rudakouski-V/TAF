import data.StaticProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DivsDPTest {
    Calculator calculator = new Calculator("Division calculator with DataProvider");

    @Test(dataProvider = "dataForDivInts", dataProviderClass = StaticProvider.class, threadPoolSize = 32)
    public void divIntsDPTest(int a, int b, int c) {
        Assert.assertEquals(calculator.divInts(a, b), c, "Wrong result...");
    }

    @Test(dataProvider = "dataForDivIntsByZero", dataProviderClass = StaticProvider.class,
            threadPoolSize = 32, expectedExceptions = ArithmeticException.class)
    public void divIntsDPByZeroTest(int a, int b, int c) {
        Assert.assertEquals(calculator.divInts(a, b), c, "Wrong result...");
    }

    @Test(dataProvider = "dataForDivDoubles", dataProviderClass = StaticProvider.class, threadPoolSize = 32)
    public void divDoublesDPTest(double a, double b, double c) {
        Assert.assertEquals(calculator.divDoubles(a, b), c, "Wrong result...");
    }
}
