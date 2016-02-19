import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.w3c.dom.NamedNodeMap;

import static junitparams.JUnitParamsRunner.$;

@RunWith(JUnitParamsRunner.class)
public class SquareFiguresTest {

    @Test
    @Parameters
    public void testGetSquareTriangle(double sideA, double sideB, double sideC, double result) throws Exception {
        Assert.assertEquals(result, SquareFigures.getSquare(sideA, sideB, sideC), 0.1);
    }

    @Test
    @Parameters
    public void testGetSquareRectangle(double sideA, double sideB, double result) throws Exception {
        Assert.assertEquals(result, SquareFigures.getSquare(sideA, sideB), 0.1);
    }

    @Test
    @Parameters
    public void testGetSquareCircle(double radius, double result) throws Exception {
        Assert.assertEquals(result, SquareFigures.getSquare(radius), 0.1);
    }

    public Object[] parametersForTestGetSquareTriangle() {
        return $(
                $(3, 5, 8, 0),
                $(4, 3, 2, 2.9),
                $(4, 3, -1, Double.NaN),
                $(0, 0, 0, Double.NaN),
                $(1E50, 1E50, 1E50, 4.330127018922194E99),
                $(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Double.NaN),
                $(Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE, 0),
                $(Double.NaN, 100, 10, Double.NaN)
        );
    }

    public Object[] parametersForTestGetSquareRectangle() {
        return $(
                $(3, 5, 15),
                $(4, -1, Double.NaN),
                $(0, 0, Double.NaN),
                $(1E50, 1E50, 1.0000000000000002E100),
                $(Double.MAX_VALUE, Double.MAX_VALUE, Double.NaN),
                $(Double.MIN_VALUE, Double.MIN_VALUE, 0),
                $(Double.NaN, 100, Double.NaN)
        );
    }

    public Object[] parametersForTestGetSquareCircle() {
        return $(
                $(3, 28.3),
                $(157.682, 78111.3),
                $(0, Double.NaN),
                $(1E50, 3.141592653589794E100),
                $(Double.MAX_VALUE, Double.NaN),
                $(Double.MIN_VALUE, 0),
                $(Double.NaN, Double.NaN)
        );
    }



}