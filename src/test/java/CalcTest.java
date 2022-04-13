import data.StaticProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CalcTest extends BaseTest{

    @Test
    public void testSum(){
        Assert.assertEquals(calculator.sum(2,3), 5, "Неверная сумма ...");
    }

    @Test(enabled = false)
    public void testSum1(){
        Assert.assertEquals(calculator.sum(2,3), 5, "Неверная сумма ...");
    }

    @Test(description = "Test with a description")
    public void testSum2(){
        Assert.assertEquals(calculator.sum(2,3), 5, "Неверная сумма ...");
    }

    @Test(testName = "Test with a name")
    public void testSum3(){
        Assert.assertEquals(calculator.sum(2,3), 5, "Неверная сумма ...");
    }

    @Test(timeOut = 1000)
    public void waitLongTimeTest() throws InterruptedException {
        Thread.sleep(1000);
    }

    @Test(invocationCount = 3, invocationTimeOut = 1000, threadPoolSize = 3)
    public void invocationCountTest() throws InterruptedException {
        Thread.sleep(500);
        Assert.assertEquals(calculator.sum(2,3), 5, "Неверная сумма ...");
    }

    @Test(dataProvider = "dataForSum", dataProviderClass = StaticProvider.class, threadPoolSize = 4)
    public void testDataProvider(int a, int b, int expectedResult){
        Assert.assertEquals(calculator.sum(a,b), expectedResult, "Неверная сумма ...");
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testExeptions(){
        List list = null;
        int size = list.size();
    }
}















