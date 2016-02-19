import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

@RunWith(Parameterized.class)
public class TemperatureTest {
    private double celsius;
    private double fahrenheit;

    public TemperatureTest(double celsius, double fahrenheit) {
        this.celsius = celsius;
        this.fahrenheit = fahrenheit;
    }

    @Test
    public void testGetCelsius() throws Exception {
        Assert.assertEquals(celsius, Temperature.getCelsius(fahrenheit), 0.001);
    }

    @Test
    public void testGetFahrenheit() throws Exception {
        Assert.assertEquals(fahrenheit, Temperature.getFahrenheit(celsius), 0.001);
    }

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {20, 68},
                {0, 32},
                {-20, -4} });
    }

}