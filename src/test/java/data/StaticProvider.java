package data;

import org.testng.annotations.DataProvider;

import static jdk.nashorn.internal.objects.Global.Infinity;
import static jdk.nashorn.internal.objects.Global.NaN;

public class StaticProvider {

    @DataProvider(name = "dataForSum")
    public static Object[][] dataForSumTest() {
        return new Object[][]{
                {-2, -3, -5},
                {0, 0, 0},
                {2, 3, 5}
        };
    }

    @DataProvider(name = "dataForDivInts")
    public static Object[][] dataForDivIntsTest() {
        return new Object[][]{
                {-10, -10, 1},
                {0, -10, 0},
                {0, 5, 0},
                {-10, 5, -2},
                {10, -5, -2},
                {3, 4, 0},
                {4, 3, 1},
                {-3, 4, 0},
                {4, -3, -1},
                {3, -4, 0},
                {-4, 3, -1},
                {-3, -4, 0},
                {-4, -3, 1},
        };
    }

    @DataProvider(name = "dataForDivIntsByZero")
    public static Object[][] dataForDivIntsTestByZero() {
        return new Object[][]{
                {-10, 0, 0},
                {0, 0, 0},
                {-0, 0, 0},
                {0, -0, 0},
                {5, 0, 0},
        };
    }

    @DataProvider(name = "dataForDivDoubles")
    public static Object[][] dataForDivDoublesTest() {
        return new Object[][]{
                {-10, -10, 1.0},
                {-10, 0, -Infinity},
                {0, -10, 0},
                {0, 0, NaN},
                {-0, 0, NaN},
                {0, -0, NaN},
                {5, 0, Infinity},
                {0, 5, 0},
                {-10, 5, -2.0},
                {10, -5, -2.0},
                {3, 4, 0.75},
                {4, 3, 1.3333333333333333},
                {-3, 4, -0.75},
                {4, -3, -1.3333333333333333},
                {3, -4, -0.75},
                {-4, 3, -1.3333333333333333},
                {-3, -4, 0.75},
                {-4, -3, 1.3333333333333333},
        };
    }
}
