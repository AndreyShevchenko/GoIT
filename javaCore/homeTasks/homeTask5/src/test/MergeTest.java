import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Random;

import static junitparams.JUnitParamsRunner.$;

@RunWith(JUnitParamsRunner.class)
public class MergeTest {

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
    public void testSort(int[] array) throws Exception {
        int[] expectedArray = Arrays.copyOfRange(array, 0, array.length);
        Arrays.sort(expectedArray);
        int[] actualArray = Merge.sort(array);
        Assert.assertArrayEquals(expectedArray, actualArray);
    }

    public Object[] parametersForTestSort() {
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