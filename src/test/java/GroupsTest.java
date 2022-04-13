import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupsTest {

    @Test(groups = "smoke")
    public void stepB(){System.out.println("step B ...");}

    @Test(groups = "regression")
    public void stepA(){System.out.println("step A ...");}

    @Test(groups = "regression")
    public void stepC(){System.out.println("step C ...");}

    @Test(groups = {"smoke", "regression"})
    public void stepD(){System.out.println("step D ...");}
}
