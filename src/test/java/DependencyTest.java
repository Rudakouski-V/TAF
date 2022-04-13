import org.testng.Assert;
import org.testng.annotations.Test;

public class DependencyTest {

    @Test
    public void stepB(){System.out.println("step B ...");}

    @Test(dependsOnMethods = "stepB")
    public void stepA(){System.out.println("step A ...");
        Assert.assertTrue(false);}

    @Test(dependsOnMethods = "stepA", alwaysRun = true)
    public void stepC(){System.out.println("step C ...");}

    @Test(dependsOnMethods = {"stepC", "stepA"})
    public void stepD(){System.out.println("step D ...");}
}
