import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

@RunWith(Parameterized.class)
public class DistancePointsTest {
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double distance;

    public DistancePointsTest(double x1, double y1, double x2, double y2, double distance) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.distance = distance;
    }

    @Test
    public void testGetDistance() throws Exception {
        Assert.assertEquals(distance, DistancePoints.getDistance(x1, y1, x2, y2), 0.1);
    }

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {0, 0, 1, 1, 1.4},
                {-1, -1, 1, 1, 2.8}, });
    }
}