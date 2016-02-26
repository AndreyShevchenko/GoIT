import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Random;

import static junitparams.JUnitParamsRunner.$;

@RunWith(JUnitParamsRunner.class)
public class CombTest {

    private void swapArray(int[] array) {
        int i = 0;
        while (i < array.length - 1 - i) {
            array[i] -= array[array.length - 1 - i];
            array[array.length - 1 - i] += array[i];
            array[i] = array[array.length - 1 - i] - array[i];
            i++;
        }
    }

    private int[] getRandomArray() {
        int size = 1_000_000;
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt() % size;
        }
        return array;
    }

    @Test(timeout = 2000)
    @Parameters
    public void testComb(int[] array) throws Exception {
        int[] expectedArray = Arrays.copyOfRange(array, 0, array.length);
        Arrays.sort(expectedArray);
        int[] actualArray = Comb.sort(array, "INCREASE");
        Assert.assertArrayEquals(expectedArray, actualArray);
        swapArray(expectedArray);
        actualArray = Comb.sort(array, "DECREASE");
        Assert.assertArrayEquals(expectedArray, actualArray);
    }

    public Object[] parametersForTestComb() {
        return $(
                $(new int[]{-1, 1, 0, -1, 1, 0, 1, -1, -1, 1, 0}),
                $(new int[]{Integer.MIN_VALUE, 0, Integer.MAX_VALUE, (int) 1E31}),
                $(new int[]{5, -5, 10, 0, 23, -23}),
                $(new int[]{5, 4, 3, 2, 1, 0, (int) 1E33, -1, -2}),
                $(new int[]{5, 4, 3, 2, 1, 0, (int) 1E-33, -1, -2}),
                $(new int[]{5, 4, 3, 2, 1, 0, (int) 1E30, (int) -1E33, -1, -2}),
                $(new int[]{Integer.MIN_VALUE}),
                $(new int[]{1, 1}),
                $(new int[]{}),
                $(getRandomArray())
        );
    }
}