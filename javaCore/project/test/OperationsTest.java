import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

import static junitparams.JUnitParamsRunner.$;

/**
 * Created by JAvengers on 28.01.2016.
 * class checks the work of the four basic arithmetic operations from class Operations
 */
@RunWith(JUnitParamsRunner.class)
public class OperationsTest {
    private BigDecimal a;
    private BigDecimal b;
    private BigDecimal expected;
    Operations oper = new Operations();

    private void getValues(double a, double b, double expected) {
        this.a = new BigDecimal(Double.toString(a));
        this.b = new BigDecimal(Double.toString(b));
        this.expected = new BigDecimal(Double.toString(expected));
    }

    @Test
    @Parameters
    public void testAdd(double a, double b, double expected) throws Exception {
        getValues(a, b, expected);
        Assert.assertEquals(0, this.expected.compareTo(oper.add(this.a, this.b)));
    }

    @Test
    @Parameters
    public void testSubtract(double a, double b, double expected) throws Exception {
        getValues(a, b, expected);
        Assert.assertEquals(0, this.expected.compareTo(oper.subtract(this.a, this.b)));
    }

    @Test
    @Parameters
    public void testMultiply(double a, double b, double expected) throws Exception {
        getValues(a, b, expected);
        Assert.assertEquals(0, this.expected.compareTo(oper.multiply(this.a, this.b)));
    }

    @Test
    @Parameters
    public void testDivide(double a, double b, double expected) throws Exception {
        getValues(a, b, expected);
        Assert.assertEquals(0, this.expected.compareTo(oper.divide(this.a, this.b)));
    }

    @Test(expected = DivisionByZeroException.class)
    @Parameters
    public void testDivideForException(double a, double b, double expected) throws Exception {
        getValues(a, b, expected);
        Assert.assertEquals(0, this.expected.compareTo(oper.divide(this.a, this.b)));
    }

    public Object[] parametersForTestAdd() {
        return $(
                $(3, 5, 8),
                $(4, -2, 2)
        );
    }

    public Object[] parametersForTestSubtract() {
        return $(
                $(3, 5, -2),
                $(4, -2, 6)
        );
    }

    public Object[] parametersForTestMultiply() {
        return $(
                $(3, 5, 15),
                $(4, -2, -8)
        );
    }

    public Object[] parametersForTestDivide() {
        return $(
                $(3, 5, 0.6),
                $(4, -2, -2)
        );
    }

    public Object[] parametersForTestDivideForException() {
        return $(
                $(1000, 0, 0.1),
                $(5, 0, 0)
        );
    }

}