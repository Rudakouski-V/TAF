import org.testng.annotations.Test;

public class PriorityTest {

    @Test(priority = 1)
    public void stepB(){System.out.println("step B ...");}

    @Test(priority = 2)
    public void stepA1(){System.out.println("step A1 ...");}

    @Test(priority = 3)
    public void stepA11(){System.out.println("step A11 ...");}

    @Test(priority = 4)
    public void stepC(){System.out.println("step C ...");}

    @Test(priority = 5)
    public void stepD(){System.out.println("step D ...");}
}
