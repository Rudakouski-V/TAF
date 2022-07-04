public class Calculator {
    private String title;

    public Calculator(String title) {
        this.title = title;
    }

    public int sum(int a, int b) {
        return (a + b);
    }

    public int divInts(int a, int b) {
        return (a / b);
    }

    public double divDoubles(double a, double b) {
        return (a / b);
    }
}
