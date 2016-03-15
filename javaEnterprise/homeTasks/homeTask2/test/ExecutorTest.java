import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.*;

public class ExecutorTest {
    private ExecutorRealization test = new ExecutorRealization();
    private final int[][] data = {
            {191, 0, 35, 38},
            {766, 228, 35, 130},
            {714, 0, 35, 124},
            {858, 0, 35, 154},
            {509, 251, 35, 86},
            {160, 0, 35, 69},
            {287, 215, 0, 72}
    };

    private void setTestData() {
        for (int[] aData : data) {
            test.addTask(new TaskRealization(aData[0], aData[1], aData[2], aData[3]));
        }
    }

    @Test
    public void testExecutorRealization() throws Exception {
        setTestData();
        test.execute();
        List<Double> a = asList(3.0, 2.0, 4.0, 4.0, 1.0, 0.0, 0.0);
        Assert.assertEquals(test.getValidResults(), a);
        Assert.assertTrue(test.getInvalidResults().isEmpty());
    }

}