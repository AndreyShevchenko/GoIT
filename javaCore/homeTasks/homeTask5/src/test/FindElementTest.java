import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junitparams.JUnitParamsRunner.$;

@RunWith(JUnitParamsRunner.class)
public class FindElementTest {

    @Test
    @Parameters
    public void testGetElement(int[] array, int minElement, int maxElement) throws Exception {
        Assert.assertEquals(minElement, FindElement.getElement(array, "MIN"));
        Assert.assertEquals(maxElement, FindElement.getElement(array, "MAX"));
    }

    public Object[] parametersForTestGetElement() {
        return $(
                $(new int[]{-1, 1, 0, -1, 1, 0, 1, -1, -1, 1, 0}, -1, 1),
                $(new int[]{Integer.MIN_VALUE, 0, Integer.MAX_VALUE, (int) 1E31}, Integer.MIN_VALUE, Integer.MAX_VALUE),
                $(new int[]{5, -5, 10, 0, 23, -23}, -23, 23),
                $(new int[]{5, 4, 3, 2, 1, 0, (int) 1E33, -1, -2}, -2, Integer.MAX_VALUE),
                $(new int[]{5, 4, 3, 2, 1, 0, (int) 1E-33, -1, -2}, -2, 5),
                $(new int[]{5, 4, 3, 2, 1, 0, (int) 1E30, (int) -1E33, -1, -2}, Integer.MIN_VALUE, (int) 1E30),
                $(new int[]{Integer.MIN_VALUE}, Integer.MIN_VALUE, Integer.MIN_VALUE),
                $(new int[]{1, 1}, 1, 1),
                $(new int[]{}, 0, 0)
        );
    }

    @Test
    public void testGetElementForWrongValue() throws Exception {
        int[] array = {1};
        String value = "MX";
        Assert.assertEquals(0, FindElement.getElement(array, value));
    }


}