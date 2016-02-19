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
                $(4, 0, 2, -1),
                $(3, 5, 9, 0)
        );
    }

    public Object[] parametersForTestGetSquareRectangle() {
        return $(
                $(3, 5, 15),
                $(4, 3, 12),
                $(-1, 3, -1)
        );
    }

    public Object[] parametersForTestGetSquareCircle() {
        return $(
                $(3, 28.3),
                $(4, 50.3),
                $(-2, -1)
        );
    }



}